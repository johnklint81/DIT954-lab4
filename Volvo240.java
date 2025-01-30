import java.awt.*;

public class Volvo240 extends Car {

    public final static double trimFactor = 1.25;

    protected Volvo240() {
        super(4, 100, Color.RED, "Volvo240");
    }

    @Override
    protected double speedFactor() {
        return getEnginePower() * 0.01 * trimFactor;
    }

}
