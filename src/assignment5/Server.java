package assignment5;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;


/**
 * Server side class
 * @author Daniel Hertzman-Ericson
 *
 */
public class Server {
	private static int uniqueId; 
	private ArrayList<ClientHandle> clientList;
	ArrayList<Message> offMsg = new ArrayList<Message>();
	private int port;
	private boolean keepOn;

	/**
	 * Constructor 
	 * 
	 * @param port
	 * @param gui
	 */
	public Server(int port, ServerUI gui) {
		this.port = port;
		clientList = new ArrayList<ClientHandle>();
	}

	/**
	 * Start new session and add clients to a list
	 */
	public void start() {
		keepOn = true;
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			System.out.println("Server up and running smooth!");
			while (keepOn) {
				Socket socket = serverSocket.accept(); // accept connection

				if (!keepOn)
					break;
				
				ClientHandle t = new ClientHandle(socket);
				clientList.add(t);
				t.start();
			}

			try {
				serverSocket.close();
				for (int i = 0; i < clientList.size(); ++i) {
					ClientHandle tc = clientList.get(i);
					try {
						tc.inputStream.close();
						tc.outputStream.close();
						tc.socket.close();
					} catch (IOException ioE) {}
				}
			} catch (Exception e) {}
		}

		catch (IOException e) {
		}
	}
	
	/**
	 * Handles messages
	 * @param message - Message obj
	 */
	private synchronized void broadcast(Message message) {
		Message messageLf = message;
		
		for (int i = clientList.size(); --i >= 0;) {
			ClientHandle ct = clientList.get(i);

			try {
				if (!ct.writeMsg(messageLf)) {
					clientList.remove(i);
					System.out.println("Disconnected Client " + ct.username
							+ " removed from list.");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Removes clients
	 * @param id
	 */
	synchronized void remove(int id) {
		for (int i = 0; i < clientList.size(); ++i) {
			ClientHandle ct = clientList.get(i);

			if (ct.id == id) {
				clientList.remove(i);
				return;
			}
		}
	}
	
	/**
	 * Handles clients and listens for messages
	 * @author Daniel Hertzman-Ericson
	 *
	 */
	class ClientHandle extends Thread {
		Socket socket;
		ObjectInputStream inputStream;
		ObjectOutputStream outputStream;
		int id;
		String username;
		Message chatMsg;
		LinkedList<String> userList = new LinkedList<String>();

		ClientHandle(Socket socket) {
			id = ++uniqueId;
			this.socket = socket;

			try {
				outputStream = new ObjectOutputStream(socket.getOutputStream());
				inputStream = new ObjectInputStream(socket.getInputStream());
				username = (String) inputStream.readObject();				
			}

			catch (IOException e) {
			} catch (ClassNotFoundException e) {
			}
		}

		public void run() {

			boolean keepOn = true;

			while (keepOn) {
				try {
					chatMsg = (Message) inputStream.readObject();
				}

				catch (IOException | ClassNotFoundException e) {
					break;	
				}
				
				switch (chatMsg.getType()) {
				case Message.MESSAGE:
					broadcast(new Message(Message.MESSAGE, username + ": "
							+ chatMsg.getMessage(), chatMsg.getRecipient(), chatMsg.getSender()));
					break;
				case Message.LOGOUT:
					keepOn = false; // lyssnar ej längre
					broadcast(new Message(Message.USER, username
							+ "updateTheUser", chatMsg.getRecipient(), chatMsg.getSender()));
					break;
				case Message.USER:
					System.out.println(offMsg.size());
	
					for (int i = 0; i < clientList.size(); ++i) {
						ClientHandle ct = clientList.get(i);
						broadcast(new Message(Message.USER, ct.username
								+ "USERNAME100", chatMsg.getRecipient(), chatMsg.getSender()));
					}
					
					if(offMsg.size() > 0){
						
						for (Message mezz : offMsg){
							for (String s : mezz.getRecipient()){
								if(s.equals(username)){
									broadcast(new Message(Message.MESSAGE,  mezz.getMessage(), mezz.getRecipient(), mezz.getSender()));
								}
							}
						}
					}
					
					break;
				case Message.OFFLINE:
					offMsg.add(chatMsg);
					System.out.println(offMsg.size()+" "+chatMsg.getRecipient());
					break;
				}
			}
			remove(id);
			close();

		}
		
		/**
		 * Closes
		 */
		private void close() {
			try {
				if (outputStream != null)
					outputStream.close();
			} catch (Exception e) {
			}
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (Exception e) {
			}
			;
			try {
				if (socket != null)
					socket.close();
			} catch (Exception e) {
			}
		}
		
		/**
		 * Writes messages to client
		 * @param msg
		 * @return
		 * @throws IOException
		 */
		private boolean writeMsg(Message msg) throws IOException {
			if (!socket.isConnected()) {
				socket.close();
				return false;
			}
			try {
				outputStream.writeObject(msg);
			} catch (IOException e) {
			}
			return true;
		}
	}
}
