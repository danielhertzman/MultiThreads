package assignment5;

import javax.swing.JFrame;

/**
 * Executes the server UI
 * @author Daniel Hertzman-Ericson
 *
 */
public class StartServer {
	
	public static void main(String[] args) {
		
		ServerUI start = new ServerUI(3540);
		
		JFrame view = new JFrame("AweSome Chat 1.0");
		view.add(start);
		view.setResizable(false);
		view.pack();
		view.setVisible(true);
	}
	
}
