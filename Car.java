import java.awt.*;

public abstract class Car implements Movable {
    // We need to discuss whether we want to use interfaces also...
    // Inheritance is nice here because the subclasses share a lot of functionality

    protected double[] currentPosition;
    protected double currentDirection; // Angle in degrees (from unit vector perspective)
    protected int nrDoors; // Number of doors on the car
    protected double enginePower; // Engine power of the car
    protected double currentSpeed; // The current speed of the car
    protected Color color; // Color of the car
    protected String modelName; // The car model name
    // Should not be a property of car, but for now it is
    protected final static double timestep = 0.1;

    // Constructor, maybe we should have more fields in this one?
    // I don't like that the subclasses initialises nrDoors, enginePower, etc
    protected Car(Color color) {
        this.color = color;
    }
    // Override is strictly speaking not necessary here since we implement
    // the required method by the interface for the first time. But it is not
    // wrong either.
    @Override
    public void move() {
        // currentPosition is a 2D array: [x, y]
        // atan2 gives us signed direction in radians
        double direction = Math.atan2(currentPosition[1], currentPosition[0]);
        double positionChange = getCurrentSpeed() * timestep;

        currentPosition[0] += positionChange * Math.cos(direction);
        currentPosition[1] += positionChange * Math.sin(direction);
    }
    // We could use radians below if we wanted to
    @Override
    public void turnLeft(double angle) {
        currentDirection = (currentDirection + angle) % 360;
    }
    @Override
    public void turnRight(double angle) {
        currentDirection = (currentDirection - angle) % 360;
    }
    public int getNrDoors() {
        return nrDoors;
    }

    public double getEnginePower() {
        return enginePower;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public double getCurrentDirection() {
        return currentDirection;
    }
    public double[] getCurrentPosition() {
        return currentPosition;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color clr) {
        color = clr;
    }

    public void startEngine() {
        currentSpeed = 0.1;
    }

    public void stopEngine() {
        currentSpeed = 0;
    }
    // Protected because it is not meant to be changed directly by the user.
    // 1 is just a default value.
    protected double speedFactor() {
        return 1;
    }
    // Protected because meant to be overridden and used by subclasses.
    // If they are protected we can still change them in our subclass while
    // preventing external access.
    protected void incrementSpeed(double amount) {
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }

    protected void decrementSpeed(double amount) {
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }
}
