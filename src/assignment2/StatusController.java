package assignment2;

/**
 * Controller class for the statuses in the GUIMutex
 * 
 * @author Daniel Hertzman-Ericson
 *
 */
public class StatusController {
	
	private GUIMutex gui;
	private String w = "";
	private String r = "";
	
	/**
	 * Constructor that recieves the gui
	 * @param gui
	 */
	public StatusController(GUIMutex gui) {
		this.gui = gui;
	}
	
	/**
	 * Sets the writer log
	 * @param c
	 */
	public void setWLog(char c) {
		gui.setWriteList(c);
		w += c;
		gui.setTrans(w);
	}
	
	/**
	 * Sets the reader log
	 * @param c
	 */
	public void setRLog(char c) {
		gui.setReadList(c);
		r += c;
		gui.setRec(r);
	}
	
	/**
	 * Sets the writer string
	 * @param txt
	 */
	public void setW(String txt) {
		w = txt;
	}
	
	/**
	 * Sets the reader string
	 * @param txt
	 */
	public void setR(String txt) {
		r = txt;
	}

}
