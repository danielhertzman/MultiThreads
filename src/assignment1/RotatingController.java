package assignment1;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Implements the Runnable interface and handles 
 * the logic to the rotating display
 * @author danielhertzman-ericson
 *
 */
public class RotatingController implements Runnable {
	
	private ImageIcon icon;
	private JLabel lbl;
	private boolean stop;
	
	public RotatingController() {
		icon = new ImageIcon("src/resources/gobbe.jpg"); // Creating an image from the resources directory
		lbl = new JLabel();
		lbl.setIcon(icon);
	}

	@Override
	public void run() {
		stop = false;
		
		while (!stop) {
			
			try {
				
				Thread.sleep(300);
				rotate();
				lbl.setIcon(icon);
				
			} catch(InterruptedException e) { stop = true; }
		}
	}
	
	/**
	 * Makes the ImageIcon rotate
	 */
	private void rotate() {
		
		int w = icon.getIconWidth();
        int h = icon.getIconHeight();
        int angle = 90;
        int type = BufferedImage.TYPE_INT_RGB; 
        BufferedImage image = new BufferedImage(h, w, type);
        Graphics2D g2 = image.createGraphics();
        double x = (h - w)/2.0;
        double y = (w - h)/2.0;
        AffineTransform at = AffineTransform.getTranslateInstance(x, y);
        at.rotate(Math.toRadians(angle), w/2.0, h/2.0);
        g2.drawImage(icon.getImage(), at, lbl);
        g2.dispose();
        icon = new ImageIcon(image);
		
	}
	
	/**
	 * Returns the JLabel containing the icon
	 * @return JLabel
	 */
	public JLabel getIconLbl() {
		return lbl;
	}
}
