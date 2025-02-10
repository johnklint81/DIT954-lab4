import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class CarTransporterTest {
    private CarTransporter transporter;
    private Volvo240 volvo;
    private Saab95 saab;

    @BeforeEach
    void setUp() {
        transporter = new CarTransporter();
        volvo = new Volvo240();
        saab = new Saab95();
    }

    @Test
    void testLowerRampWhenStationary() {
        transporter.lowerRamp();
        assertTrue(transporter.isRampDown());
    }

    @Test
    void testCannotLowerRampWhileMoving() {
        transporter.startEngine();
        assertThrows(IllegalStateException.class, () -> transporter.lowerRamp());
    }

    @Test
    void testCannotMoveWithRampDown() {
        transporter.lowerRamp();
        assertThrows(IllegalStateException.class, () -> transporter.gas(0.5));
    }

    @Test
    void testLoadCarWhenClose() {
        transporter.lowerRamp();
        assertTrue(transporter.loadCar(volvo));
        assertEquals(transporter.getCurrentPosition()[0], volvo.getCurrentPosition()[0], 0.001);
        assertEquals(transporter.getCurrentPosition()[1], volvo.getCurrentPosition()[1], 0.001);
    }

    @Test
    void testCannotLoadCarWhenRampUp() {
        assertThrows(IllegalStateException.class, () -> transporter.loadCar(volvo));
    }

    @Test
    void testCannotLoadWhenFull() {
        transporter.lowerRamp();
        // Load max number of cars
        for (int i = 0; i < 4; i++) {
            transporter.loadCar(new Volvo240());
        }
        assertFalse(transporter.loadCar(volvo));
    }

    @Test
    void testUnloadCarLastInFirstOut() {
        transporter.lowerRamp();
        transporter.loadCar(volvo);
        transporter.loadCar(saab);
        
        Car unloadedCar = transporter.unloadCar();
        assertEquals(saab, unloadedCar);
        
        unloadedCar = transporter.unloadCar();
        assertEquals(volvo, unloadedCar);
    }

    @Test
    void testCannotUnloadWhenRampUp() {
        transporter.lowerRamp();
        transporter.loadCar(volvo);
        transporter.raiseRamp();
        
        assertThrows(IllegalStateException.class, () -> transporter.unloadCar());
    }

    @Test
    void testLoadedCarsFollowTransporter() {
        transporter.lowerRamp();
        transporter.loadCar(volvo);
        transporter.raiseRamp();
        
        transporter.gas(0.5);
        transporter.move();
        
        assertEquals(transporter.getCurrentPosition()[0], volvo.getCurrentPosition()[0], 0.001);
        assertEquals(transporter.getCurrentPosition()[1], volvo.getCurrentPosition()[1], 0.001);
    }

    @Test
    void testCannotLoadTransporterOnTransporter() {
        CarTransporter anotherTransporter = new CarTransporter();
        transporter.lowerRamp();
        assertFalse(transporter.loadCar(anotherTransporter));
    }
} 