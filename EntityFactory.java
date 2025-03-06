import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class EntityFactory {
    private final ModelFacade model;
    private final List<Class<? extends MotorVehicle>> vehicles = new ArrayList<>();

    EntityFactory(ModelFacade model) {
        vehicles.add(Volvo240.class);
        vehicles.add(Saab95.class);
        vehicles.add(Scania.class);
        this.model = model;
    }

    Volvo240 createVolvo() {
        return new Volvo240(model);
    }

    Saab95 createSaab() {
        return new Saab95(model);
    }

    Scania createScania() {
        return new Scania(model);
    }

    <T extends Car> CarWorkshop<T> createWorkshop(Class<T> acceptedCar, int maxCars) {
        return new CarWorkshop<>(acceptedCar, model, maxCars);
    }

    public MotorVehicle createRandomMotorVehicle(int offset) {
        int i = ThreadLocalRandom.current().nextInt(vehicles.size());
        try {
            var car = vehicles.get(i).getDeclaredConstructor(ModelFacade.class).newInstance(model);
            car.setPos(car.getPos().add(0, offset));
            return car;
        } catch (Exception e) {
            return null;
        }
    }
}
