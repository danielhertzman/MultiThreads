package assignment5;

import java.io.*;
import java.net.*;

public class Client implements Runnable {
	
	private String userName;
	private Socket socket;
	private ClientUI ui;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private boolean running;
	
	public Client(ClientUI ui, String userName) {
		this.ui = ui;
		this.userName = userName;
	}

	@Override
	public void run() {
		running = true;
		try {
			
			while (running) {
				socket = new Socket("localhost", 3000);
				oos = new ObjectOutputStream(socket.getOutputStream());
				ois = new ObjectInputStream(socket.getInputStream());
				System.out.println("Client connected");
				if (ui.ok()) {
					try {
						if (ois.readObject() != null) {
							Controller c = new Controller();
							new Thread(c).start();
							System.out.println(ois.readObject());
							ui.append(c.getMessage(), userName);
						}
					} catch (ClassNotFoundException e) {}
				}
			}
		} catch (IOException e) {} 
	}
	
	private class Controller implements Runnable {
		
		private String recievedMsg;

		@Override
		public void run() {

			send();
			read();

		}
		
		public void send() {
			try {
				oos.writeObject(ui.getMessage());
				oos.writeObject(userName);
				oos.flush();
			} catch (IOException e) {}
		}
		
		public void read() {
			try {
				recievedMsg = (String) ois.readObject();
				
			} catch (ClassNotFoundException | IOException e) {}
		}
		
		public String getMessage() {
			return recievedMsg;
		}
	}
}
