import java.awt.*;

public class Saab95 extends Car {

    public boolean turboOn;

    protected Saab95(Color color) {
        super(color);
        nrDoors = 2;
        enginePower = 125;
        turboOn = false;
        modelName = "Saab95";
        stopEngine();
    }

    public void setTurboOn() {
        turboOn = true;
    }

    public void setTurboOff() {
        turboOn = false;
    }
    @Override
    public double speedFactor() {
        double turbo = 1;
        if (turboOn) turbo = 1.3;
        return enginePower * 0.01 * turbo;
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
