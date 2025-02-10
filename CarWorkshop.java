import java.util.*;

public class CarWorkshop<T extends Car> {
    private final ArrayList<T> cars;
    private final int maxCars;

    public CarWorkshop(int maxCars) {
        this.cars = new ArrayList<>();
        this.maxCars = maxCars;
    }

    public void submitCar(T car) {
        if (cars.size() >= maxCars) {
            throw new IllegalStateException("The workshop is full");
        }

        if (cars.contains(car)) {
            throw new IllegalArgumentException("You can't submit a car that's already in the workshop");
        }

        cars.add(car);
    }

    public void retrieveCar(T car) {
        if (!cars.contains(car)) {
            throw new IllegalArgumentException("You can't retrieve a car that's not in the workshop");
        }

        cars.remove(car);
    }

    public boolean hasCar(T car) {
        return cars.contains(car);
    }
}
