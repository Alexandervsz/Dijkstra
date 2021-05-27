public class Drive extends Step {
    public Drive(float pricePerKilometer, float kilometers, int stepNumber) {
        this.setStepSize(Math.round(pricePerKilometer * kilometers));
        this.setStepNumber(stepNumber);
    }
}
