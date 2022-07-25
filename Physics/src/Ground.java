import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Main map of the game
 */
public class Ground extends JPanel{
    Hero myHero;
    protected final int JUMP = 50;
    public static void main(String[] args) {
        JFrame myFrame = new JFrame("Fire & Fire");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Hero hero = new Hero();
        JPanel myPanel = new Ground(hero);
        myFrame.getContentPane().add(myPanel);
        myFrame.pack();
        myFrame.setSize(2048, 1080);
        myFrame.setVisible(true);
    }
    public Ground(Hero myHero){
        super();
        addKeyListener(new movementListener(this));
        this.myHero=myHero;
        this.setPreferredSize(new Dimension(2048,1080));
        //this.setLayout(new BorderLayout());
        //this.createComponents();
        myHero.setNewLocation(1000, 500);
        setFocusable(true);
    }
    public void createComponents(){
        Hero myHero = new Hero();
        this.add(myHero);//, BorderLayout.CENTER);
        myHero.setNewLocation(1000, 500);
        repaint();
    }
    public class movementListener implements KeyListener {
        Ground myGround;
        public movementListener(Ground myGround){
            this.myGround = myGround;
        }
        public void keyPressed(KeyEvent event) {
            switch (event.getKeyCode()) {
                case KeyEvent.VK_UP:
                    myGround.myHero.currentImage = myGround.myHero.up;
                    myGround.myHero.y -= JUMP;
                    repaint();
                    break;
                case KeyEvent.VK_DOWN:
                    myHero.currentImage = myHero.down;
                    myHero.y += JUMP;
                    break;
                case KeyEvent.VK_LEFT:
                    myHero.currentImage = myHero.left;
                    myHero.x -= JUMP;
                    break;
                case KeyEvent.VK_RIGHT:
                    myHero.currentImage = myHero.right;
                    myHero.x += JUMP;
                    break;
            }
            repaint();
        }
        public void keyTyped(KeyEvent e) {}
        public void keyReleased(KeyEvent e) {}
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(myHero.currentImage, myHero.x, myHero.y, this); // see javadoc for more info on the parameters            
    }
}
