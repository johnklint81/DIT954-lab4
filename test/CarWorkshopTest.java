import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarWorkshopTest {
    private CarWorkshop<Car> workshop;
    private final int MAX_CARS = 3;

    @BeforeEach
    public void setUp() {
        workshop = new CarWorkshop<>(MAX_CARS);
    }

    @Test
    public void testSubmitCar() {
        Volvo240 car = new Volvo240();
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
                workshop.submitCar(new Volvo240());
            }
        });
    }

    @Test
    public void testRetrieveNonSubmittedCarWorkshop() {
        Volvo240 car = new Volvo240();
        assertThrows(IllegalArgumentException.class, () -> workshop.retrieveCar(car));
    }
}