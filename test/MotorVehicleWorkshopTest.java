import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MotorVehicleWorkshopTest {
    private static final int MAX_CARS = 3;
    private CarWorkshop<Car> workshop;
    private CarWorkshop<Volvo240> volvoWorkshop;
    ModelFacade model = new ModelFacade(Vec2.ZERO);

    @BeforeEach
    public void setUp() {
        workshop = new CarWorkshop<>(model, MAX_CARS);
        volvoWorkshop = new CarWorkshop<>(model, MAX_CARS);
    }

    @Test
    public void testSubmitCar() {
        Volvo240 car = new Volvo240(model);
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
                workshop.submitCar(new Volvo240(model));
            }
        });
    }

    @Test
    public void testPassengerCarWorkshop() {
         volvoWorkshop.submitCar(new Volvo240(model));
         // volvoWorkshop.submitCar(new Saab95());
    }

    @Test
    public void testRetrieveNonSubmittedCarWorkshop() {
        Volvo240 car = new Volvo240(model);
        assertThrows(IllegalArgumentException.class, () -> workshop.retrieveCar(car));
    }
}