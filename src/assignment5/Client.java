package assignment5;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 *Client side class
 * 
 * @author Daniel Hertzman-Ericson
 *
 */
public class Client {
	private String username;
	private int port;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;
	private Socket socket;
	private ClientUI client;

	/**
	 * Constructor
	 * @param username
	 * @param client
	 */
	public Client(int port, String username, ClientUI client) {
		this.username = username;
		this.client = client;
		this.port = port;
	}

	/**
	 * Connects to server and creats I/O
	 * 
	 * @return
	 */
	public boolean start() {

		try {
			socket = new Socket("127.0.0.1", port);			
			inputStream = new ObjectInputStream(socket.getInputStream());
			outputStream = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException eIO) {}

		new ServerListener().start();

		try {
			outputStream.writeObject(username);
		} catch (IOException eIO) {}

		return true;
	}

	/**
	 * Send messages
	 * @param msg
	 */
	void sendMessage(Message msg) {
		try {
			outputStream.writeObject(msg);
			outputStream.flush();
		} catch (IOException e) {}
	}

	/**
	 * Thread that waits for messages
	 * 
	 * @author Daniel Hertzman-Ericson
	 */
	class ServerListener extends Thread {
		ArrayList<String> arr;
		Set<String> set = new HashSet<String>();
		Random rand = new Random();
		private SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");

		public void run() {

			while (true) {
				try {

					Message msg = (Message) inputStream.readObject();

					if (client == null) {
						System.out.println(msg);
						System.out.print("> ");
					} else {

						if (msg.getMessage() != null) {

							String message = null;


							if (msg.getMessage() != null) {
								message = msg.getMessage();
							}

							if (message.contains("USERNAME100")) {
								// Splits away USERNAME100
								message = message.split("USERNAME100")[0];
								// adds to set
								set.add(message);
								arr = new ArrayList<String>(set);
								client.updateUsers(arr);
				

							} else if (message.contains("updateTheUser")) {
								String split = message.split("updateTheUser")[0];
								set.remove(split);
								arr = new ArrayList<String>(set);
								client.updateUsers(arr);
							} else {
								
								
								
								for(String str : msg.getRecipient()){
									if(str.equals("")){
										String time = df.format(new Date());
										String messageLf = time + " " + message
												+ "\n";
										client.append(messageLf);
									}
									
									if (arr.contains(str)) {
										String time = df.format(new Date());
										String messageLf = time + " " + message
												+ "\n";
										client.privateAppend(messageLf,
												str, msg.getSender());
									}
									
									if(!arr.contains(str) && !str.equals("")){
										String[] rec = msg.getRecipient();
										String mess = msg.getMessage();
										String sender = msg.getSender();
										sendMessage(new Message(Message.OFFLINE, mess, rec, sender));
									}
									
								}
								
							}
						}

					}
				} catch (IOException e) {
				}

				catch (ClassNotFoundException e2) {
					System.out.println("Något gick fel!");
				}
			}
		}
	}

}
