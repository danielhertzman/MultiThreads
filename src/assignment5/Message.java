package assignment5;

import java.io.Serializable;

/**
 * Message class that represent a message that can be sent
 * @author Daniel Hertzman-Ericson
 *
 */
public class Message implements Serializable {
	
	static final int USER = 0, MESSAGE = 1, LOGOUT = 2, OFFLINE = 3;
	private int type;
	private String content;
	private String[] recipent;
	private String sender;
	
	public Message(int type, String content, String[] recipent, String sender) {
		this.type = type;
		this.content = content;
		this.recipent = recipent;
		this.sender = sender;
	}
	
	public int getType() {
		return type;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getSender() {
		return sender;
	}

}
