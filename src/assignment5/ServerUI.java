package assignment5;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Simple UI in order to start server
 * @author Daniel Hertzman-Ericson
 *
 */
public class ServerUI extends JPanel implements ActionListener {
	private JButton btn = new JButton("Starta servern!");
	private Server server;
	private int port;

	/**
	 * Constructor from port
	 * @param port
	 */
	public ServerUI(int port) {
		
		this.port = port;
		server = new Server(port, this);
		
		setPreferredSize(new Dimension(320,160));
		add(btn);
		
		btn.addActionListener(this);
	}
	
	/**
	 * ActionListener
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn){
			new ConnectNow().start();
			System.out.println("Tryck på knapp registrerad!");
		}
		
	}
	
	/**
	 * Start!
	 * @author Daniel Hertzman-Ericson
	 *
	 */
	class ConnectNow extends Thread {
		public void run() {
			server.start(); 
			server = null;
		}
	}

}
