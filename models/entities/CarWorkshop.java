package models.entities;

import models.Car;
import models.Entity;
import app.*;

import java.util.*;

public class CarWorkshop<T extends Car> extends Entity {
    private final ArrayList<T> cars;
    private final int maxCars;
    public static final Vec2 SIZE = new Vec2(101, 96);
    private final Class <T> acceptedCar;

    public CarWorkshop(Class <T> acceptedCar, ModelFacade model, int maxCars) {
        super(model, Vec2.ZERO, SIZE);
        this.cars = new ArrayList<>();
        this.maxCars = maxCars;
        this.acceptedCar = acceptedCar;
    }

    public void submitCar(T car) {
        if (cars.size() >= maxCars) {
            throw new IllegalStateException("The workshop is full");
        }

        if (cars.contains(car)) {
            throw new IllegalArgumentException("You can't submit a car that's already in the workshop");
        }

        car.setInWorkshop(true);
        cars.add(car);
    }

    public void retrieveCar(T car) {
        if (!cars.contains(car)) {
            throw new IllegalArgumentException("You can't retrieve a car that's not in the workshop");
        }

        car.setInWorkshop(false);
        cars.remove(car);
    }

    public void tick() {
        for (Entity vehicle : model.getRepository()) {
            if (vehicle.getClass() == acceptedCar) {
                // Safe cast because T extends entities.Car
                T car = (T) vehicle;
                if (CollisionChecker.collides(this, car)) {
                    car.setInWorkshop(true);
                    car.setPos(getPos());
                }
            }
        }
    }

    public boolean hasCar(T car) {
        return cars.contains(car);
    }
}
