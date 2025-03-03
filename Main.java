import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class Main {
    private static final int delay = 50;
    ArrayList<MotorVehicle> cars = new ArrayList<>();

    ModelFacade model;
    CarView view;
    CarController cc;

    Main() {
        model = new ModelFacade(new Vec2(800, 800));
        view = new CarView("CarSim", model);
        cc = new CarController(model, view);

        cc.addCar(new Volvo240(model));
        cc.addCar(new Saab95(model));
        cc.addCar(new Scania(model));

        var timer = new Timer(delay, (e) -> cc.onTick());
        timer.start();
    }

    public static void main(String[] args) {
        new Main();
    }
}
