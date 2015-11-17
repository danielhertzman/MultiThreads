package assignment1;

import java.util.Random;
import javax.swing.JLabel;

public class DisplayController implements Runnable {
	private JLabel lbl;
	private Random rand;
	private boolean stop;
	
	public DisplayController() {
		rand = new Random();
		lbl = new JLabel();
		lbl.setText("Test");
	}
	
	@Override
	public void run() {
		stop = false;
		
		while (!stop) {
			try {
				
				Thread.sleep(500);
				lbl.setLocation(rand.nextInt(150),rand.nextInt(150));
				
			} catch (InterruptedException e) { stop = true; }
		}
	}
	
	public JLabel getLabel() {
		return lbl;
	}

}
