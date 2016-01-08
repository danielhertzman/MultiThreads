package assignment3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

/**
 * The shared resource between the producer and consumer
 * 
 * @author Daniel Hertzman-Ericson
 *
 */
public class Storage {

	private Controller controller;
	private Queue<FoodItem> foodItems = new LinkedList<>();
	private boolean go;
	private int nbrOfItems;
	private Semaphore removeSemaphore;
	private Semaphore addSemaphore;

	public Storage(Controller controller) {
		this.controller = controller;
		addSemaphore = new Semaphore(40);
		removeSemaphore = new Semaphore(0);

	}

	/*
	 * Add food items to the que
	 */
	public void add(FoodItem foodItem) {
		try {
			addSemaphore.acquire();
		} catch (InterruptedException e) {}

		synchronized (this) {
			foodItems.add(foodItem);
			nbrOfItems++;
			setBufferStatus(nbrOfItems);

		}
		removeSemaphore.release();
	}

	/*
	 * Remove food items to the que
	 */
	public void remove() {
		try {
			removeSemaphore.acquire();
		} catch (InterruptedException e) {}

		synchronized (this) {
			nbrOfItems--;
			setBufferStatus(nbrOfItems);
			controller.work(1);
			foodItems.remove();
		}

		addSemaphore.release();

	}

	/*
	 * Checks if the que is empty
	 */
	public boolean isEmpty() {
		return foodItems.isEmpty();
	}
	/*
	 * Return the current item for examine
	 */

	public FoodItem element() {
		return foodItems.element();
	}

	/*
	 * Changes the bufferStatusbar
	 */
	private void setBufferStatus(int nbr) {
		controller.setBufferStatus(nbr);
	}

	/*
	 * Return nbr so we can check in the factory if it is time to stop
	 */
	public int getNbrOfItems() {
		return nbrOfItems;
	}

	public boolean getGo() {
		return go;
	}

	/*
	 * These method decides if the truck can pick up items or not
	 */
	public void setGo(boolean go) {
		this.go = go;
	}

}
