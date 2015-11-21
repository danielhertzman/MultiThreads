package assignment2;

public class CharacterBuffer {
	private Writer r;
	private Reader w;
	private char current;
	
	public CharacterBuffer(char current) {
		this.current = current;
		System.out.println(current);
	}
	
	public char getCurrent() {
		return current;
	}
	
}
