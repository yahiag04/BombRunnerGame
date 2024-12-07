
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class BombRunner extends JPanel implements KeyListener {

    Character c = new Character(400,500);
    private final Image image = c.getImage();

    private final JLabel collisionLabel;
    private boolean gameOver;
    private ArrayList<Bomb> listB;
    private ArrayList<Ellipse2D> listOvals;
    private final Image backgroundImage;
    private Timer timer;
    private Rectangle characterRect;
    AudioGame audio = new AudioGame("res/gameAudio.wav");


    public BombRunner(){

        this.backgroundImage = new ImageIcon("res/background.png").getImage();
        this.listB = new ArrayList<>();
        this.listOvals = new ArrayList<>();
        this.gameOver = false;
        addKeyListener(this);
        setFocusable(true);
        this.collisionLabel = new JLabel("Collision Detected!");
        collisionLabel.setForeground(Color.WHITE);
        collisionLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        this.add(this.collisionLabel);
        this.collisionLabel.setVisible(false);


         this.timer =  new Timer(16, _ -> {

             audio.play();
             updateBombPosition();
             checkCollision();
             repaint();

             if(gameOver){
                 audio.stop();
                 timer.stop();


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
            c.setY(0);
        }
        if(yPos < 0){
            c.setY(getHeight());
        }

        if(xPos > getWidth()){
           c.setX(0);
        }
        if(xPos < 0){
            c.setX(getWidth());
        }
    }

    public void checkCollision(){
        for(Ellipse2D ovals: listOvals){
            if(characterRect.intersects(ovals.getBounds2D())  || ovals.getBounds2D().intersects(characterRect)){
                gameOver = true;
                collisionLabel.setVisible(true);
                break;
            }else{
                gameOver = false;
                collisionLabel.setVisible(false);

            }
        }
    }

    public void bombAdder(){
        for(int i=0; i<30; i++){
            int xRandom = (int)(Math.random()*601);
            Bomb b = new Bomb(xRandom,2);
            listB.add(b);
        }
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        pacManEffect(c.getX(), c.getY());
        g.drawImage(backgroundImage, 0, 0, getWidth(),getHeight(), null);
        g.drawImage(image, c.getX(), c.getY(), 30, 60, this);


        characterRect = new Rectangle(c.getX()+9, c.getY()+5, 12, 55);
        //g.drawRect(characterRect.x, characterRect.y, characterRect.width, characterRect.height);
        listOvals.clear();
        bombAdder();
        for (int i = 0; i < 30; i++) {
            g.drawImage(listB.get(i).getImage(), listB.get(i).getX(), listB.get(i).getY(), 50, 50, this);
            Ellipse2D oval = new Ellipse2D.Double(listB.get(i).getX()+8, listB.get(i).getY()+15, 25, 25) ;
            listOvals.add(oval);
            //g.drawOval((int) oval.getX(), (int)oval.getY(), (int)oval.getWidth(),(int)oval.getHeight());
        }


        repaint();

    }




    @Override
    public void keyTyped(KeyEvent e) {

    }


        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    c.setX(c.getX() - 10);
                    break;
                case KeyEvent.VK_RIGHT:
                    c.setX(c.getX() + 10);
                    break;
                case KeyEvent.VK_UP:
                    c.setY(c.getY() - 10);
                    break;
                case KeyEvent.VK_DOWN:
                    c.setY(c.getY() + 10);
                    break;
            }

            if(gameOver){ removeKeyListener(this);}
            repaint();
        }


    @Override
    public void keyReleased(KeyEvent e) {

    }


}
