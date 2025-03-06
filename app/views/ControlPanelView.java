package app.views;

import app.ModelFacade;

import javax.swing.*;
import java.awt.*;

public class ControlPanelView extends JPanel {
    // The controller member
    ModelFacade model;
    int x;

    public JPanel amountPanel = new JPanel();
    public JSpinner amountSpinner = new JSpinner();
    public JLabel amountLabel = new JLabel("Amount");
    public JButton addRandomCarButton = new JButton("Add random car");
    public JButton removeLastCarButton = new JButton("Remove car");
    public JButton gasButton = new JButton("Gas");
    public JButton brakeButton = new JButton("Brake");
    public JButton turboOnButton = new JButton("Turbo on");
    public JButton turboOffButton = new JButton("Turbo off");
    public JButton liftBedButton = new JButton("Lift Bed");
    public JButton lowerBedButton = new JButton("Lower Bed");

    public JButton startButton = new JButton("Start all cars");
    public JButton stopButton = new JButton("Stop all cars");

    // Constructor
    public ControlPanelView(ModelFacade model){
        this.model = model;
        this.x = ((int) model.getWorldSize().x());
        initComponents();
    }

    // Sets everything in place and fits everything
    private void initComponents() {
        SpinnerModel spinnerModel =
                new SpinnerNumberModel(100, //initial value
                        0, //min
                        100, //max
                        1);//step
        amountSpinner = new JSpinner(spinnerModel);

        amountPanel.setLayout(new BorderLayout());
        amountPanel.add(amountLabel, BorderLayout.PAGE_START);
        amountPanel.add(amountSpinner, BorderLayout.PAGE_END);

        this.add(amountPanel);

        this.setLayout(new GridLayout(2,4));

        this.add(gasButton, 0);
        this.add(turboOnButton, 1);
        this.add(liftBedButton, 2);
        this.add(brakeButton, 3);
        this.add(turboOffButton, 4);
        this.add(lowerBedButton, 5);
        this.add(addRandomCarButton, 6);
        this.add(removeLastCarButton, 7);
        this.setPreferredSize(new Dimension((x), 200));

        this.setBackground(Color.CYAN);


        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(x/5-15,200));
        this.add(startButton);


        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(x/5-15,200));
        this.add(stopButton);
    }
}
