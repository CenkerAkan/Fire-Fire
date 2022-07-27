import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
/**
 * Main map of the game
 */
public class Ground extends JPanel{
    Hero myHero;
    private double imageAngleRad = 0;
    protected final int JUMP = 5;
    protected int acceleration=3;
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
        MouseAim myListener = new MouseAim(this); 
        addMouseMotionListener(myListener);//new MouseAim(this));
        addMouseListener(myListener);
        this.myHero=myHero;
        this.setPreferredSize(new Dimension(2048,1080));
        //this.setLayout(new BorderLayout());
        //this.createComponents();
        myHero.setNewLocation(1000,500);
        setFocusable(true);
    }
    public void createComponents(){

    }

    public class movementListener implements KeyListener {
        Ground myGround;
        private final Set<Integer> pressedKeys = new HashSet<>();
        public movementListener(Ground myGround){
            this.myGround = myGround;
        }
        public void keyPressed(KeyEvent event) {
            pressedKeys.add(event.getKeyCode());
            Point offset = new Point();
            if (!pressedKeys.isEmpty()) {
                for (Iterator<Integer> it = pressedKeys.iterator(); it.hasNext();) {
                    switch (it.next()) {
                        case KeyEvent.VK_W:
                        case KeyEvent.VK_UP:
                            myGround.myHero.y -=JUMP;//acceleration;
                            repaint();
                            break;
                        case KeyEvent.VK_A:
                        case KeyEvent.VK_LEFT:
                            myGround.myHero.x -= JUMP;
                            repaint();
                            break;
                        case KeyEvent.VK_S:
                        case KeyEvent.VK_DOWN:
                            myGround.myHero.y += JUMP;
                            repaint();
                            break;
                        case KeyEvent.VK_D:
                        case KeyEvent.VK_RIGHT:
                            myGround.myHero.x += JUMP;
                            repaint();
                            break;
                        case KeyEvent.VK_1:
                        myGround.myHero.changeAttackMode(myHero.MELEE);
                        repaint();
                        break;
                        case KeyEvent.VK_2:
                        myGround.myHero.changeAttackMode(myHero.HANDGUN);
                        repaint();
                        break;
                        case KeyEvent.VK_3:
                        myGround.myHero.changeAttackMode(myHero.RIFLE);
                        repaint();
                        break;
                    }
                }
                repaint();
            }
            /*switch (event.getKeyCode()) {
                case KeyEvent.VK_W:
                    acceleration +=3; 
                    myGround.myHero.y -=JUMP;//acceleration;
                    repaint();
                    break;
                case KeyEvent.VK_S:
                    myGround.myHero.y += JUMP;
                    repaint();
                    break;
                case KeyEvent.VK_A:
                    myGround.myHero.x -= JUMP;
                    repaint();
                    break;
                case KeyEvent.VK_D:
                    myGround.myHero.x += JUMP;
                    repaint();
                    break;
            }
            repaint();*/
        }
        public void keyTyped(KeyEvent e) {}
        public void keyReleased(KeyEvent e) {
            pressedKeys.remove(e.getKeyCode());
        }
    }
    public class MouseAim implements MouseMotionListener, MouseListener{
        Ground myPanel;
        static double MouseX;
        static double MouseY;
        protected static boolean shoot = false;

        public MouseAim(Ground myGround){
            this.myPanel = myGround;
        }
        public void mouseMoved(MouseEvent e) {
            double dx = e.getX() - (myHero.x+63);//according to the gun
            double dy = e.getY() - (myHero.y+37);
            MouseX = e.getX();
            MouseY = e.getY();
            imageAngleRad = Math.atan2(dy, dx);
            repaint();
        }
        public void mouseDragged(MouseEvent e){}
        
        
        public void mouseClicked(MouseEvent e) {
            shoot = true;
            repaint();
        }
        public void mousePressed(MouseEvent e) {
            shoot = true;
            repaint();  
        }
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
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
        if(MouseAim.shoot){
            g.drawLine((int)(myHero.x+63*Math.cos(imageAngleRad)),(int)(myHero.y+Math.sin(imageAngleRad)*37),(int) MouseAim.MouseX,(int) MouseAim.MouseY);
            MouseAim.shoot = false;
        }
    }
}
