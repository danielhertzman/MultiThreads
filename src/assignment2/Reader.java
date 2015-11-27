package assignment2;

import java.util.Observable;
import java.util.Observer;

public class Reader implements Observer, Runnable {
	
	private MainForm mf;
	private String txt;
	private char[] cArray;
	private boolean sync;
	private CharacterBuffer cb;
	
	public Reader(MainForm mf) {
		this.mf = mf;
		sync = mf.isSync();
//		txt = mf.getTxt();
		cArray = new char[mf.getTxt().length()];
		for (int i = 0; i < mf.getTxt().length(); i++) {
			cArray[i] = mf.getTxt().charAt(i);
		}
	}
	

	@Override
	public void run() {
		
		boolean running = true;
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
