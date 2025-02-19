import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* Its responsibilities are to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<MotorVehicle> cars = new ArrayList<>();

    CarWorkshop<Volvo240> volvoWorkshop = new CarWorkshop<>(3, new Vec2(500, 50));

    public static final Vec2 CAR_SIZE = new Vec2(100, 60);

    Vec2 frameSize;

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();


        cc.cars.add(new Volvo240());
        cc.cars.add(new Saab95());
        cc.cars.add(new Scania());

        for (int i = 0; i < cc.cars.size(); i++) {
            cc.cars.get(i).setPos(new Vec2(0, i * 100));
        }

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);
        cc.frameSize = new Vec2(cc.frame.getX(), cc.frame.getX());

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (MotorVehicle car : cars) {
                int x = (int) Math.round(car.getPos().getX());
                int y = (int) Math.round(car.getPos().getY());

                if (x > (frame.getX() - CAR_SIZE.getX()) || x < 0) {
                    car.turnLeft(Math.PI);
                }

                if (car instanceof Volvo240 volvo) {
                    if (volvoWorkshop.collidesWith(volvo) && !volvoWorkshop.hasCar(volvo)) {
                        volvoWorkshop.submitCar(volvo);
                    }
                }

                car.move();

                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (MotorVehicle car : cars) {
            car.gas(gas);
        }
    }
    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (MotorVehicle car : cars) {
            car.brake(brake);
        }
    }

    void setTurbo(boolean state) {
        for (var car : cars) {
            if (car instanceof Saab95) {
                if (state) ((Saab95) car).setTurboOn();
                else ((Saab95) car).setTurboOff();
            }
        }
    }

    void stopEngines() {
        for (var car : cars) {
            car.stopEngine();
        }
    }

    void startEngines() {
        for (var car : cars) {
            car.startEngine();
        }
    }

    void updateBed(boolean lower) {
        for (var car : cars) {
            if (car instanceof Scania) {
                if (lower) ((Scania) car).lowerBed(10);
                else ((Scania) car).raiseBed(10);
            }
        }
    }
}
