package entities;

import mvc.ModelFacade;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class EntityFactory {
    private final mvc.ModelFacade model;
    private final List<Class<? extends MotorVehicle>> vehicles = new ArrayList<>();

    public EntityFactory(mvc.ModelFacade model) {
        vehicles.add(Volvo240.class);
        vehicles.add(Saab95.class);
        vehicles.add(Scania.class);
        this.model = model;
    }

    public Volvo240 createVolvo() {
        return new Volvo240(model);
    }

    public Saab95 createSaab() {
        return new Saab95(model);
    }

    public Scania createScania() {
        return new Scania(model);
    }

    public <T extends Car> CarWorkshop<T> createWorkshop(Class<T> acceptedCar, int maxCars) {
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
