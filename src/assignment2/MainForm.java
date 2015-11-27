package assignment2;

public class MainForm {
	
	private boolean sync;
	private String txt;
	private Thread writeT;
	private Thread readT;
	
	public MainForm(String txt, boolean sync) {
		this.txt = txt;
		this.sync = sync;
		initialize();
	}
	
	private void initialize() {
		CharacterBuffer cb = new CharacterBuffer();
		readT = new Thread(new Writer(this, cb));
		writeT = new Thread(new Reader(this, cb));
		writeT.start();
		readT.start();
		
	}

	public boolean isSync() {
		return sync;
	}

	public String getTxt() {
		return txt;
	}
}
