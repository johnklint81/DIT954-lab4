import entities.CarTransporter;
import entities.MotorVehicle;
import entities.Saab95;
import entities.Volvo240;
import mvc.InMemoryEntityRepository;
import mvc.ModelFacade;
import mvc.Vec2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class MotorVehicleTransporterTest {
    private CarTransporter transporter;
    private Volvo240 volvo;
    private Saab95 saab;
    ModelFacade model = new ModelFacade(new InMemoryEntityRepository(5), Vec2.ZERO);

    @BeforeEach
    void setUp() {
        transporter = new CarTransporter(model, 4);
        volvo = model.getFactory().createVolvo();
        saab = model.getFactory().createSaab();
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
        transporter.gas(0.5);
        assertEquals(transporter.getCurrentSpeed(), 0);
    }

    @Test
    void testLoadCarWhenClose() {
        transporter.lowerRamp();
        transporter.loadCar(volvo);
        assertEquals(transporter.getPos().x(), volvo.getPos().x(), 0.001);
        assertEquals(transporter.getPos().y(), volvo.getPos().y(), 0.001);
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
            transporter.loadCar(model.getFactory().createVolvo());
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
        
        assertEquals(transporter.getPos().x(), volvo.getPos().x(), 0.001);
        assertEquals(transporter.getPos().y(), volvo.getPos().y(), 0.001);
    }
}