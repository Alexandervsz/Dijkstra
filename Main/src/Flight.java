public class Flight extends Step {
    public Flight(float flightPrice, float luggageLoss) {
        this.setStepSize(Math.round(flightPrice * luggageLoss));
    }
}
