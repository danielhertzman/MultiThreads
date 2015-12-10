package assignment3;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Factory implements Runnable {
	
	private Storage storage;
	private LinkedList<FoodItem> foodList;
	private Semaphore s;
	private Truck truck;
	
	public Factory() {
		s = new Semaphore(10);
		storage = new Storage(s);
		truck = new Truck(storage);
		produce();
	}

	public Storage getStorage() {
		return storage;
	}
	
	public void retrive() {
		truck.unLoad(); // temporary
	}
	
	@Override
	public void run() {
		new Thread(truck).start();
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
		
		for (FoodItem item : fi) {
			storage.addToStorage(item);
		}
	}
}
