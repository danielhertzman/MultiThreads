package assignment2;

public class MainForm {
	
	private boolean sync;
	private String txt;
	
	public MainForm(String txt, boolean sync) {
		this.txt = txt;
		this.sync = sync;
		System.out.println(txt + " " + sync);
	}

	public boolean isSync() {
		return sync;
	}

	public String getTxt() {
		return txt;
	}
}
