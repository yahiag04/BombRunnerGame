import javax.swing.*;
import java.awt.*;

public class Bomb {

    private Image image;
    private int x;
    private int y;

    public Bomb(int x, int y){
        this.image = new ImageIcon("res/bomb.png").getImage();
        this.x = x;
        this.y = y;
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }



}
