package assignment3;

import java.util.LinkedList;
import java.util.Queue;

public class Truck implements Runnable {
	
	private Storage s;
	private Queue<FoodItem> q = new LinkedList<FoodItem>();
	private static final int CAPACITY = 10;
	
	public Truck(Storage s) {
		this.s = s;
	}

	@Override
	public void run() {
		
		boolean running = true;
		int nbrOfItems = 0;
		
		try {
			Thread.sleep(200);
			while (running && nbrOfItems != CAPACITY) {
				q.add(s.removeFromStorage());
				nbrOfItems++;
			}
		} catch(InterruptedException e) { running = false; }
	} 
}
