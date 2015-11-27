package assignment2;

import java.util.Observable;
import java.util.Observer;

public class Reader implements Observer, Runnable {

	private boolean sync;
	private char c;
	private CharacterBuffer cb;
	
	public Reader(MainForm mf, CharacterBuffer cb) {
		sync = mf.isSync();
		this.cb = cb;
	}
	

	@Override
	public void run() {
		
		boolean running = true;
		
		while (running) {

			try {
				Thread.sleep(300);
				
				if (sync) {
					
					synchronized (cb) {
						c = cb.get();
					}
					
				} else {
					c = cb.get();
				}
				
				System.out.println(c);
			} catch (InterruptedException e) { running = false; }
		}
		
	}
	
	public char getCharacter() {
		return c;
	}


	@Override
	public void update(Observable o, Object arg) {
		synchronized(cb) {
			notifyAll();
		}
	}

}
