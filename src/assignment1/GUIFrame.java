
package assignment1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;

/**
 * The GUI for assignment 1
 */
public class GUIFrame implements ActionListener
{
	/**
	 * These are the components you need to handle.
	 * You have to add listeners and/or code
	 */
	private JFrame frame;		// The Main window
	private JButton btnOpen;	// Open sound file button
	private JButton btnPlay;	// Play selected file button
	private JButton btnStop;	// Stop music button
	private JButton btnDisplay;	// Start thread moving display
	private JButton btnDStop;	// Stop moving display thread
	private JButton btnTriangle;// Start moving graphics thread
	private JButton btnTStop;	// Stop moving graphics thread
	private JLabel lblPlaying;	// Hidden, shown after start of music
	private JLabel lblPlayURL;	// The sound file path
	private JPanel pnlMove;		// The panel to move display in
	private JPanel pnlRotate;	// The panel to move graphics in
	private MusicController mc;
	private DisplayController dc;
	private TriangleController tc;
	private Thread t1;
	private Thread t2;
	private Thread t3;

	/**
	 * Constructor
	 */
	public GUIFrame()
	{
	}
	
	/**
	 * Starts the application
	 */
	public void start()
	{
		frame = new JFrame();
		frame.setBounds(0, 0, 494, 437);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setTitle("Multiple Thread Demonstrator");
		initializeGUI();					// Fill in components
		frame.setVisible(true);
		frame.setResizable(false);			// Prevent user from change size
		frame.setLocationRelativeTo(null);	// Start middle screen
	}
	
	/**
	 * Sets up the GUI with components
	 */
	private void initializeGUI()
	{
		// The play panel
		JPanel pnlSound = new JPanel();
		Border b1 = BorderFactory.createTitledBorder("Music Player");
		pnlSound.setBorder(b1);
		pnlSound.setBounds(12, 12, 450, 100);
		pnlSound.setLayout(null);
		
		// Add the buttons and labels to this panel
		btnOpen = new JButton("Open");
		btnOpen.setBounds(6, 71, 75, 23);
		btnOpen.addActionListener(this);
		pnlSound.add(btnOpen);
		
		btnPlay = new JButton("Play");
		btnPlay.setBounds(88, 71, 75, 23);
		btnPlay.addActionListener(this);
		pnlSound.add(btnPlay);
		
		btnStop = new JButton("Stop");
		btnStop.setBounds(169, 71, 75, 23);
		btnStop.addActionListener(this);
		pnlSound.add(btnStop);
		
		lblPlaying = new JLabel("Now Playing...",JLabel.CENTER);
		lblPlaying.setFont(new Font("Serif", Font.BOLD, 20));
		lblPlaying.setBounds(128, 16, 120, 30);
		pnlSound.add(lblPlaying);
		
		lblPlayURL = new JLabel("Music url goes here");
		lblPlayURL.setBounds(10, 44, 115, 13);
		pnlSound.add(lblPlayURL);
		// Then add this to main window
		frame.add(pnlSound);
		
		// The moving display outer panel
		JPanel pnlDisplay = new JPanel();
		Border b2 = BorderFactory.createTitledBorder("Display Thread");
		pnlDisplay.setBorder(b2);
		pnlDisplay.setBounds(12, 118, 222, 269);
		pnlDisplay.setLayout(null);
		
		// Add buttons and drawing panel to this panel
		btnDisplay = new JButton("Start Display");
		btnDisplay.setBounds(10, 226, 121, 23);
		pnlDisplay.add(btnDisplay);
		btnDisplay.addActionListener(this);
		
		btnDStop = new JButton("Stop");
		btnDStop.setBounds(135, 226, 75, 23);
		btnDStop.addActionListener(this);
		pnlDisplay.add(btnDStop);
		
		pnlMove = new JPanel();
		pnlMove.setBounds(10,  19,  200,  200);
		Border b21 = BorderFactory.createLineBorder(Color.black);
		pnlMove.setBorder(b21);
		pnlDisplay.add(pnlMove);
		// Then add this to main window
		frame.add(pnlDisplay);
		
		// The moving graphics outer panel
		JPanel pnlTriangle = new JPanel();
		Border b3 = BorderFactory.createTitledBorder("Triangle Thread");
		pnlTriangle.setBorder(b3);
		pnlTriangle.setBounds(240, 118, 222, 269);
		pnlTriangle.setLayout(null);
		
		// Add buttons and drawing panel to this panel
		btnTriangle = new JButton("Start Rotate");
		btnTriangle.setBounds(10, 226, 121, 23);
		btnTriangle.addActionListener(this);
		pnlTriangle.add(btnTriangle);
		
		btnTStop = new JButton("Stop");
		btnTStop.setBounds(135, 226, 75, 23);
		pnlTriangle.add(btnTStop);
		
		pnlRotate = new JPanel();
		pnlRotate.setBounds(10,  19,  200,  200);
		Border b31 = BorderFactory.createLineBorder(Color.black);
		pnlRotate.setBorder(b31);
		pnlTriangle.add(pnlRotate);
		// Add this to main window
		frame.add(pnlTriangle);
	}
	


	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		
		if (e.getSource() == btnOpen) {
			
			mc = new MusicController();
			lblPlayURL.setText(mc.getFilePath());
			lblPlaying.setText(mc.getDescription());
			
		}
		
		if (e.getSource() == btnPlay) {
			
			if (t1 == null) {
				
				t1 = new Thread(mc);		
				t1.start();
				
			} 
		} 
		
		if (e.getSource() == btnStop) {
			
			if (t1 != null) {
				
				mc.stopMusic();
				t1.interrupt();
				System.out.println(t1.isInterrupted());
				t1 = null;
				
			}		
		}
		
		if (e.getSource() == btnDisplay) {
			
			if (dc == null) {
				
				dc = new DisplayController();
				t2 = new Thread(dc);
				pnlMove.add(dc.getLabel());
				pnlMove.updateUI();
				t2.start();
				
			} else {
				
				t2 = new Thread(dc);
				t2.start();
				
			}
		}
		
		if (e.getSource() == btnDStop) {
			
			if (t2 != null) {
				
				t2.interrupt();
				t2 = null;
				
			}
				
		}
		
		if (e.getSource() == btnTriangle) {
			
			tc = new TriangleController();
			pnlRotate.add(tc.getImageLbl());
			pnlRotate.updateUI();
			
		}
			
	}
	
}