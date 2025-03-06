import models.entities.Volvo240;
import app.InMemoryEntityRepository;
import app.ModelFacade;
import app.Vec2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Volvo240Test {
    ModelFacade model = new ModelFacade(new InMemoryEntityRepository(5), Vec2.ZERO);
    Volvo240 volvo240;

    @BeforeEach
    void before() {
        volvo240 = model.getFactory().createVolvo();
    }

    @Test
    void speedFactor() {
        double speedValue = Volvo240.trimFactor * volvo240.getEnginePower() * 0.01;
        assertEquals(speedValue, volvo240.speedFactor());
    }

    @Test
    void gas() {
        double amount = 0.9;
        double currentSpeed = volvo240.getCurrentSpeed();
        volvo240.gas(amount);
        double newSpeed = volvo240.getCurrentSpeed();
        assertTrue(currentSpeed <= newSpeed);
        assertThrows(IllegalArgumentException.class, () -> volvo240.gas(1.5));
        assertThrows(IllegalArgumentException.class, () -> volvo240.gas(-1.5));
    }

    @Test
    void brake() {
        double amount = 0.9;
        double currentSpeed = volvo240.getCurrentSpeed();
        volvo240.brake(amount);
        double newSpeed = volvo240.getCurrentSpeed();

        assertTrue(currentSpeed >= newSpeed);

        assertThrows(IllegalArgumentException.class, () -> volvo240.brake(1.5));
        assertThrows(IllegalArgumentException.class, () -> volvo240.brake(-1.5));
    }
    @Test
    void getModelName() {
        assertEquals("entities.Volvo240", volvo240.getModelName());
    }

}