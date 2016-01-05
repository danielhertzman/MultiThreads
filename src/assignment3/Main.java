package assignment3;

public class Main 
{
	public static void main(String[] args)
	{
		GUISemaphore gui = new GUISemaphore ( );
        Controller controller = new Controller (gui);
        gui.start(controller);
	}
	
}
