package assignment2;

public class Controller {
	
	private GUIMutex gui;
	
	public Controller(GUIMutex gui) {
		this.gui = gui;
	}
	
	public void setWLog(char c) {
		gui.setWriteList(c);
	}
	
	public void setRLog(char c) {
		gui.setReadList(c);
	}

}
