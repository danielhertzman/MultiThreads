package assignment5;

import java.io.Serializable;


/**
 * General message class
 * @author Daniel Hertzman-Ericson
 *
 */
public class Message implements Serializable {
	static final int USER = 0, MESSAGE = 1, LOGOUT = 2, OFFLINE = 3;
	private int type;
	private String message;
	private String[] recipient;
	private String sender;
	
	Message(int type, String message, String[] recipient, String sender) {
		this.type = type;
		this.message = message;
		this.recipient = recipient;
		this.sender = sender;
	}
	
	/**
	 * Get message type
	 * @return type
	 */
	int getType() {
		return type;
	}
	
	/**
	 * Get message
	 * @return message
	 */
	String getMessage() {
		return message;
	}
	
	/**
	 * Get recipents
	 * @return recipient
	 */
	String[] getRecipient(){
		return recipient;
	}
	
	/**
	 * Get sender
	 * @return sender
	 */
	String getSender(){
		return sender;
	}

}
