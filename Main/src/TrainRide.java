public class TrainRide extends Step {
    public TrainRide(float pricePerMinute, float time, int stepNumber) {
        this.setStepSize(Math.round(pricePerMinute * time));
        this.setStepNumber(stepNumber);
    }
}
