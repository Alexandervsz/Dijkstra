public class Flight extends Step {
    public Flight(float flightPrice, float luggageLoss, int stepNumber) {
        this.setStepSize(Math.round(flightPrice * luggageLoss));
        this.setStepNumber(stepNumber);
    }
}
