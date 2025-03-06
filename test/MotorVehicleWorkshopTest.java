import models.Car;
import models.entities.CarWorkshop;
import models.entities.Volvo240;
import app.InMemoryEntityRepository;
import app.ModelFacade;
import app.Vec2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MotorVehicleWorkshopTest {
    private static final int MAX_CARS = 3;
    private CarWorkshop<Car> workshop;
    private CarWorkshop<Volvo240> volvoWorkshop;
    ModelFacade model = new ModelFacade(new InMemoryEntityRepository(5), Vec2.ZERO);

    @BeforeEach
    public void setUp() {
        workshop = new CarWorkshop<>(Car.class, model, MAX_CARS);
        volvoWorkshop = new CarWorkshop<>(Volvo240.class, model, MAX_CARS);
    }

    @Test
    public void testSubmitCar() {
        Volvo240 car = model.getFactory().createVolvo();
        workshop.submitCar(car);
        assertTrue(workshop.hasCar(car));
        workshop.retrieveCar(car);
        assertFalse(workshop.hasCar(car));

        workshop.submitCar(car);
        assertThrows(IllegalArgumentException.class, () -> workshop.submitCar(car));
    }

    @Test
    public void testWorkshopFullException() {
        assertThrows(IllegalStateException.class, () -> {
            for (int i = 0; i <= MAX_CARS; i++) {
                workshop.submitCar(model.getFactory().createVolvo());
            }
        });
    }

    @Test
    public void testPassengerCarWorkshop() {
         volvoWorkshop.submitCar(model.getFactory().createVolvo());
         // volvoWorkshop.submitCar(new entities.Saab95());
    }

    @Test
    public void testRetrieveNonSubmittedCarWorkshop() {
        Volvo240 car = model.getFactory().createVolvo();
        assertThrows(IllegalArgumentException.class, () -> workshop.retrieveCar(car));
    }
}