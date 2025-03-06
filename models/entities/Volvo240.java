package models.entities;

import models.Car;
import mvc.ModelFacade;

import java.awt.*;

public class Volvo240 extends Car {

    public final static double trimFactor = 1.25;

    protected Volvo240(ModelFacade model) {
        super(model, 4, 100, Color.RED, "entities.Volvo240");
    }

    @Override
    public double speedFactor() {
        return getEnginePower() * 0.01 * trimFactor;
    }

}
