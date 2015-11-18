package assignment2;

public class Reader implements Runnable {
	
	private char c;
	private boolean running;
	
	public Reader() {}
	
	public void read(char c) {
		this.c = c;
		
		// do stuff	
	}
	
	public char getCharacter() {
		return c;
	}

	@Override
	public void run() {
		running = true;
		
		while (running) {

			try {
				Thread.sleep(500);
				System.out.println("yolo");
				
			} catch (InterruptedException e) {
				running = false;
			}
		}
		
	}
}
