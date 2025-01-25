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

    // TODO fix this method according to lab pm
    public void gas(double amount) {
        incrementSpeed(amount);
    }

    // TODO fix this method according to lab pm
    public void brake(double amount) {
        decrementSpeed(amount);
    }
}
