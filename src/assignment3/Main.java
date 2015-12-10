package assignment3;

public class Main 
{
	public static void main(String[] args)
	{
		Factory a = new Factory();
		Factory b = new Factory();
		GUISemaphore test = new GUISemaphore(a,b);
	}
	
}
