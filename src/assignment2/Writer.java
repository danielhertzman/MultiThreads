package assignment2;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class Writer implements Observer, Runnable {
	
	private boolean running;
	private boolean sync;
	private String txt;
	private char[] cArray;
	private CharacterBuffer cb;
	private StatusController controller;
	
	public Writer(MainForm mf, CharacterBuffer cb, StatusController c) {
		this.controller = c;
		sync = mf.isSync();
		this.cb = cb;
		txt = mf.getTxt();
		cb.addObserver(this);
		cArray = new char[mf.getTxt().length()];
		
		for (int i = 0; i < mf.getTxt().length(); i++) {
			cArray[i] = txt.charAt(i);
		}
	}
	
	public String getTxt() {
		return txt;
	}

	/**
	 * 
	 */
	@Override
	public void run() {
		running = true;
		int index = 0;
		Random rand = new Random();
		
		while (index != cArray.length && running) {

			try {
				Thread.sleep(100 + rand.nextInt(500));
				controller.setWLog(cArray[index]);
				
				if (sync) {
					
					synchronized (this) {
						cb.syncPut(cArray[index]);
						wait();
					}	
					
				} else {
					cb.put(cArray[index]);
				}
				
				index++;
				
			} catch (InterruptedException e) { running = false; }
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		
		synchronized(this) {
			notifyAll();
		}
		
	}
}
