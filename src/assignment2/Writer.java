package assignment2;

import java.util.Observable;
import java.util.Observer;

public class Writer implements Observer, Runnable {
	
	private boolean running;
	private MainForm mf;
	private boolean sync;
	private String txt;
	private char[] cArray;
	private CharacterBuffer cb;
	
	public Writer(MainForm mf) {
		this.mf = mf;
		sync = mf.isSync();
//		txt = mf.getTxt();
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
					synchronized (cArray) {
						cb = new CharacterBuffer(cArray[index], sync);
					}
				} else {
					cb = new CharacterBuffer(cArray[index], sync);
				}
				
				
				index++;
			} catch (InterruptedException e) { running = false; }
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
