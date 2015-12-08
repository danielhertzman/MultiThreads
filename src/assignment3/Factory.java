package assignment3;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Factory implements Runnable {
	
	private Storage storage;
	private LinkedList<FoodItem> foodList;
	private Semaphore s;
	
	public Factory() {
		foodList = new LinkedList<FoodItem>();
		s = new Semaphore(2);
		storage = new Storage(foodList, s);
		produce();
	}

	public Storage getStorage() {
		return storage;
	}
	
	@Override
	public void run() {
		new Thread(new Truck(storage)).start();
	}
	
	private void produce() {
		FoodItem[] fi = new FoodItem[10];
		fi[0] = new FoodItem("Milk", 1, 1);
		fi[1] = new FoodItem("Eggs", 2, 2);
		fi[2] = new FoodItem("Meat", 3, 3);
		fi[3] = new FoodItem("Cheese", 4, 4);
		fi[4] = new FoodItem("Pasta", 5, 5);
		fi[5] = new FoodItem("Rice", 6, 6);
		fi[6] = new FoodItem("Yolo", 7, 7);
		fi[7] = new FoodItem("Beans", 8, 8);
		fi[8] = new FoodItem("Banana", 9, 9);
		fi[9] = new FoodItem("Swag", 10, 10);
//		fi[10] = new FoodItem("Apple", 11, 11);
		
		for (FoodItem item : fi) {
			storage.addToStorage(item);
		}
	}
}
