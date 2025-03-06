import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class CarTransporter extends Truck {
  private static final double LOADING_DISTANCE = 1.0;

  private final Ramp ramp;
  private final Deque<Car> loadedCars;
  private final int maxCars;

  public CarTransporter(ModelFacade model, int maxCars) {
    super(model, 2, 200, Color.BLUE, "CarTransporter");
    this.ramp = new Ramp();
    this.loadedCars = new ArrayDeque<>();
    this.maxCars = maxCars;
  }

  @Override
  protected double speedFactor() {
    return getEnginePower() * 0.005;
  }

  @Override
  public boolean canMove() {
    return !ramp.isDown();
  }

  public void lowerRamp() {
    if (getCurrentSpeed() > 0) {
      throw new IllegalStateException("Cannot lower ramp while moving");
    }
    ramp.lower();
  }

  public void raiseRamp() {
    ramp.raise();
  }

  public boolean isRampDown() {
    return ramp.isDown();
  }

  private boolean isCloseEnough(Car car) {
    return getPos().distanceTo(car.getPos()) <= LOADING_DISTANCE;
  }

  public void loadCar(Car car) {
    if (!ramp.isDown()) {
      throw new IllegalStateException("Cannot load car when ramp is up");
    }
    if (loadedCars.size() >= maxCars) {
      throw new IllegalStateException("Cannot load more entityRepository");
    }
    if (!isCloseEnough(car)) {
      throw new IllegalStateException("Car is not close enough to the transporter");
    }

    loadedCars.push(car);
    updateCarPosition(car);
  }

  public MotorVehicle unloadCar() {
    if (!ramp.isDown()) {
      throw new IllegalStateException("Cannot unload car when ramp is up");
    }
    if (loadedCars.isEmpty()) {
      throw new IllegalStateException("No entityRepository to unload");
    }

    MotorVehicle car = loadedCars.pop();
    // Place the car slightly behind the transporter on the X axis
    Vec2 position = getPos().copy();
    position.add(-LOADING_DISTANCE, 0);

    car.setPos(position);
    return car;
  }

  @Override
  public void tick() {
    super.tick();
    // Update positions of all loaded entityRepository
    for (MotorVehicle car : loadedCars) {
      updateCarPosition(car);
    }
  }

  private void updateCarPosition(MotorVehicle car) {
    car.setPos(getPos());
  }
}