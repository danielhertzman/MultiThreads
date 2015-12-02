package assignment3;

public class FoodItem {
	
	private String name;
	private int weight;
	private int volume;
	
	public FoodItem(String name, int weight, int volume) {
		this.name = name;
		this.weight = weight;
		this.volume = volume;
	}

	public String getName() {
		return name;
	}


	public int getWeight() {
		return weight;
	}


	public int getVolume() {
		return volume;
	}

}
