// Module 23, Exercise 3
// Graham Thomas
// BallPanelTest.java
// class for testing panel with bouncing balls

/* Modify the program in Exercise 2 to add shadows. As a ball moves, draw a solid black oval at the bottom of the JPanel. You may consider adding a 3-D effect by increasing or decreasing the size of each ball when it hits the edge of the JPanel. */

// import required classes
import javax.swing.JFrame;

// class inherits from JFrame
public class BallPanelTest extends JFrame
{

	BallPanel ballPanel; // BallPanel object
	
	// constructor
	public BallPanelTest()
	{
		super( "Bouncing Ball" ); // call superclass constructor with window title
		ballPanel = new BallPanel( 600, 600 ); // initialise ballPanel with a width and height of 600 pixels
		add( ballPanel ); // add ballPanel to this frame
	} // end constructor

	// main method
	public static void main( String args[] )
	{
		BallPanelTest bpt = new BallPanelTest(); // declare and initialise new BallPanelTest object
		bpt.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); // set window to exit on close
		bpt.pack(); // resize window to fit contents
		bpt.setVisible( true ); // set window to be visible
	} // end method main
} // end class BallPanelTest