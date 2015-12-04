package assignment3;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Factory {
	
	private Storage storage;
	private LinkedList<FoodItem> foodList;
	private Semaphore s;
	
	public Factory() {
		foodList = new LinkedList<FoodItem>();
		s = new Semaphore(2);
		storage = new Storage(foodList, s);
		initFoodItems();
	}

	public Storage getStorage() {
		return storage;
	}
	
	private void initFoodItems() {
		FoodItem[] fi = new FoodItem[10];
		fi[0] = new FoodItem("Milk", 1, 1);
		fi[1] = new FoodItem("Eggs", 2, 2);
	}
}
