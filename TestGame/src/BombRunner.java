
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class BombRunner extends JPanel implements KeyListener {

    private Image image;
    Character c = new Character();
    private int xPos;
    private int yPos;
    private JLabel collisionLabel;
    private boolean gameOver;
    private ArrayList<Bomb> listB;
    private Image backgroundImage;


    public BombRunner(){
        this.image = c.getImage();
        this.backgroundImage = new ImageIcon("res/background.png").getImage();
        this.xPos = 400;
        this.yPos = 500;
        this.listB = new ArrayList<>();
        this.gameOver = false;
        addKeyListener(this);
        setFocusable(true);
        this.collisionLabel = new JLabel("Collision Detected!");
        this.add(this.collisionLabel);
        this.collisionLabel.setVisible(false);

        Timer timer =  new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                updateBombPosition();
                checkCollision();
                repaint();

            }

        });
        timer.start();
    }

    public void updateBombPosition(){
        for(int i=0; i<listB.size(); i++){
            if(listB.get(i).getY() > getHeight()){
                int randomX = (int)(Math.random() * 601);
                listB.get(i).setY(0);
                listB.get(i).setX(randomX);
            }else {
                int randomY = (int) (Math.random() * 5);
                listB.get(i).setY(listB.get(i).getY() + randomY);
                listB.get(i).setX(listB.get(i).getX());
            }
        }

    }



    public void pacManEffect(int xPos, int yPos){
        if(yPos > getHeight()){
            this.yPos = 0;
        }
        if(yPos < 0){
            this.yPos = getHeight();
        }

        if(xPos > getWidth()){
           this.xPos = 0;
        }
        if(xPos < 0){
            this.xPos = getWidth();
        }
    }

    public void checkCollision(){
        Rectangle character = new Rectangle(this.xPos, this.yPos, 30, 60);

        for (Bomb value : listB) {
            Rectangle bomb = new Rectangle(value.getX(), value.getY(), 50, 50);

            if (character.intersects(bomb) || bomb.intersects(character)) {
                this.collisionLabel.setVisible(true);
                this.gameOver = true;
                break;
            } else {
                this.collisionLabel.setVisible(false);
                this.gameOver = false;
            }
            repaint();
        }

        repaint();

    }

    public void bombAdder(){
        for(int i=0; i<10; i++){
            int xRandom = (int)(Math.random()*601);
            Bomb b = new Bomb(xRandom,2);
            listB.add(b);
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        pacManEffect(this.xPos, this.yPos);
        g.drawImage(backgroundImage, 0, 0, getWidth(),getHeight(), null);
        g.drawImage(image, xPos, yPos, 30, 60, this);
        bombAdder();
        IntStream.range(0, 10).forEach(i -> g.drawImage(listB.get(i).getImage(), listB.get(i).getX(), listB.get(i).getY(), 50, 50, this));


        repaint();

    }




    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch(key){
            case KeyEvent.VK_LEFT: this.xPos-=10; break;
            case KeyEvent.VK_RIGHT: this.xPos+=10; break;
            case KeyEvent.VK_UP: this.yPos-=10; break;
            case KeyEvent.VK_DOWN: this.yPos+=10; break;
        }

       checkCollision();
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
