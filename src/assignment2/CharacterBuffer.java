package assignment2;

import java.util.Observable;

public class CharacterBuffer extends Observable{
	
	private char c;
	
	public void syncPut(char c) {
		this.c = c;
		setChanged();
		notifyObservers();
	}
	
	public char syncGet() {
		setChanged();
		notifyObservers();
		return c;
	}
	
	public void put(char c) {
		this.c = c;
	}
	
	public char get() {
		return c;
	}
	
}
