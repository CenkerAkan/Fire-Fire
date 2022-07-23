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
    protected int x= STARTING_APSIS;
    protected int y= STARTING_ORDINATE;
    protected ImageIcon currentImage;
    protected static ImageIcon up= new ImageIcon("up.png");;
    protected static ImageIcon down= new ImageIcon("down.png");;
    protected static ImageIcon left= new ImageIcon("left.png");;
    protected static ImageIcon right= new ImageIcon("right.png");;
    public Hero(){
        currentImage = down;
        setPreferredSize (new Dimension(63,46));
    }
    public void setNewLocation(int newX, int newY){
        x=newX;
        y=newY;
        repaint();
    }
    public void setNewImageIcon(ImageIcon icon){
        this.currentImage = icon;
        repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        currentImage.paintIcon(this, g, x, y); // see javadoc for more info on the parameters            
    }
}
