package assignment3;

/**
 * Controller class for the program
 * @author Daniel Hertzman-Ericson
 *
 */
public class Controller {
	
	private final GUISemaphore gui;
	private final Storage storage;
	private Thread t1;
	private Thread t2;
	private boolean t1Running = false;
	private boolean t2Running = false;
	private Factory factory1;
	private Factory factory2;

	/*
	 * Constructor that receives a gui item and makes a new Semaphore and a
	 * storage
	 */
	public Controller(GUISemaphore gui) {
		this.gui = gui;
		storage = new Storage(this);
	}

	/**
	 * Starting the first factory
	 */
	public void startFirstFactory() {
		t1 = new Thread(factory1 = new Factory(storage, this));
		t1.start();
		factory1.setRunning(true);
		t1Running = true;
		factoryResting(2);
	}

	/**
	 * Starting the second factory
	 */
	public void startSecondFactory() {
		t2 = new Thread(factory2 = new Factory(storage, this));
		t2.start();
		factory2.setRunning(true);
		t2Running = true;
		factoryResting(4);
	}

	/*
	 * Start the delivery thread
	 */
	public void startDeliver() {
		Thread thread3 = new Thread(new Truck(storage, this));
		thread3.start();
	}
	/*
	 * Stops the producer thread A
	 */

	public void stopProducerA() {
		factory1.setRunning(false);
		t1.interrupt();
		factoryResting(1);
		t1Running = false;
	}

	/*
	 * Start the producer thread B
	 */
	public void stopProducerB() {
		factory2.setRunning(false);
		factoryResting(3);
		t2Running = false;
	}

	/*
	 * Sets number of items in the gui class
	 */
	public void setNbrOfItems(int numberOfITEMS) {
		gui.setLblLimNrs("Items " + numberOfITEMS);
	}

	/*
	 * Sets weight in the gui class
	 */
	public void setWeight(double weight) {
		gui.setLblLimWeight("Weight " + weight);
	}

	/*
	 * Sets volume in the gui class
	 */
	public void setVolume(double volume) {
		gui.setLblLimVolume("Volume " + volume);
	}

	/*
	 * Append names in the gui class
	 */
	public void setName(String name) {
		gui.setLstTruck(name + "\n");
	}

	/*
	 * Changes the bufferStatusBar
	 */
	public void setBufferStatus(int value) {
		gui.setBufferStatus(value);
	}

	/*
	 * Sets labels to their correct activity
	 */
	public void work(int work) {
		switch (work) {

		case 1:
			gui.setLblTruckStatus("Loading...");
			gui.setLblDeliver("");
			break;

		case 2:
			gui.setLblTruckStatus("Waiting...");
			break;

		case 3:
			gui.setLblTruckStatus("Truck limited by weight...");
			gui.setLblDeliver("Truck Delivering");
			break;

		}
	}

	/*
	 * Empty the items list with all the foodItems
	 */
	public void truckDone() {
		gui.empty();
	}

	/*
	 * Changes the label for the factory to it´ correct activity
	 */
	public void factoryResting(int b) {
		switch (b) {
		
		case 1:
			gui.setLblAStatus("Producer: Resting");
			break;

		case 2:
			gui.setLblAStatus("Producer: Working");
			break;

		case 3:
			gui.setLblBStatus("Producer: Resting");
			break;

		case 4:
			gui.setLblBStatus("Producer: Working");
			break;
		}
	}

	/*
	 * Changes the working and resting label for factory 1
	 */
	public boolean isT1Running() {
		return t1Running;
	}

	/*
	 * Changes the working and resting label for factory 1
	 */
	public boolean isT2Running() {
		return t2Running;
	}
}
