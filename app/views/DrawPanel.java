package app.views;

import models.*;
import models.entities.CarWorkshop;
import models.entities.Saab95;
import models.entities.Scania;
import models.entities.Volvo240;
import app.ModelFacade;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    ModelFacade model;
    Map<Class<?>, BufferedImage> images = new HashMap<>();

    // Initializes the panel and reads the images
    public DrawPanel(ModelFacade model, int x, int y) {
        this.model = model;
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.pink);

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
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Entity entity : model.getRepository()) {
            g.drawImage(images.getOrDefault(entity.getClass(), images.get(Volvo240.class)), (int)entity.getPos().x(), (int)entity.getPos().y(), null); // see javadoc for more info on the parameters
        }
    }
}
