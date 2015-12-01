package assignment2;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class Reader implements Observer, Runnable {

	private boolean sync;
	private char c;
	private CharacterBuffer cb;
	private StatusController controller;
	private int strLength;
	
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
		
		while (index != strLength && running) {

			try {
				Thread.sleep(200 + rand.nextInt(500));
				
				if (sync) {
					
					synchronized (this) {
						c = cb.syncGet();
						controller.setRLog(c);
						wait();
					}
					
				} else {
					c = cb.get();
					controller.setRLog(c);
				}
				
				index++;
				
			} catch (InterruptedException e) { running = false; }
		}
		
	}
	
	public char getCharacter() {
		return c;
	}


	@Override
	public void update(Observable o, Object arg) {

		synchronized (this) {
			notifyAll();

		}
	}

}
