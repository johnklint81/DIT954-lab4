import java.awt.*;

public abstract class Car extends MotorVehicle {
    protected Car(int nrDoors, double enginePower, Color color, String modelName) {
        super(nrDoors, enginePower, color, modelName);
    }
}
