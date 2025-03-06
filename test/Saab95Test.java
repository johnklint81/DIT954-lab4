import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class Saab95Test {
    ModelFacade model = new ModelFacade(new InMemoryEntityRepository(5), Vec2.ZERO);
    Saab95 saab95;

    @BeforeEach
    void before() {
        saab95 = new Saab95(model);
    }

    @Test
    void setTurboOn() {
        saab95.setTurboOn();
        assertTrue(saab95.getTurboOn());
    }

    @Test
    void setTurboOff() {
        saab95.setTurboOn();
        saab95.setTurboOff();
        assertFalse(saab95.getTurboOn());
    }

    @Test
    void speedFactor() {
        saab95.setTurboOn();
        double turboOnValue = 1.3 * saab95.getEnginePower() * 0.01;
        assertEquals(turboOnValue, saab95.speedFactor());
        saab95.setTurboOff();
        double turboOffValue = 1.0 * saab95.getEnginePower() * 0.01;
        assertEquals(turboOffValue, saab95.speedFactor());
    }

    @Test
    void gas() {
        double amount = 0.9;
        double currentSpeed = saab95.getCurrentSpeed();
        saab95.gas(amount);
        double newSpeed = saab95.getCurrentSpeed();
        assertTrue(currentSpeed <= newSpeed);
        assertThrows(IllegalArgumentException.class, () -> saab95.gas(1.5));
        assertThrows(IllegalArgumentException.class, () -> saab95.gas(-1.5));
    }

    @Test
    void brake() {
        double amount = 0.9;
        double currentSpeed = saab95.getCurrentSpeed();
        saab95.brake(amount);
        double newSpeed = saab95.getCurrentSpeed();
        assertTrue(currentSpeed >= newSpeed);

        assertThrows(IllegalArgumentException.class, () -> saab95.brake(1.5));
        assertThrows(IllegalArgumentException.class, () -> saab95.brake(-1.5));
    }
    @Test
    void getModelName() {
        assertEquals("Saab95", saab95.getModelName());
    }

}