package assignment3;

/**
 * The consumer in a prod/cons model
 * @author Daniel Hertzman-Ericson
 *
 */
public class Truck implements Runnable {

	private final Storage storage;
	private final Controller controller;
	private double maxVolume;
	private double maxWeight;
	private int nbr = 0;

	public Truck(Storage storage, Controller controller) {
		this.storage = storage;
		this.controller = controller;
	}

	/*
	 * This method is the logic for the Truck
	 */
	public void run() {
		while (!Thread.interrupted()) {
			try {
				Thread.sleep(300);
				if (storage.isEmpty()) {
					doing(2);
				} else if (!storage.isEmpty()) {

					checkMax();
				}
			} catch (InterruptedException e) {
				break;
			}
		}
	}

	/*
	 * Runs until the truck is fully loaded.
	 */
	private void checkMax() throws InterruptedException {
		if (maxVolume < 30 || maxWeight < 30 || nbr < 20) {

			if (storage.getGo()) {
				doing(1);
			} else if (!storage.getGo()) {
				doing(2);
			}

			nbr++;
			setWeight();
			setVolume();
			setProductName();
			setNbrOfItems();
			storage.remove();
		}
		if (maxVolume >= 30 || maxWeight >= 30 || nbr >= 20) {
			pickUp();
		}
	}

	/*
	 * When the truck is loaded this method resets the trucks value and make the
	 * Thread sleep for 5sec.
	 */
	private void pickUp() throws InterruptedException {
		maxVolume = 0;
		maxWeight = 0;
		nbr = 0;
		doing(3);
		controller.truckDone();

		Thread.sleep(5000);

	}

	/*
	 * Count the weight.
	 */
	private void setWeight() {
		maxWeight += storage.element().getWeight();
		controller.setWeight(maxWeight);
	}

	/*
	 * Count the volume.
	 */
	private void setVolume() {
		maxVolume += storage.element().getVolume();
		controller.setVolume(maxVolume);
	}

	/*
	 * Sends the name of the item to the controller class.
	 */
	private void setProductName() {
		controller.setName(storage.element().getName());

	}

	/*
	 * Count the items.
	 */
	private void setNbrOfItems() {
		controller.setNbrOfItems(nbr);
	}

	/*
	 * Call the method work that have a some switch statements in it.
	 */
	private void doing(int work) {
		controller.work(work);
	}

}
