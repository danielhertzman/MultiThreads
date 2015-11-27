package assignment2;

public class MainForm {
	
	private boolean sync;
	private String txt;
	private Thread writeT;
	private Thread readT;
	private Thread rNotSync;
	private Thread wNotSync;
	
	public MainForm(String txt, boolean sync) {
		this.txt = txt;
		this.sync = sync;
		initialize();
	}
	
	private void initialize() {
		
		readT = new Thread(new Writer(this));
		writeT = new Thread(new Reader(this));
		readT.start();
		writeT.start();
		
	}

	public boolean isSync() {
		return sync;
	}

	public String getTxt() {
		return txt;
	}
}
