// Module 23, Exercise 3
// Graham Thomas
// RepaintTimer.java
// decides how often a panel is repainted

/* Modify the program in Exercise 2 to add shadows. As a ball moves, draw a solid black oval at the bottom of the JPanel. You may consider adding a 3-D effect by increasing or decreasing the size of each ball when it hits the edge of the JPanel. */

// import required classes
import javax.swing.JPanel;

public class RepaintTimer implements Runnable
{
	private JPanel repaintPanel; // panel to repaint
	private int sleepTime; // time to wait between repaints

	// constructor
	public RepaintTimer( JPanel panel, int time )
	{
		repaintPanel = panel; // set the panel to be repainted
		sleepTime = time; // set the sleep time
	} // end constructor
	
	// main code to be run
	public void run()
	{
		// loop continuously until user exits the program
		while( true )
		{
			try
			{
				Thread.sleep( sleepTime ); // sleep for a while
				repaintPanel.repaint(); // repaint the panel
			}
			catch( InterruptedException exception )
			{
				// InterruptedException caught
				System.out.println( "Interrupted exception in RepaintTimer" );
			} // end catch block
		} // end while loop
	} // end method run
} // end class RepaintTimer