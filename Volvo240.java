import java.awt.*;

public class Volvo240 extends Car {

    public final static double trimFactor = 1.25;

    protected Volvo240(Color color) {
        super(color);
        nrDoors = 4;
        enginePower = 100;
        modelName = "Volvo240";
        stopEngine();
    }

    @Override
    protected double speedFactor() {
        return enginePower * 0.01 * trimFactor;
    }

    public void gas(double amount) {
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("gas amount must be in interval [0, 1].");
        }
        incrementSpeed(amount);
    }

    public void brake(double amount) {
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("brake amount must be in interval [0, 1].");
        }
        decrementSpeed(amount);
    }
}
