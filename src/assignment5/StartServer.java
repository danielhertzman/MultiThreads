package assignment5;

public class StartServer {
	
	public static void main(String[] args) {
		Server server = new Server(3000);
		new Thread(server).start();
	}

}
