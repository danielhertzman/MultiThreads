package assignment5;

import javax.swing.JFrame;

/**
 * Starts a client window
 * @author Daniel Hertzman-Ericson
 *
 */
public class StartClient {

	public static void main(String[] args) {
		
		ClientUI start = new ClientUI();
		
		JFrame view = new JFrame("Chat");
		view.add(start);
		view.setResizable(false);
		view.pack();
		view.setVisible(true);
		
	}

}
