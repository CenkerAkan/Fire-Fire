import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
/**
 * Main map of the game
 */
public class Ground extends JPanel{
    Hero myHero;
    private double imageAngleRad = 0;
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
        addMouseMotionListener(new MouseAim());
        this.myHero=myHero;
        this.setPreferredSize(new Dimension(2048,1080));
        //this.setLayout(new BorderLayout());
        //this.createComponents();
        myHero.setNewLocation(1500,1000);
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
                    //myGround.myHero.currentImage = myGround.myHero.up;
                    myGround.myHero.y -= JUMP;
                    repaint();
                    break;
                case KeyEvent.VK_DOWN:
                    //myHero.currentImage = myHero.down;
                    myHero.y += JUMP;
                    break;
                case KeyEvent.VK_LEFT:
                    //myHero.currentImage = myHero.left;
                    myHero.x -= JUMP;
                    break;
                case KeyEvent.VK_RIGHT:
                    //myHero.currentImage = myHero.right;
                    myHero.x += JUMP;
                    break;
            }
            repaint();
        }
        public void keyTyped(KeyEvent e) {}
        public void keyReleased(KeyEvent e) {}
    }
    public class MouseAim implements MouseMotionListener, Runnable{

        public void run() {
            // TODO Auto-generated method stub
            
        }
        public void mouseMoved(MouseEvent e) {
            double dx = e.getX() - (myHero.x+63);//according to the gun
            double dy = e.getY() - (myHero.y+37);
            imageAngleRad = Math.atan2(dy, dx);
            repaint();
        }
        public void mouseDragged(MouseEvent e){}
    }
    
    protected void paintComponent(Graphics gr) {
        //super.paintComponent(g);
        //g.drawImage(myHero.currentImage, myHero.x, myHero.y, this); // see javadoc for more info on the parameters            
        super.paintComponent(gr);
        Graphics2D g = (Graphics2D)gr;
        g.setRenderingHint(
            RenderingHints.KEY_RENDERING, 
            RenderingHints.VALUE_RENDER_QUALITY);

        int cx = myHero.currentImage.getWidth() / 2;
        int cy = myHero.currentImage.getHeight() / 2;
        AffineTransform oldAT = g.getTransform();
        g.translate(cx+(myHero.x), cy+(myHero.y));
        g.rotate(imageAngleRad);
        g.translate(-cx, -cy);
        g.drawImage(myHero.currentImage, 0, 0, null);
        g.setTransform(oldAT);
    }
}
