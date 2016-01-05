package assignment3;

/**
 * Represents an object that is being produced by a producer
 * and then sent into a shared resource. In this case, the Storage
 * @author Daniel Hertzman-Ericson
 *
 */
public class FoodItem {
	
	private String name;
	private double weight;
	private double volume;
	
	public FoodItem(double volume, double weight, String name) {
		this.name = name;
		this.weight = weight;
		this.volume = volume;
	}

	public String getName() {
		return name;
	}


	public double getWeight() {
		return weight;
	}


	public double getVolume() {
		return volume;
	}

}
