public class TrainRide extends Step {
    public TrainRide(float pricePerMinute, float time) {
        this.setStepSize(Math.round(pricePerMinute * time));
    }
}
