package hu.progtech.vizfacsarok.lordsofthebattlefield;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class actionPanel extends JPanel{
    BufferedImage img;
    actionPanel(){ 
        try {
            img = ImageIO.read(getClass().getResource("source/photo/actionpanelimg.png"));
        } catch (IOException ex) {
            Logger.getLogger(Simulation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void paintComponent(Graphics g)
    {
      super.paintComponent(g);
      if (img != null)
        g.drawImage(img, 0,0,this.getWidth(),this.getHeight(),this);
    }
}