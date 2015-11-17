package assignment1;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class RotatingController implements Runnable {
	
	private ImageIcon image;
	private JLabel lbl;
	private boolean stop;
	
	public RotatingController() {
		image = new ImageIcon("/Users/danielhertzman-ericson/Desktop/gobbe.jpg");
		lbl = new JLabel();
		lbl.setIcon(image);
	}

	@Override
	public void run() {
		stop = false;
		
		while (!stop) {
			
			try {
				Thread.sleep(300);
				System.out.println("rotating :)");
			} catch(InterruptedException e) { stop = true; }
			
		}
		
		
	}
	
	public JLabel getImageLbl() {
		return lbl;
	}
}
