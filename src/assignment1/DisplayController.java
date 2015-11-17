package assignment1;

import java.util.Random;
import javax.swing.JLabel;

/**
 * Implements to Runnable interface and handles 
 * all the logic 
 * @author danielhertzman-ericson
 *
 */
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
				
				Thread.sleep(500); // 500 ms delay between every new location
				lbl.setLocation(rand.nextInt(150),rand.nextInt(150)); // Sets a random location in the panel
				
			} catch (InterruptedException e) { stop = true; }
		}
	}
	
	/**
	 * Returns the JLabel
	 * @return the JLabel
	 */
	public JLabel getLabel() {
		return lbl;
	}

}
