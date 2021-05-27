import java.util.*;

public class Trip {
    private int[] path;
    private int numberOfSteps;
    private Set<Integer> processedSteps;
    private PriorityQueue<Step> stepQueue;
    private List<List<Step>> steplist;

    public Trip(int numberOfSteps) {
        this.numberOfSteps = numberOfSteps;
        path = new int[numberOfSteps];
        processedSteps = new HashSet<>();
        stepQueue = new PriorityQueue<>(numberOfSteps, new Step());
    }

    public void getShortestRoute(List<List<Step>> steplist, int beginStep) {
        this.steplist = steplist;
        for (int i = 0; i < numberOfSteps; i++) {
            path[i] = Integer.MAX_VALUE;
        }
        stepQueue.add(new Step(beginStep, 0));
        path[beginStep] = 0;
        while (processedSteps.size() != numberOfSteps) {
            System.out.println(stepQueue);
            int closestStep = stepQueue.remove().getStepNumber();
            processedSteps.add(closestStep);
            processNeighbourNodes(closestStep);

        }
    }

    private void processNeighbourNodes(int step) {
        int oldStepSize;
        int newStepSize;
        for (int i = 0; i < steplist.get(step).size(); i++) {
            Step currentStep = steplist.get(step).get(i);
            if (!processedSteps.contains(currentStep.getStepNumber())) {
                oldStepSize = currentStep.getStepSize();
                newStepSize = path[step] + oldStepSize;
                if (newStepSize < path[currentStep.getStepNumber()]) {
                    path[currentStep.getStepNumber()] = newStepSize;
                }
                stepQueue.add(new Step(currentStep.getStepNumber(), path[currentStep.getStepNumber()]));
            }
        }
    }

    public int[] getPath() {
        return path;
    }

    public static void main(String[] args) {
        int numberOfSteps = 7;
        int beginStep = 1;
        int destination = 4;
        List<List<Step>> stepList = new ArrayList<>();
        for (int i = 0; i < numberOfSteps; i++) {
            List<Step> item = new ArrayList<>();
            stepList.add(item);
        }
        Drive drive1 = new Drive(0.8f, 10);
        Drive drive2 = new Drive(1.3f, 20);
        Drive drive3 = new Drive(0.2f, 3);
        Drive drive4 = new Drive(1.0f, 100);
        Drive drive5 = new Drive(1.3f, 35);
        Drive drive6 = new Drive(1.7f, 12);
        drive1.setStepNumber(1);
        drive2.setStepNumber(2);
        drive3.setStepNumber(3);
        drive4.setStepNumber(4);
        drive5.setStepNumber(5);
        drive6.setStepNumber(6);
        stepList.get(0).add(drive1);
        stepList.get(1).add(drive2);
        stepList.get(1).add(drive3);
        stepList.get(2).add(drive4);
        stepList.get(3).add(drive5);
        stepList.get(4).add(drive6);
        stepList.get(5).add(drive6);
        Trip trip = new Trip(numberOfSteps);
        trip.getShortestRoute(stepList, beginStep);
        System.out.println("Het kortste pad vanaf punt: ");
        System.out.println(beginStep + " naar " + destination + " is " + trip.getPath()[destination]);
    }

}


