import org.junit.jupiter.api.Test;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    void move() {
        Car saab = new Saab95();
        double initialX = saab.getCurrentPosition()[0];
        double initialY = saab.getCurrentPosition()[1];
        saab.move();
        assertEquals(initialX, saab.getCurrentPosition()[0]);
        assertEquals(initialY, saab.getCurrentPosition()[1]);
    }

    @Test
    void turnLeft() {
        Car saab = new Saab95();
        double rad = Math.PI / 2;
        saab.turnLeft(rad);
        assertEquals(Math.PI / 2, saab.getCurrentDirection());
    }

    @Test
    void turnRight() {
        Car saab = new Saab95();
        double rad = Math.PI / 2;
        saab.turnRight(rad);
        assertEquals(-Math.PI / 2, saab.getCurrentDirection());
    }

    @Test
    void getNrDoors() {
        Car saab = new Saab95();
        assertEquals(2, saab.getNrDoors());
    }

    @Test
    void getEnginePower() {
        Car saab = new Saab95();
        assertEquals(125, saab.getEnginePower());
    }

    @Test
    void getCurrentSpeed() {
        Car saab = new Saab95();
        assertEquals(0, saab.getCurrentSpeed());
    }

    @Test
    void getCurrentDirection() {
        Car saab = new Saab95();
        assertEquals(0, saab.getCurrentDirection());
    }

    @Test
    void getCurrentPosition() {
        Car saab = new Saab95();
        assertEquals(0, saab.getCurrentPosition()[0]);
        assertEquals(0, saab.getCurrentPosition()[1]);
    }

    @Test
    void getColor() {
        Car saab = new Saab95();
        assertEquals(Color.BLACK, saab.getColor());
    }

    @Test
    void setColor() {
        Car saab = new Saab95();
        saab.setColor(Color.GREEN);
        assertEquals(Color.GREEN, saab.getColor());
    }

    @Test
    void startEngine() {
        Car saab = new Saab95();
        saab.startEngine();
        assertEquals(0.1, saab.getCurrentSpeed());
    }

    @Test
    void stopEngine() {
        Car saab = new Saab95();
        saab.stopEngine();
        assertEquals(0, saab.getCurrentSpeed());
    }

    @Test
    void speedFactor() {
        // Create an anonymous subclass just for testing.
        // Allows us to instantiate an abstract subclass.
        Car saab = new Saab95();
        // enginePower = 125, turbo = off,
        // speed is 125 * 0.01 * 1 = 1.25
        assertEquals(1.25, saab.speedFactor());
        Car volvo = new Volvo240();
        // enginePower = 100, trimFactor = 1.25
        // speed is 100 * 0.01 * 1.25 = 1.25
        assertEquals(1.25, volvo.speedFactor());
    }
}