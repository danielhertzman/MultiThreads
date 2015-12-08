package assignment3;

import java.util.LinkedList;
import java.util.Queue;

public class Truck implements Runnable {
	
	private Storage s;
	private Queue<FoodItem> q = new LinkedList<FoodItem>();
	private boolean full = false;
	private int currentLoad = 0;
	private static final int CAPACITY = 10;
	
	public Truck(Storage s) {
		this.s = s;
	}
	
	@Override
	public void run() {
		
		boolean running = true;
		
		try {
			Thread.sleep(200);
			while (running) {
				fill(s.removeFromStorage());
			}
		} catch(InterruptedException e) { running = false; }
	} 
	
	private void fill(FoodItem item) {
		while (!q.isEmpty() && !full) {
			q.add(item);
			currentLoad++;
			if (currentLoad == CAPACITY) full = true; System.out.println("now full");
		}
	}
	
	public int getCurrentLoad() {
		return currentLoad;
	}
	
	public boolean isFull() {
		return full;
	}
}
