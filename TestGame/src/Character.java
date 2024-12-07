import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;



public class Character extends Entity {

    private Image image;
    private int hp;
    private int x;
    private int y;
    protected Rectangle hitbox;


    public Character(int x, int y){
        super(x, y);
        this.hp = 100;
        this.image = new ImageIcon("res/character.png").getImage();

    }



    public Image getImage() {
        return image;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }


    public int getHp(){
        return hp;
    }
}
