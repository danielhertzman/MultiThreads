package assignment1;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class RotatingController implements Runnable {
	
	private ImageIcon icon;
	private JLabel lbl;
	private boolean stop;
	private int angle = 90;
	
	public RotatingController() {
		icon = new ImageIcon("/Users/danielhertzman-ericson/Desktop/gobbe.jpg");
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
	
	private void rotate() {
		
		int w = icon.getIconWidth();
        int h = icon.getIconHeight();
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
	
	public JLabel getImageLbl() {
		return lbl;
	}
}
