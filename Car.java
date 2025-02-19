import java.awt.*;

public abstract class Car extends MotorVehicle {
    private boolean isInWorkshop = false;

    protected Car(int nrDoors, double enginePower, Color color, String modelName) {
        super(nrDoors, enginePower, color, modelName);
    }

    @Override
    public boolean canMove() {
        return super.canMove() && !isInWorkshop;
    }

    public void setInWorkshop(boolean inWorkshop) {
        isInWorkshop = inWorkshop;
    }
}
