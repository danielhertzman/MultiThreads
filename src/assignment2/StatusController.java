package assignment2;

public class StatusController {
	
	private GUIMutex gui;
	private String w = "";
	private String r = "";
	
	public StatusController(GUIMutex gui) {
		this.gui = gui;
	}
	
	public void setWLog(char c) {
		gui.setWriteList(c);
		w += c;
		gui.setTrans(w);
	}
	
	public void setRLog(char c) {
		gui.setReadList(c);
		r += c;
		gui.setRec(r);
	}
	
	public void setW(String txt) {
		w = txt;
	}
	
	public void setR(String txt) {
		r = txt;
	}

}
