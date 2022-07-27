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
    protected double gunX = x+67;
    protected double gunY = y+37;  
    // attack modes
    protected final int MELEE  = 101;
    protected final int HANDGUN  = 102;
    protected final int RIFLE  = 103;
    protected int currentWeapon=HANDGUN;

    // images
    protected BufferedImage currentImage;
    protected static BufferedImage right;
    protected static BufferedImage knife;
    protected static BufferedImage rifle;
    //
    
    public Hero(){
        setPreferredSize (new Dimension(63,46));
        ImagePanel();
        currentImage = right;
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
    public void changeAttackMode(int attackMode){
        if(currentWeapon!=attackMode){
            if(attackMode==MELEE){
                currentImage = knife;
                currentWeapon=MELEE;
            }else if(attackMode==HANDGUN){
                currentImage = right;
                currentWeapon=HANDGUN;
            }else if (attackMode==RIFLE){
                currentImage = rifle;
                currentWeapon=RIFLE;
            }
        }
    }
    public void ImagePanel() {
        try {
            knife = ImageIO.read(new File("melee.png"));
            right = ImageIO.read(new File("right.png"));
            rifle = ImageIO.read(new File("rifle.png"));
        } catch (IOException ex) {
            System.out.println("Problem in image paths");
        }
    }
    public void shoot(double mouseX, double mouseY){

    }
}
