import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
/**
 * Main character object 
 */
public class Hero extends JPanel{
    private final int STARTING_APSIS =0;
    private final int STARTING_ORDINATE =0;
    protected double x= STARTING_APSIS;
    protected double y= STARTING_ORDINATE;
    protected double velX = 0;
    protected double velY =0;
    protected BufferedImage currentImage;
    protected static BufferedImage up;
    protected static BufferedImage down;
    protected static BufferedImage left;
    protected static BufferedImage right;
    public Hero(){
        ImagePanel();
        currentImage = right;
        setPreferredSize (new Dimension(63,46));
    }
    public void setNewLocation(int newX, int newY){
        x=newX;
        y=newY;
        repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(currentImage, 0, 0, this); // see javadoc for more info on the parameters            
    }
    public void ImagePanel() {
        try {                
           up = ImageIO.read(new File("up.png"));
           down = ImageIO.read(new File("down.png"));
           left = ImageIO.read(new File("left.png"));
           right = ImageIO.read(new File("right.png"));
        } catch (IOException ex) {
            System.out.println("Problem in image paths");
        }
     }
}
