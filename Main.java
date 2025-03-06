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
        Vec2 worldSize = new Vec2(800, 800);
        model = new ModelFacade(new InMemoryEntityRepository(6), worldSize);
        view = new CarView("CarSim", model);
        cc = new CarController(model, view);

        var workshop = model.factory.createWorkshop(Volvo240.class, 3);
        workshop.setPos(new Vec2(500, 50));

        model.repository.add(workshop);

        cc.addCar(model.factory.createVolvo());
        cc.addCar(model.factory.createSaab());
        cc.addCar(model.factory.createScania());

        var timer = new Timer(delay, (e) -> cc.onTick());
        timer.start();
    }

    public static void main(String[] args) {
        new Main();
    }
}
