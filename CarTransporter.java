import java.awt.*;
import java.util.Stack;

public class CarTransporter extends Car {
  private static final double LOADING_DISTANCE = 1.0;

  private final Ramp ramp;
  private final Stack<Car> loadedCars;
  private final int maxCars;

  public CarTransporter(int maxCars) {
    super(2, 200, Color.BLUE, "CarTransporter");
    this.ramp = new Ramp();
    this.loadedCars = new Stack<>();
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
    double dx = getCurrentPosition()[0] - car.getCurrentPosition()[0];
    double dy = getCurrentPosition()[1] - car.getCurrentPosition()[1];
    return Math.sqrt(dx * dx + dy * dy) <= LOADING_DISTANCE;
  }

  public void loadCar(Car car) {
    if (!ramp.isDown()) {
      throw new IllegalStateException("Cannot load car when ramp is up");
    }
    if (loadedCars.size() >= maxCars) {
      throw new IllegalStateException("Cannot load more cars");
    }
    if (car instanceof CarTransporter) {
      throw new IllegalStateException("Cannot load another car transporter");
    }
    if (!isCloseEnough(car)) {
      throw new IllegalStateException("Car is not close enough to the transporter");
    }

    loadedCars.push(car);
    updateCarPosition(car);
  }

  public Car unloadCar() {
    if (!ramp.isDown()) {
      throw new IllegalStateException("Cannot unload car when ramp is up");
    }
    if (loadedCars.isEmpty()) {
      throw new IllegalStateException("No cars to unload");
    }

    Car car = loadedCars.pop();
    // Place the car slightly behind the transporter on the X axis
    double[] position = getCurrentPosition().clone();
    position[0] -= LOADING_DISTANCE;
    car.getCurrentPosition()[0] = position[0];
    car.getCurrentPosition()[1] = position[1];
    return car;
  }

  @Override
  public void move() {
    super.move();
    // Update positions of all loaded cars
    for (Car car : loadedCars) {
      updateCarPosition(car);
    }
  }

  private void updateCarPosition(Car car) {
    car.getCurrentPosition()[0] = getCurrentPosition()[0];
    car.getCurrentPosition()[1] = getCurrentPosition()[1];
  }
}