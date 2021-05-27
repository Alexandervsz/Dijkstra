public class TrainRide extends Step {
    public TrainRide(float time, int stepNumber) {
        this.setStepSize(Math.round(time/3));
        this.setStepNumber(stepNumber);
    }
}
