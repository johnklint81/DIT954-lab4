import entities.MotorVehicle;
import entities.Volvo240;
import mvc.*;

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

        var workshop = model.getFactory().createWorkshop(Volvo240.class, 3);
        workshop.setPos(new Vec2(500, 50));

        model.getRepository().add(workshop);

        cc.addCar(model.getFactory().createVolvo());
        cc.addCar(model.getFactory().createSaab());
        cc.addCar(model.getFactory().createScania());

        var timer = new Timer(delay, (e) -> cc.onTick());
        timer.start();
    }

    public static void main(String[] args) {
        new Main();
    }
}
