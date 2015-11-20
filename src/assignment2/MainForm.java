package assignment2;

public class MainForm {
	
	private boolean sync;
	private String txt;
	private Thread tSync;
	private Thread tNotSync;
	
	public MainForm(String txt, boolean sync) {
		this.txt = txt;
		this.sync = sync;
		
		initialize();
	}
	
	private void initialize() {
		
		if (sync) {
			tSync = new Thread(new Reader(this));
			tSync.start();
		} else {
			tNotSync = new Thread(new Reader(this));
			tNotSync.start();
		}
		
	}

	public boolean isSync() {
		return sync;
	}

	public String getTxt() {
		return txt;
	}
}
