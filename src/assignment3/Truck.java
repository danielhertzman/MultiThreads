package assignment3;

import java.util.LinkedList;
import java.util.Queue;

public class Truck implements Runnable {
	
	private Storage s;
	private LinkedList<FoodItem> q = new LinkedList<FoodItem>();
	private boolean full = false;
	private int currentIndex = 0;
	private static final int CAPACITY = 10;
	
	public Truck(Storage s) {
		this.s = s;
	}
	
	@Override
	public void run() {
		
		boolean running = true;
		
		try {
			while (running && !s.isEmpty() && !full) {
				Thread.sleep(200);
				load(s.removeFromStorage());
			}
		} catch(InterruptedException e) { running = false; }
	} 
	
	private void load(FoodItem item) {
		q.add(item);
		System.out.println(q.get(currentIndex).getName());
		currentIndex++;

		if (currentIndex == CAPACITY) {
			full = true;
			System.out.println("now full");
		}
	}
	
	public FoodItem unLoad() {
		return q.removeFirst(); // temporary
	}
	
	public int getNumberOfElements() {
		return currentIndex;
	}
	
	public boolean isFull() {
		return full;
	}
}
