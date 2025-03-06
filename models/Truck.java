package models;

import app.ModelFacade;

import java.awt.*;

public abstract class Truck extends MotorVehicle {
    protected Truck(ModelFacade model, int nrDoors, double enginePower, Color color, String modelName) {
        super(model, nrDoors, enginePower, color, modelName);
    }
}
