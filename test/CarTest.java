import org.junit.jupiter.api.Test;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    void move() {
        Car saab = new Saab95(Color.BLACK);
        double initialX = saab.getCurrentPosition()[0];
        double initialY = saab.getCurrentPosition()[1];
        saab.move();
        assertEquals(initialX, saab.getCurrentPosition()[0]);
        assertEquals(initialY, saab.getCurrentPosition()[1]);
    }

    @Test
    void turnLeft() {
        Car saab = new Saab95(Color.BLACK);
        double rad = Math.PI / 2;
        saab.turnLeft(rad);
        assertEquals(Math.PI / 2, saab.getCurrentDirection());
    }

    @Test
    void turnRight() {
        Car saab = new Saab95(Color.BLACK);
        double rad = Math.PI / 2;
        saab.turnRight(rad);
        assertEquals(-Math.PI / 2, saab.getCurrentDirection());
    }

    @Test
    void getNrDoors() {
        Car saab = new Saab95(Color.BLACK);
        assertEquals(2, saab.getNrDoors());
    }

    @Test
    void getEnginePower() {
        Car saab = new Saab95(Color.BLACK);
        assertEquals(125, saab.getEnginePower());
    }

    @Test
    void getCurrentSpeed() {
        Car saab = new Saab95(Color.BLACK);
        assertEquals(0, saab.getCurrentSpeed());
    }

    @Test
    void getCurrentDirection() {
        Car saab = new Saab95(Color.BLACK);
        assertEquals(0, saab.getCurrentDirection());
    }

    @Test
    void getCurrentPosition() {
        Car saab = new Saab95(Color.BLACK);
        assertEquals(0, saab.getCurrentPosition()[0]);
        assertEquals(0, saab.getCurrentPosition()[1]);
    }

    @Test
    void getColor() {
        Car saab = new Saab95(Color.BLACK);
        assertEquals(Color.BLACK, saab.getColor());
    }

    @Test
    void setColor() {
        Car saab = new Saab95(Color.RED);
        saab.setColor(Color.GREEN);
        assertEquals(Color.GREEN, saab.getColor());
    }

    @Test
    void startEngine() {
        Car saab = new Saab95(Color.BLACK);
        saab.startEngine();
        assertEquals(0.1, saab.currentSpeed);
    }

    @Test
    void stopEngine() {
        Car saab = new Saab95(Color.BLUE);
        saab.stopEngine();
        assertEquals(0, saab.currentSpeed);
    }

    @Test
    void speedFactor() {
        // Create an anonymous subclass just for testing.
        // Allows us to instantiate an abstract subclass.
        Car car = new Car(Color.BLACK) {};
        assertEquals(1, car.speedFactor());
        Car saab = new Saab95(Color.BLACK);
        // enginePower = 125, turbo = off,
        // speed is 125 * 0.01 * 1 = 1.25
        assertEquals(1.25, saab.speedFactor());
        Car volvo = new Volvo240(Color.RED);
        // enginePower = 100, trimFactor = 1.25
        // speed is 100 * 0.01 * 1.25 = 1.25
        assertEquals(1.25, volvo.speedFactor());
    }

    @Test
    void incrementSpeed() {
        Car saab = new Saab95(Color.BLACK);
        saab.incrementSpeed(1000);
        System.out.println(saab.getCurrentSpeed());
        assertEquals(saab.getEnginePower(), saab.getCurrentSpeed());

        Car volvo = new Volvo240(Color.GRAY);
        volvo.incrementSpeed(1000);
        System.out.println(volvo.getCurrentSpeed());
        assertEquals(volvo.getEnginePower(), volvo.getCurrentSpeed());
    }

    @Test
    void decrementSpeed() {
        Car saab = new Saab95(Color.BLACK);
        saab.decrementSpeed(1000);
        System.out.println(saab.getCurrentSpeed());
        assertEquals(0, saab.getCurrentSpeed());

        Car volvo = new Volvo240(Color.BLACK);
        volvo.decrementSpeed(1000);
        System.out.println(volvo.getCurrentSpeed());
        assertEquals(0, volvo.getCurrentSpeed());
    }
}