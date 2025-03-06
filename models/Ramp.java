package models;

public class Ramp {
    private boolean isDown;

    public Ramp() {
        isDown = false;
    }

    public void lower() {
        isDown = true;
    }

    public void raise() {
        isDown = false;
    }

    public boolean isDown() {
        return isDown;
    }
} 