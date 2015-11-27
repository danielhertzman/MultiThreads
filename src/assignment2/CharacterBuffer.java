package assignment2;

import java.util.Observable;

public class CharacterBuffer extends Observable{
	private Writer r;
	private Reader w;
	private char current;
	private boolean sync;
	
	public CharacterBuffer(char current, boolean sync) {
		this.sync = sync;
		this.current = current;
		System.out.println(current);
	}
	
	public char getCurrent() {
		return current;
	}
	
}
