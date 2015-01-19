// Module 23, Exercise 3
// Graham Thomas
// Ball.java
// Represents a ball bouncing round a panel

/* Modify the program in Exercise 2 to add shadows. As a ball moves, draw a solid black oval at the bottom of the JPanel. You may consider adding a 3-D effect by increasing or decreasing the size of each ball when it hits the edge of the JPanel. */

// import required classes
import java.util.Random;
import java.awt.Color;

public class Ball implements Runnable
{
	private int x; // tracks horizontal position of ball
	private int y; // tracks vertical position of ball
	private int xChange; // rate of change in horizontal position
	private int yChange; // rate of change in vertical position
	private int diameter; // diameter of the ball
	private int maxWidth; // width of the area in which the ball should bounce
	private int maxHeight; // height of the area in which the ball should bounce
	private boolean forwards; // is the ball moving forwards (left to right)
	private boolean downwards; // is the ball moving downwards (top to bottom)
	private Random randomGenerator; // random number generator
	private Color ballColour; // colour of the ball

	// constructor takes parameters for setting window width, window height,
	// starting horizontal position, starting vertical position and ball diameter
	public Ball( int windowWidth, int windowHeight, int startX, int startY, int ballDiameter )
	{
		x = startX; // set starting horizontal position
		y = startY; // set starting vertical position
		diameter = ballDiameter; // set ball diameter
		forwards = true; // set ball moving left to right initially
		downwards = true; // set ball moving top to bottom initially
		setMaxWidth( windowWidth ); // set the width of the area in which the ball should bounce
		setMaxHeight( windowHeight ); // set the height of the area in which the ball should bounce
		randomGenerator = new Random(); // initialise random number generator
		xChange = 1 + randomGenerator.nextInt( 5 ); // random rate of horizontal change between 1 and 5 pixels
		yChange = 1 + randomGenerator.nextInt( 5 ); // random rate of vertical change between 1 and 5 pixels
		ballColour = Color.BLUE; // initialise ball colour to blue
	} // end constructor
	
	// main code to run
	public void run()
	{
		// loop continuously until user exits the program
		while( true )
		{
			try
			{
				Thread.sleep( 20 ); // sleep for 20 milliseconds
			
				if( forwards ) // if ball is moving forwards (left to right)
				{
					x += xChange; // advance ball further forwards 
					
					// if ball has reached the right most horizontal boundary of the bouncing area
					if( x >= ( maxWidth - diameter ) )
						forwards = false; // set the ball moving backwards (right to left)
				}
				else // if ball is moving backwards (right to left)
				{
					x -= xChange; // advance ball further backwards

					// if ball has reached the left most horizontal boundary of the bouncing area
					if( x <= 0 )
						forwards = true; // set the ball moving forwards (left to right)
				}
				
				if( downwards ) // if ball is moving downwards (top to bottom)
				{
					y += yChange; // advance ball further downwards
					
					// if ball has reached the bottom most vertical boundary of the bouncing area
					if( y >= ( maxHeight - diameter ) )
						downwards = false; // set the ball moving upwards (bottom to top)
				}
				else // if ball is moving upwards (bottom to top)
				{
					y -= yChange; // advance ball further upwards
					
					// if ball has reached the top most vertical boundary of the bouncing area
					if( y <= 0 )
						downwards = true; // set the ball moving downward (top to bottom)
				}
			} // end try block
			catch( InterruptedException exception )
			{
				// InterruptedException encountered
				System.out.println( "Interrupted exception in Ball" );
			}
		}
	}
	
	// set method for the max width of the bouncing area
	// could be used to update the max width if window is resized
	public void setMaxWidth( int width )
	{
		maxWidth = width;
	}
	
	// set method for the max height of the bouncing area
	// could be used to update the max height if window is resized
	public void setMaxHeight( int height )
	{
		maxHeight = height;
	}
	
	// set method for the ball colour
	public void setBallColour( Color colour )
	{
		ballColour = colour;
	}
	
	// get method for ball diameter
	public int getDiameter()
	{
		return diameter;
	}
	
	// get method for horizontal position of the ball
	public int getXCoord()
	{
		return x;
	}
	
	// get method for vertical position of the ball
	public int getYCoord()
	{
		return y;
	}
	
	// get method for the colour of the ball
	public Color getBallColour()
	{
		return ballColour;
	}
} // end class Ball