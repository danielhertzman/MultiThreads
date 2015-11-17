package assignment1;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class TriangleController implements Runnable {
	
	private ImageIcon image;
	private JLabel lbl = new JLabel();
	
	public TriangleController() {
		image = new ImageIcon("/Users/danielhertzman-ericson/Desktop/gobbe.png");
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
