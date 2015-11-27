package assignment2;

import java.util.Observable;
import java.util.Observer;

public class Writer implements Observer, Runnable {
	
	private boolean running;
	private boolean sync;
	private String txt;
	private char[] cArray;
	private CharacterBuffer cb;
	
	public Writer(MainForm mf, CharacterBuffer cb) {
		sync = mf.isSync();
		this.cb = cb;
		cArray = new char[mf.getTxt().length()];
		System.out.println("synchronized: " + sync);
		for (int i = 0; i < mf.getTxt().length(); i++) {
			cArray[i] = mf.getTxt().charAt(i);
		}
	}
	
	public String getTxt() {
		return txt;
	}

	@Override
	public void run() {
		running = true;
		int index = 0;
		
		while (index != cArray.length && running) {

			try {
				Thread.sleep(300);
				
				if (sync) {
					
					synchronized (cb) {
						cb.put(cArray[index]);
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
		synchronized(cb) {
			notifyAll();
		}
		
	}
}
