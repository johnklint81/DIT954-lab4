import java.awt.*;

public abstract class MotorVehicle extends Entity implements EngineObserver {
    // We need to discuss whether we want to use interfaces also...
    // Inheritance is nice here because the subclasses share a lot of functionality

    private double currentDirection = 0; // Angle in radians (from unit vector perspective)
    private final int nrDoors; // Number of doors on the car
    private final double enginePower; // Engine power of the car
    private double currentSpeed = 0; // The current speed of the car
    private Color color; // Color of the car
    private final String modelName; // The car model name

    // Should not be a property of car, but for now it is
    // Maybe this is what is found as 0.01 in speedFactor?
    // protected final static double timestep = 0.1;

    // Constructor, maybe we should have more fields in this one?
    // I don't like that the subclasses initialises nrDoors, enginePower, etc,
    // to hard-coded values.
    protected MotorVehicle(ModelFacade model, int nrDoors, double enginePower, Color color, String modelName) {
        super(model, Vec2.ZERO, new Vec2(100, 60));
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        model.listenTick(this);
        model.listenEngine(this);
        stopEngine();
    }

    @Override
    public void setEngine(boolean newState) {
        if (newState) {
            startEngine();
        } else {
            stopEngine();
        }
    }

    // Override is strictly speaking not necessary here since we implement
    // the required method by the interface for the first time. But it is not
    // wrong either.
    @Override
    public void tick() {
        if (canMove()) {
            double positionChange = getCurrentSpeed();
            double xOffset = positionChange * Math.cos(currentDirection);
            double yOffset =  positionChange * Math.sin(currentDirection);
            setPos(getPos().add(xOffset,yOffset));
        }
    }

    public void turnLeft(double angle) {
        currentDirection = (currentDirection + angle);
    }

    public void turnRight(double angle) {
        currentDirection = (currentDirection - angle);
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


    public Color getColor() {
        return color;
    }

    public String getModelName() {
        return modelName;
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
    // Abstract because it can change depending on model of car
    protected abstract double speedFactor();
    // Protected because meant to be overridden and used by subclasses.
    // If they are protected we can still change them in our subclass while
    // preventing external access.

    private void setCurrentSpeed(double speed) {
        if (!canMove() && Math.abs(speed) > 0) {
            throw new IllegalStateException("Vehicle cannot move in current state");
        }

        currentSpeed = Math.min(Math.max(speed, 0), getEnginePower());
    }

    private void incrementSpeed(double amount) {
        setCurrentSpeed(getCurrentSpeed() + speedFactor() * amount);
    }

    private void decrementSpeed(double amount) {
        setCurrentSpeed(getCurrentSpeed() - speedFactor() * amount);
    }

    public boolean canMove() {
        return true;
    }

    public void gas(double amount) {
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("gas amount must be in interval [0, 1].");
        }
        incrementSpeed(amount);
    }

    public void brake(double amount) {
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("brake amount must be in interval [0, 1].");
        }
        decrementSpeed(amount);
    }
}
