package assignment2;

public class MainForm {
	
	private Reader r;
	private Writer w;
	
	public MainForm() {
		r = new Reader();
		new Thread(r).start();
	}
	
	public static void main(String[] args) {
		new MainForm();
	}

}
