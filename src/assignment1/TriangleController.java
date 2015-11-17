package assignment1;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class TriangleController implements Runnable {
	
	private ImageIcon image;
	private JLabel lbl;
	
	public TriangleController() {
		image = new ImageIcon("/Users/danielhertzman-ericson/Desktop/gobbe.jpg");
		lbl = new JLabel();
		lbl.setIcon(image);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public JLabel getImageLbl() {
		return lbl;
	}
}
