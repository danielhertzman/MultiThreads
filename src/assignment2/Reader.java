package assignment2;

public class Reader implements Runnable {
	
	private boolean running;
	private MainForm mf;
	private boolean sync;
	private String txt;
	private char[] cArray;
	
	public Reader(MainForm mf) {
		this.mf = mf;
		sync = mf.isSync();
		txt = mf.getTxt();
		cArray = new char[txt.length()];
		
		for (int i = 0; i < txt.length(); i++) {
			cArray[i] = txt.charAt(i);
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
				Thread.sleep(500);
				System.out.println(cArray[index]);
				index++;
			} catch (InterruptedException e) { running = false; }
		}
	}

	private synchronized void syncRead() {
		
	}
	
	private void asyncRead() {
		
	}
}
