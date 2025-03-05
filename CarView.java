import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents the full view of the MVC pattern of your car simulator.
 * It initializes with being center on the screen and attaching it's controller in it's state.
 * It communicates with the Controller by calling methods of it when an action fires of in
 * each of it's components.
 * TODO: Write more actionListeners and wire the rest of the buttons
 **/

public class CarView extends JFrame{
    // The controller member
    ModelFacade model;
    DrawPanel drawPanel;
//    ControlPanelView controlPanel;
    int x, y;

    public JPanel amountPanel;
    public JSpinner amountSpinner;
    public int amount;
    public JLabel amountLabel;

    public JButton gasButton;
    public JButton brakeButton;
    public JButton turboOnButton;
    public JButton turboOffButton;
    public JButton liftBedButton;
    public JButton lowerBedButton;

    public JButton startButton;
    public JButton stopButton;

    // Constructor
    public CarView(String frameName, ModelFacade model){
        this.model = model;
//        this.controlPanel = new ControlPanelView(this, model);
        this.x = ((int) model.worldSize.getX());
        this.y = ((int) model.worldSize.getY());

        drawPanel = new DrawPanel(model, x, y-240);

        this.setTitle(frameName);
        this.setPreferredSize(new Dimension((int) model.worldSize.getX(), (int) model.worldSize.getY()));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        this.add(drawPanel);
        ControlPanelView controlPanel = new ControlPanelView(this, this.model);

        this.amountPanel = controlPanel.amountPanel;
        this.amountSpinner = controlPanel.amountSpinner;
        this.amount = controlPanel.amount;
        this.amountLabel = controlPanel.amountLabel;

        this.gasButton = controlPanel.gasButton;
        this.brakeButton = controlPanel.brakeButton;
        this.turboOnButton = controlPanel.turboOnButton;
        this.turboOffButton = controlPanel.turboOffButton;
        this.liftBedButton = controlPanel.liftBedButton;
        this.lowerBedButton = controlPanel.lowerBedButton;

        this.startButton = controlPanel.startButton;
        this.stopButton = controlPanel.stopButton;

//        // Make the frame pack all it's components by respecting the sizes if possible.
        this.pack();

        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        // Make the frame visible
        this.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}