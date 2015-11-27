package assignment2;

import java.util.Observable;

public class CharacterBuffer extends Observable{
	
	private char c;
	
	public void put(char c) {
		this.c = c;
		setChanged();
		notifyObservers();
	}
	
	public char get() {
		setChanged();
		notifyObservers();
		return c;
	}
	
}
