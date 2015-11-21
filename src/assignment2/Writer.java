package assignment2;

public class Writer implements Runnable {
	
	private boolean running;
	private MainForm mf;
	private boolean sync;
	private String txt;
	private char[] cArray;
	private CharacterBuffer cb;
	
	public Writer(MainForm mf) {
		this.mf = mf;
		sync = mf.isSync();
		txt = mf.getTxt();
		cArray = new char[txt.length()];
		System.out.println("synchronized: " + sync);
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
				Thread.sleep(300);
				
				if (sync) {
					syncRead(index);
				}

				else {
					asyncRead(index);
				}
				
				index++;
			} catch (InterruptedException e) { running = false; }
		}
	}

	private synchronized void syncRead(int index) {
		cb = new CharacterBuffer(cArray[index]);
	}
	
	private void asyncRead(int index) {
		cb = new CharacterBuffer(cArray[index]);
	}
}
