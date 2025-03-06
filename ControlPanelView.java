import javax.swing.*;
import java.awt.*;

public class ControlPanelView extends JPanel {
    // The controller member
    ModelFacade model;
//    DrawPanel drawPanel;
//    CarView carView;
    JPanel controlPanel = new JPanel();
    //FIXME: remove unused
    int x, y;

    JPanel amountPanel = new JPanel();
    JSpinner amountSpinner = new JSpinner();
    int amount = 0;
    JLabel amountLabel = new JLabel("Amount");

    JButton gasButton = new JButton("Gas");
    JButton brakeButton = new JButton("Brake");
    JButton turboOnButton = new JButton("Saab Turbo on");
    JButton turboOffButton = new JButton("Saab Turbo off");
    JButton liftBedButton = new JButton("Scania Lift Bed");
    JButton lowerBedButton = new JButton("Lower Lift Bed");

    JButton startButton = new JButton("Start all cars");
    JButton stopButton = new JButton("Stop all cars");

    // Constructor
    public ControlPanelView(ModelFacade model){
        this.model = model;
        this.x = ((int) model.worldSize.getX());
//        this.y = ((int) model.worldSize.getY());
        initComponents();
    }

    // Sets everything in place and fits everything
    private void initComponents() {
        SpinnerModel spinnerModel =
                new SpinnerNumberModel(0, //initial value
                        0, //min
                        100, //max
                        1);//step
        amountSpinner = new JSpinner(spinnerModel);

        amountPanel.setLayout(new BorderLayout());
        amountPanel.add(amountLabel, BorderLayout.PAGE_START);
        amountPanel.add(amountSpinner, BorderLayout.PAGE_END);

        this.add(amountPanel);

        controlPanel.setLayout(new GridLayout(2,4));

        controlPanel.add(gasButton, 0);
        controlPanel.add(turboOnButton, 1);
        controlPanel.add(liftBedButton, 2);
        controlPanel.add(brakeButton, 3);
        controlPanel.add(turboOffButton, 4);
        controlPanel.add(lowerBedButton, 5);
        controlPanel.setPreferredSize(new Dimension((x/2)+4, 200));
        this.add(controlPanel);
        controlPanel.setBackground(Color.CYAN);


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
