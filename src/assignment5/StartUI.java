package assignment5;

import javax.swing.JFrame;

public class StartUI {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new ClientUI());
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
