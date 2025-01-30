import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class Volvo240Test {

    @Test
    void speedFactor() {
        Volvo240 volvo240 = new Volvo240(Color.BLUE);
        double speedValue = Volvo240.trimFactor * volvo240.enginePower * 0.01;
        assertEquals(speedValue, volvo240.speedFactor());
    }

    @Test
    void gas() {
        Volvo240 volvo240 = new Volvo240(Color.BLUE);
        double amount = 0.9;
        double currentSpeed = volvo240.getCurrentSpeed();
        volvo240.gas(amount);
        double newSpeed = volvo240.getCurrentSpeed();
        assertTrue(currentSpeed <= newSpeed);
        assertThrows(IllegalArgumentException.class, () -> {
            volvo240.gas(1.5);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            volvo240.gas(-1.5);
        });
    }

    @Test
    void brake() {
        Volvo240 volvo240 = new Volvo240(Color.BLUE);
        double amount = 0.9;
        double currentSpeed = volvo240.getCurrentSpeed();
        volvo240.brake(amount);
        double newSpeed = volvo240.getCurrentSpeed();

        assertTrue(currentSpeed >= newSpeed);

        assertThrows(IllegalArgumentException.class, () -> {
            volvo240.brake(1.5);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            volvo240.brake(-1.5);
        });
    }
}