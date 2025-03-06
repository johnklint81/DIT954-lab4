import models.MotorVehicle;
import models.entities.Saab95;
import mvc.InMemoryEntityRepository;
import mvc.ModelFacade;
import mvc.Vec2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;

class MotorVehicleTest {
    ModelFacade model = new ModelFacade(new InMemoryEntityRepository(5), Vec2.ZERO);
    Saab95 saab;

    @BeforeEach
    void before() {
        saab = model.getFactory().createSaab();
    }

    @Test
    void move() {
        double initialX = saab.getPos().x();
        double initialY = saab.getPos().y();
        saab.tick();
        assertEquals(initialX, saab.getPos().x());
        assertEquals(initialY, saab.getPos().y());
    }

    @Test
    void turnLeft() {
        double rad = Math.PI / 2;
        saab.turnLeft(rad);
        assertEquals(Math.PI / 2, saab.getCurrentDirection());
    }

    @Test
    void turnRight() {
        double rad = Math.PI / 2;
        saab.turnRight(rad);
        assertEquals(-Math.PI / 2, saab.getCurrentDirection());
    }

    @Test
    void getNrDoors() {
        assertEquals(2, saab.getNrDoors());
    }

    @Test
    void getEnginePower() {
        assertEquals(125, saab.getEnginePower());
    }

    @Test
    void getCurrentSpeed() {
        assertEquals(0, saab.getCurrentSpeed());
    }

    @Test
    void getCurrentDirection() {
        assertEquals(0, saab.getCurrentDirection());
    }

    @Test
    void getCurrentPosition() {
        assertEquals(0, saab.getPos().x());
        assertEquals(0, saab.getPos().y());
    }

    @Test
    void getColor() {
        assertEquals(Color.BLACK, saab.getColor());
    }

    @Test
    void setColor() {
        saab.setColor(Color.GREEN);
        assertEquals(Color.GREEN, saab.getColor());
    }

    @Test
    void startEngine() {
        saab.startEngine();
        assertEquals(0.1, saab.getCurrentSpeed());
    }

    @Test
    void stopEngine() {
        saab.stopEngine();
        assertEquals(0, saab.getCurrentSpeed());
    }

    @Test
    void speedFactor() {
        // enginePower = 125, turbo = off,
        // speed is 125 * 0.01 * 1 = 1.25
        assertEquals(1.25, saab.speedFactor());
        MotorVehicle volvo = model.getFactory().createVolvo();
        // enginePower = 100, trimFactor = 1.25
        // speed is 100 * 0.01 * 1.25 = 1.25
        assertEquals(1.25, volvo.speedFactor());
    }
}