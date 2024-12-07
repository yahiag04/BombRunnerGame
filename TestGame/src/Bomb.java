import javax.swing.*;
import java.awt.*;

public class Bomb extends Entity{

    private Image image;
    private int x;
    private int y;

    public Bomb(int x, int y){
        super(x, y);
        this.image = new ImageIcon("res/bomb.png").getImage();

    }

    public Image getImage() {
        return image;
    }




}
