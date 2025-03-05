import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class MotorVehicleTransporterTest {
    private CarTransporter transporter;
    private Volvo240 volvo;
    private Saab95 saab;
    ModelFacade model = new ModelFacade(Vec2.ZERO);

    @BeforeEach
    void setUp() {
        transporter = new CarTransporter(model, 4);
        volvo = new Volvo240(model);
        saab = new Saab95(model);
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
        transporter.loadCar(volvo);
        assertEquals(transporter.getPos().getX(), volvo.getPos().getX(), 0.001);
        assertEquals(transporter.getPos().getY(), volvo.getPos().getY(), 0.001);
    }

    @Test
    void testCannotLoadCarWhenRampUp() {
        assertThrows(IllegalStateException.class, () -> transporter.loadCar(volvo));
    }

    @Test
    void testCannotLoadWhenFull() {
        transporter.lowerRamp();
        // Load max number of entityRepository
        for (int i = 0; i < 4; i++) {
            transporter.loadCar(new Volvo240(model));
        }
        assertThrows(IllegalStateException.class, () -> transporter.loadCar(volvo));
    }

    @Test
    void testUnloadCarLastInFirstOut() {
        transporter.lowerRamp();
        transporter.loadCar(volvo);
        transporter.loadCar(saab);
        
        MotorVehicle unloadedCar = transporter.unloadCar();
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
        transporter.tick();
        
        assertEquals(transporter.getPos().getX(), volvo.getPos().getX(), 0.001);
        assertEquals(transporter.getPos().getY(), volvo.getPos().getY(), 0.001);
    }
}