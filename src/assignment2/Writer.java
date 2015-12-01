package assignment2;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

/**
 * Writer class that writes every character
 * in the text string and then send it to
 * the buffer
 * 
 * @author Daniel Hertzman-Ericson
 *
 */
public class Writer implements Observer, Runnable {
	
	private boolean sync;
	private String txt;
	private char[] cArray;
	private CharacterBuffer cb;
	private StatusController controller;
	
	/**
	 * Constructor that receives instances
	 * @param mf
	 * @param cb
	 * @param c
	 */
	public Writer(MainForm mf, CharacterBuffer cb, StatusController c) {
		this.controller = c;
		sync = mf.isSync();
		this.cb = cb;
		txt = mf.getTxt();
		cb.addObserver(this);
		cArray = new char[mf.getTxt().length()];
		
		/*
		 * Creating a char array of the text string
		 */
		for (int i = 0; i < mf.getTxt().length(); i++) {
			cArray[i] = txt.charAt(i);
		}
	}
	
	/**
	 * Returns the text 
	 * @return the text
	 */
	public String getTxt() {
		return txt;
	}

	/**
	 * 
	 */
	@Override
	public void run() {
		
		boolean running = true;
		int index = 0;
		Random rand = new Random();
		
		/*
		 * Runs as long as index value doesn't exceed the 
		 * value of the char array's length. Also breaks the loop
		 * if the thread is being interrupted.
		 */
		while (index != cArray.length && running) {

			try {
				Thread.sleep(rand.nextInt(400) + 100);
				controller.setWLog(cArray[index]);
				
				if (sync) {
					
					/*
					 * If sync, we put a character from the 
					 * text into a special put in buffer.
					 * Then we wait, in order for the buffer to process
					 * the character.
					 */
					synchronized (this) {
						cb.syncPut(cArray[index]);
						wait();
					}	
					
					/*
					 * Else we simply put the 
					 * character in the buffer.
					 */
				} else {
					cb.put(cArray[index]);
				}
				
				index++;
				
			} catch (InterruptedException e) { running = false; }
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		
		/*
		 * Breaks the waiting and notifies the 
		 * class to continue writing character
		 * objects.
		 */
		synchronized(this) {
			notifyAll();
		}
		
	}
}
