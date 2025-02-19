import java.util.*;

public class CarWorkshop<T extends Car> {
    private final ArrayList<T> cars;
    private final int maxCars;
    private final Vec2 pos;
    public static final Vec2 SIZE = new Vec2(101, 96);

    public CarWorkshop(int maxCars, Vec2 pos) {
        this.cars = new ArrayList<>();
        this.maxCars = maxCars;
        this.pos = pos;
    }

    public void submitCar(T car) {
        if (cars.size() >= maxCars) {
            throw new IllegalStateException("The workshop is full");
        }

        if (cars.contains(car)) {
            throw new IllegalArgumentException("You can't submit a car that's already in the workshop");
        }

        car.setInWorkshop(true);
        car.setPos(pos);
        cars.add(car);
    }

    public void retrieveCar(T car) {
        if (!cars.contains(car)) {
            throw new IllegalArgumentException("You can't retrieve a car that's not in the workshop");
        }

        car.setInWorkshop(false);
        cars.remove(car);
    }

    public boolean collidesWith(Car car) {
        var carX = car.getPos().getX();
        var carY = car.getPos().getY();
        var carWidth = CarController.CAR_SIZE.getX();
        var carHeight = CarController.CAR_SIZE.getY();

        var workshopX = pos.getX();
        var workshopY = pos.getY();
        var workshopWidth = CarWorkshop.SIZE.getX();
        var workshopHeight = CarWorkshop.SIZE.getY();

        // Check for collision using Axis-Aligned Bounding Box (AABB)
        boolean xOverlap = carX < workshopX + workshopWidth && carX + carWidth > workshopX;
        boolean yOverlap = carY < workshopY + workshopHeight && carY + carHeight > workshopY;

        return xOverlap && yOverlap;
    }

    public boolean hasCar(T car) {
        return cars.contains(car);
    }
}
