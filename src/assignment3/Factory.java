package assignment3;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Factory {
	
	private Storage storage;
	private LinkedList<FoodItem> foodList;
	private Semaphore s;
	
	public Factory() {
		foodList = new LinkedList<FoodItem>();
		initFoodItems();
	}
	
	public void addIntoList(FoodItem item) {
		foodList.add(item);
	}

	public Storage getStorage() {
		return storage;
	}

	public LinkedList<FoodItem> getFoodList() {
		return foodList;
	}
	
	private void initFoodItems() {
		
		FoodItem[] fi = new FoodItem[10];
		fi[0] = new FoodItem("Milk", 1, 1);
		
	}
	
}
