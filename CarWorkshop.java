import java.util.*;

public class CarWorkshop<T extends Car> extends Entity {
    private final ArrayList<T> cars;
    private final int maxCars;
    private final Vec2 pos;
    public static final Vec2 SIZE = new Vec2(101, 96);

    public CarWorkshop(ModelFacade model, int maxCars) {
        this(model, maxCars, new Vec2(0, 0));
    }

    public CarWorkshop(ModelFacade model, int maxCars, Vec2 pos) {
        super(model, pos, SIZE);
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

    public void tick() {
        for (Entity vehicle : model.entityRepository) {
            if (vehicle instanceof Volvo240) {
                // Safe cast because T extends Car
                T car = (T) vehicle;
                if (CollisionChecker.collides(this, car)) {
                    car.setInWorkshop(true);
                    car.setPos(pos);
                }
            }
        }
    }

    public boolean hasCar(T car) {
        return cars.contains(car);
    }
}
