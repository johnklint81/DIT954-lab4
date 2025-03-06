package models.entities;

import java.awt.*;

import models.Car;
import app.*;
import app.interfaces.TurboObserver;

public class Saab95 extends Car implements TurboObserver {

    private boolean turboOn;

    protected Saab95(ModelFacade model) {
        super(model, 2, 125, Color.BLACK, "entities.Saab95");
        turboOn = false;
        model.listenTurbo(this);
    }

    public boolean getTurboOn() {
        return turboOn;
    }

    public void setTurboOn() {
        turboOn = true;
    }

    public void setTurboOff() {
        turboOn = false;
    }
    @Override
    public double speedFactor() {
        double turbo = 1;
        if (turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }

    @Override
    public void setTurbo(boolean newState) {
        if (newState) {
            setTurboOn();
        } else {
            setTurboOff();
        }
    }
}
