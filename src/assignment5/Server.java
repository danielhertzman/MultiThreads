package assignment5;

import java.io.*;
import java.net.*;

public class Server implements Runnable {
	
	private int port;
//	private String ip;
	private ServerSocket ss;
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private boolean running;
	
	public Server(int port) {
		this.port = port;
//		this.ip = ip;
	}

	@Override
	public void run() {
		try {
			running = true;
			ss = new ServerSocket(port);
			System.out.println("server running");
			
			while (running) {
				socket = ss.accept();
				ois = new ObjectInputStream(socket.getInputStream());
				oos = new ObjectOutputStream(socket.getOutputStream());
				System.out.println(ois.readObject());
			}
		} catch (Exception e) { running = false; System.out.println("fel");}
		
		try {
			ss.close();
			ois.close();
			oos.close();
			System.out.println("server closed");
		} catch (Exception e) {}
		
	}
	
	public boolean isRunning()  {
		return running;
	}
	
	
}
