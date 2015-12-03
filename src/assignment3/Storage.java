package assignment3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Storage {
	
	private Queue<FoodItem> q = new LinkedList<FoodItem>();
	/*
	 * Do something with this thingy
	 */
	private Semaphore s;
	
	public Storage(Semaphore s) {
		this.s = s;
	}
	
	public void addToStorage(FoodItem item) {
		q.add(item);
	}
	
	public FoodItem removeFromStorage() {
		return q.remove();
	}
}
