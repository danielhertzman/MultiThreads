package assignment3;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Storage {
	
	private LinkedList<FoodItem> list = new LinkedList<FoodItem>();
	private Semaphore s;
	
	public Storage(LinkedList<FoodItem> list, Semaphore s) {
		this.list = list;
		this.s = s;
		
	}
	
	public void addToStorage(FoodItem item) {
		list.add(item);
	}
	
	public FoodItem removeFromStorage() {
		return list.remove();
	}
}
