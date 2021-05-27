import java.util.Comparator;
import java.util.Objects;

public class Step implements Comparator<Step> {
    private int stepSize;
    private int stepNumber;

    public Step() {

    }

    public Step(int stepNumber, int stepSize) {
        this.stepNumber = stepNumber;
        this.stepSize = stepSize;
    }

    public int getStepSize() {
        return stepSize;
    }

    public void setStepSize(int stepSize) {
        this.stepSize = stepSize;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }


    public int getStepNumber() {
        return stepNumber;
    }

    @Override
    public String toString() {
        return "Step{" +
                "stepSize=" + stepSize +
                ", stepNumber=" + stepNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Step step = (Step) o;
        return stepNumber == step.stepNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(stepNumber);
    }

    @Override
    public int compare(Step step1, Step step2) {
        return Integer.compare(step1.getStepSize(), step2.getStepSize());
    }
}
