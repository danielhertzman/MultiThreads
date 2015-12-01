package assignment2;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

/**
 * Reads the character from the buffer.
 * @author Daniel Hertzman-Ericson
 *
 */
public class Reader implements Observer, Runnable {

	private boolean sync;
	private char c;
	private CharacterBuffer cb;
	private StatusController controller;
	private int strLength;
	
	/**
	 * Constructor that receive instances
	 * @param mf
	 * @param cb
	 * @param c
	 */
	public Reader(MainForm mf, CharacterBuffer cb, StatusController c) {
		this.controller = c;
		strLength = mf.getTxt().length();
		sync = mf.isSync();
		this.cb = cb;
		cb.addObserver(this);
	}
	

	@Override
	public void run() {
		
		boolean running = true;
		int index = 0;
		Random rand = new Random();
		
		/*
		 * Making sure that we don't read more characters than the 
		 * text string contains. Also making sure that thread runs as long 
		 * it's not interrupted.
		 */
		while (index != strLength && running) {

			try {
				 Thread.sleep(rand.nextInt(500) + 200);
				
				if (sync) {
					
					/*
					 * If sync, we synchronize this block 
					 * and then wait until it's being notified. 
					 */
					synchronized (this) {
						c = cb.syncGet();
						controller.setRLog(c);
						wait();
					}
					
					/*
					 * Else we just get the element in a 
					 * normal way
					 */
				} else {
					c = cb.get();
					controller.setRLog(c);
				}
				
				index++;
				
			} catch (InterruptedException e) { running = false; }
		}
	}
	
	/**
	 * Returns the character
	 * @return the character
	 */
	public char getCharacter() {
		return c;
	}


	@Override
	public void update(Observable o, Object arg) {

		/*
		 * Waiting for the observable to release the object and then notifies
		 * this class to continue reading objects
		 */
		synchronized (this) {
			notifyAll();
		}
	}
}
