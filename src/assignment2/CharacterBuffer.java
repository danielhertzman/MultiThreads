package assignment2;

import java.util.Observable;

/**
 * Buffer that holds and returns a character
 * 
 * @author Daniel Hertzman-Ericson
 *
 */
public class CharacterBuffer extends Observable{
	
	private char c;
	
	/**
	 * If sync, we notify the observers
	 * that the character as been written
	 * @param c
	 */
	public void syncPut(char c) {
		this.c = c;
		setChanged();
		notifyObservers();
	}
	
	/**
	 * If sync, we notify the observers
	 * that the character has been written
	 * @return
	 */
	public char syncGet() {
		setChanged();
		notifyObservers();
		return c;
	}
	
	/**
	 * If not sync, simply puts the character into the buffer
	 * @param c
	 */
	public void put(char c) {
		this.c = c;
	}
	
	/**
	 * If not sync, simply returns the character
	 * @return
	 */
	public char get() {
		return c;
	}
	
}
