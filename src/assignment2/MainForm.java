package assignment2;

/**
 * Main form class. Holds information about the 
 * input text string. Starts the both Reader and
 * Writer thread.
 * @author Daniel Hertzman-Ericson
 *
 */
public class MainForm {
	
	private boolean sync;
	private String txt;
	private Thread writeT;
	private Thread readT;
	private StatusController controller;
	
	/*
	 * Constructor.
	 */
	public MainForm(String txt, boolean sync, StatusController controller) {
		this.controller = controller;
		this.txt = txt;
		this.sync = sync;
		initialize();
	}
	
	/**
	 * Initializes the threads
	 */
	private void initialize() {
		CharacterBuffer cb = new CharacterBuffer();
		readT = new Thread(new Writer(this, cb, controller));
		writeT = new Thread(new Reader(this, cb, controller));
		writeT.start();
		readT.start();
	}
	
	/**
	 * Terminates the running threads 
	 */
	public void terminate() {
		
		if (writeT.isAlive() && readT.isAlive()) {
			writeT.interrupt();
			readT.interrupt();
		}
		
	}

	/**
	 * Return true if sync
	 * @return true if sync
	 */
	public boolean isSync() {
		return sync;
	}

	/**
	 * Returns the text String
	 * @return the text
	 */
	public String getTxt() {
		return txt;
	}
}
