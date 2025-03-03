import javax.swing.*;

/*
* This class represents the Controller part in the MVC pattern.
* Its responsibilities are to listen to the View and responds in an appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    ModelFacade model;
    CarView view;
    int amount = 0;
    int offset = 0;
    CarWorkshop<Volvo240> volvoWorkshop = new CarWorkshop<>(3, new Vec2(500, 50));

    CarController(ModelFacade model, CarView view) {
        this.model = model;
        this.view = view;
        setUpListeners();
    }

    private void setUpListeners() {
        view.startButton.addActionListener((e) -> model.setEngines(true));
        view.stopButton.addActionListener((e) -> model.setEngines(false));
        view.amountSpinner.addChangeListener((e) -> amount = ((int) ((JSpinner) e.getSource()).getValue()) / 100);
        view.gasButton.addActionListener((e) -> model.gas(amount));
        view.brakeButton.addActionListener((e) -> model.brake(amount));
        view.lowerBedButton.addActionListener(e -> model.lowerBeds(10));
        view.liftBedButton.addActionListener(e -> model.raiseBeds(10));
        view.turboOffButton.addActionListener(e -> model.setTurbos(false));
        view.turboOnButton.addActionListener(e -> model.setTurbos(true));
    }

    void addCar(MotorVehicle car) {
        model.cars.add(car);
        car.setPos(new Vec2(0, offset));
        offset += 100;
    }

    public void onTick() {
        model.tick();
        view.drawPanel.repaint();
    }
}
