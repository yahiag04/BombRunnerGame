import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Image in JPanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Create the panel with the image
        BombRunner panel = new BombRunner(); // Replace with your image path
        frame.add(panel);


        // Make the frame visible
        frame.setVisible(true);
    }
}
