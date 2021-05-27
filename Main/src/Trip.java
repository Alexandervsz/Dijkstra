import java.util.*;

import static java.util.Collections.min;

public class Trip implements Comparable {

    private final Step[] path;
    private final int numberOfSteps;
    private final int destination;
    private final Set<Step> processedSteps;
    private final PriorityQueue<Step> stepQueue;
    private List<List<Step>> steplist;

    public Trip(int numberOfSteps, int destination) {
        this.numberOfSteps = numberOfSteps;
        this.destination = destination;
        path = new Step[numberOfSteps];
        processedSteps = new HashSet<>();
        stepQueue = new PriorityQueue<>(numberOfSteps, new Step());
    }

    public void getShortestRoute(List<List<Step>> steplist, Step beginStep) {
        /*
        Checks the first element of the queue and calculates the distance to it's neighbours
         */
        this.steplist = steplist;
        for (int i = 0; i < numberOfSteps; i++) {
            path[i] = new Step(i, Integer.MAX_VALUE);
        }
        stepQueue.add(beginStep);
        path[0] = beginStep;
        while (processedSteps.size() != numberOfSteps) {
            Step closestStep = stepQueue.remove();
            processedSteps.add(closestStep);
            processNeighbourSteps(closestStep);

        }

    }

    private void processNeighbourSteps(Step step) {
        /*
        calculates the new step size of the neighbours of the given step, then adds the current step back to the queue.
         */
        int oldStepSize;
        int newStepSize;

        for (int i = 0; i < steplist.get(step.getStepNumber()).size(); i++) {
            Step currentStep = steplist.get(step.getStepNumber()).get(i);
            if (!processedSteps.contains(currentStep)) {
                oldStepSize = currentStep.getStepSize();
                newStepSize = path[step.getStepNumber()].getStepSize() + oldStepSize;
                if (newStepSize < path[currentStep.getStepNumber()].getStepSize()) {
                    path[currentStep.getStepNumber()].setStepSize(newStepSize);
                }
                stepQueue.add(currentStep);
            }
        }
    }

    public int getPathLength() {
        return path[destination].getStepSize();
    }

    public Step[] getPath() {
        return path;
    }

    public static List<List<Step>> createEmptyList(int numberOfSteps){
        /*
        Creates  the layers of the map.
         */
        List<List<Step>> stepList = new ArrayList<>();
        for (int i = 0; i < numberOfSteps; i++) {
            List<Step> item = new ArrayList<>();
            stepList.add(item);
        }
        return stepList;
    }


    public static Trip testMapDrive(int destination, int numberOfSteps) {
        /*
        Runs some sample data for the drive functionality.
         */
        List<List<Step>> stepList = createEmptyList(numberOfSteps);
        Drive drive0 = new Drive(0, 0, 0);
        Drive drive1 = new Drive(0.8f, 10, 1);
        Drive drive2 = new Drive(1.3f, 20, 2);
        Drive drive3 = new Drive(0.2f, 3, 3);
        Drive drive4 = new Drive(1.0f, 100, 4);
        Drive drive5 = new Drive(1.3f, 35, 5);
        Drive drive6 = new Drive(1.7f, 12, 6);
        stepList.get(0).add(drive1);
        stepList.get(1).add(drive2);
        stepList.get(1).add(drive3);
        stepList.get(2).add(drive4);
        stepList.get(3).add(drive5);
        stepList.get(4).add(drive6);
        stepList.get(5).add(drive6);
        Trip trip = new Trip(numberOfSteps, destination);
        trip.getShortestRoute(stepList, drive0);
        System.out.println("Het kortste pad vanaf punt: ");
        System.out.println(trip.getPath()[0].getStepNumber() + " naar " + destination + " kost €" + trip.getPathLength());
        return trip;
    }

    public static Trip testMapFlight(int destination, int numberOfSteps) {
        /*
        Runs some sample data for the flight functionality.
         */
        List<List<Step>> stepList = createEmptyList(numberOfSteps);
        Flight flight0 = new Flight(0, 0, 0);
        Flight flight1 = new Flight(199.99f, 0.001f, 1);
        Flight flight2 = new Flight(99.99f, 0.01f, 2);
        Flight flight3 = new Flight(3.14f, 0.1f, 3);
        Flight flight4 = new Flight(50.00f, 0.25f, 4);
        Flight flight5 = new Flight(74.99f, 0.12f, 5);
        Flight flight6 = new Flight(99.99f, 0.015f, 6);
        stepList.get(0).add(flight1);
        stepList.get(1).add(flight2);
        stepList.get(1).add(flight3);
        stepList.get(2).add(flight4);
        stepList.get(3).add(flight5);
        stepList.get(4).add(flight6);
        stepList.get(5).add(flight6);
        Trip trip = new Trip(numberOfSteps, destination);
        trip.getShortestRoute(stepList, flight0);
        System.out.println("De goedkoopste vlucht vanaf punt: ");
        System.out.println(trip.getPath()[0].getStepNumber() + " naar " + destination + " kost €" + trip.getPathLength() + "");
        return trip;
    }

    public static Trip testMapTrain(int destination, int numberOfSteps) {
        /*
        Runs some sample data for the trainride functionality.
         */
        List<List<Step>> stepList = createEmptyList(numberOfSteps);
        TrainRide trainRide0 = new TrainRide(60, 0);
        TrainRide trainRide1 = new TrainRide(15, 1);
        TrainRide trainRide2 = new TrainRide(20, 2);
        TrainRide trainRide3 = new TrainRide(120, 3);
        TrainRide trainRide4 = new TrainRide(18, 4);
        TrainRide trainRide5 = new TrainRide(5, 5);
        TrainRide trainRide6 = new TrainRide(3, 6);
        stepList.get(0).add(trainRide1);
        stepList.get(1).add(trainRide2);
        stepList.get(1).add(trainRide3);
        stepList.get(2).add(trainRide4);
        stepList.get(3).add(trainRide5);
        stepList.get(4).add(trainRide6);
        stepList.get(5).add(trainRide6);
        Trip trip = new Trip(numberOfSteps, destination);
        trip.getShortestRoute(stepList, trainRide0);
        System.out.println("Het snelste rit vanaf punt: ");
        System.out.println(trip.getPath()[0].getStepNumber() + " naar " + destination + " kost €" + trip.getPathLength() + "");
        return trip;
    }

    public static void main(String[] args) {
        int endNode = 4;
        int numberOfSteps = 7;
        Trip trip1 = testMapDrive(endNode,numberOfSteps);
        Trip trip2 = testMapFlight(endNode, numberOfSteps);
        Trip trip3 = testMapTrain(endNode, numberOfSteps);
        List<Integer> scores = new ArrayList<>();
        scores.add(trip1.compareTo(trip2) + trip1.compareTo(trip3));
        scores.add(trip2.compareTo(trip1) + trip2.compareTo(trip3));
        scores.add(trip3.compareTo(trip1) + trip3.compareTo(trip3));
        int min = min(scores);
        switch (scores.indexOf(min)) {
            case 0 -> System.out.println("De auto is het goedkoopst.");
            case 1 -> System.out.println("Het vliegtuig is het goedkoopst.");
            case 2 -> System.out.println("De trein is het goedkoopst.");
        }
    }

    @Override
    public int compareTo(Trip trip) {
        return Integer.compare(this.getPathLength(), trip.getPathLength());
    }
}


