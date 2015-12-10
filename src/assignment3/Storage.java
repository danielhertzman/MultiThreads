package assignment3;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Storage {
	
	private LinkedList<FoodItem> list;
	private Semaphore s;
	
	public Storage(Semaphore s) {
		this.list = new LinkedList<FoodItem>();
		this.s = s;
	}
	
	public void addToStorage(FoodItem item) {
//		s.acquire();
		list.add(item);
//		s.release();
	}
	
	public FoodItem removeFromStorage() {
		return list.removeFirst();
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
}
