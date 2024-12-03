import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Character {

    private Image image;
    private int hp;

    public Character(){
        this.hp = 100;
        this.image = new ImageIcon("res/character.png").getImage();
    }

    public Image getImage() {
        return image;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
