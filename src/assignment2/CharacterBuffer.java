package assignment2;

public class CharacterBuffer {
	private Reader r;
	private Writer w;
	private char current;
	
	public CharacterBuffer(char current) {
		this.current = current;
		System.out.println(current);
	}
	
	public char getCurrent() {
		return current;
	}
	
}
