package mvc;

import entities.MotorVehicle;

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

    public CarController(ModelFacade model, CarView view) {
        this.model = model;
        this.view = view;
        setUpListeners();
    }

    private void setUpListeners() {
        view.controlPanel.startButton.addActionListener((e) -> model.setEngines(true));
        view.controlPanel.stopButton.addActionListener((e) -> model.setEngines(false));
        view.controlPanel.amountSpinner.addChangeListener((e) -> amount = ((int) ((JSpinner) e.getSource()).getValue()) / 100);
        view.controlPanel.gasButton.addActionListener((e) -> model.gas(amount));
        view.controlPanel.brakeButton.addActionListener((e) -> model.brake(amount));
        view.controlPanel.lowerBedButton.addActionListener(e -> model.lowerBeds(10));
        view.controlPanel.liftBedButton.addActionListener(e -> model.raiseBeds(10));
        view.controlPanel.turboOffButton.addActionListener(e -> model.setTurbos(false));
        view.controlPanel.turboOnButton.addActionListener(e -> model.setTurbos(true));
        view.controlPanel.addRandomCarButton.addActionListener(e -> {
            model.repository.add(model.factory.createRandomMotorVehicle(offset));
            offset += 100;
        });
        view.controlPanel.removeLastCarButton.addActionListener(e -> {
            model.repository.pop();
            offset -= 100;
        });
    }

    public void addCar(MotorVehicle car) {
        model.repository.add(car);
        car.setPos(new Vec2(0, offset));
        offset += 100;
    }

    public void onTick() {
        model.tick();
        view.drawPanel.repaint();
    }
}
