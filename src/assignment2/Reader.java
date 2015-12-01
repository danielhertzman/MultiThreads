package assignment2;

import java.util.Observable;
import java.util.Observer;

public class Reader implements Observer, Runnable {

	private boolean sync;
	private char c;
	private CharacterBuffer cb;
	private Controller controller;
	
	public Reader(MainForm mf, CharacterBuffer cb, Controller c) {
		this.controller = c;
		sync = mf.isSync();
		this.cb = cb;
		cb.addObserver(this);
	}
	

	@Override
	public void run() {
		
		boolean running = true;
		
		while (running) {

			try {
				Thread.sleep(300);
				
				if (sync) {
					
					synchronized (this) {
						c = cb.syncGet();
						controller.setRLog(c);
						this.wait();
					}
					
				} else {
					c = cb.get();
					controller.setRLog(c);
				}
				
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
