import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    // Just a single image, TODO: Generalize
    BufferedImage volvoImage;
    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(500,50);
    CarController cc;

    Map<Class<?>, BufferedImage> images = new HashMap<>();

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, CarController cc) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        this.cc = cc;

        var map = new HashMap<Class<?>, String>();

        map.put(Volvo240.class, "Volvo240.jpg");
        map.put(Saab95.class, "Saab95.jpg");
        map.put(Scania.class, "Scania.jpg");
        // Now we're not able to load differently "styled" workshops
        map.put(CarWorkshop.class, "VolvoBrand.jpg");

        map.forEach((classRef, imgName) -> {
            try {
                images.put(classRef, ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream("pics/" + imgName))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        try {
            volvoWorkshopImage = ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);

        for (MotorVehicle car : cc.cars) {
            g.drawImage(images.getOrDefault(car.getClass(), images.get(Volvo240.class)), (int)car.getPos().getX(), (int)car.getPos().getY(), null); // see javadoc for more info on the parameters
        }
    }
}
