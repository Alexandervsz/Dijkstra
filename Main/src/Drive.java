public class Drive extends Step {
    public Drive(float pricePerKilometer, float kilometers) {
        this.setStepSize(Math.round(pricePerKilometer * kilometers));
    }
}
