// Module 23, Exercise 3
// Graham Thomas
// BallPanel.java
// panel in which balls can bounce

/* Modify the program in Exercise 2 to add shadows. As a ball moves, draw a solid black oval at the bottom of the JPanel. You may consider adding a 3-D effect by increasing or decreasing the size of each ball when it hits the edge of the JPanel. */

// import required classes
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

// class inherits from JPanel
public class BallPanel extends JPanel
{
	private RepaintTimer repaintTimer; // RepaintTimer object to determine how often the ball moves
	private ExecutorService threadExecutor; // ExecutorService for running threads in parallel
	private int panelWidth; // width of the panel
	private int panelHeight; // height of the panel
	private int ballCounter; // track number of balls created
	private final int MAX_NUMBER_OF_BALLS = 20; // max number of balls that can be created
	private Ball[] ballArray = new Ball[ MAX_NUMBER_OF_BALLS ]; // declare array of Ball objects
	private Random randomGenerator; // initialise random number generator
	
	// constructor accepts parameters for setting preferred size
	public BallPanel( int width, int height )
	{
		setPreferredSize( new Dimension( width, height ) ); // set preferred size
	 	panelWidth = ( int ) getPreferredSize().getWidth(); // get the preferred width
		panelHeight = ( int ) getPreferredSize().getHeight(); // get the preferred height
		
		// initialise repaintTimer with this panel and a sleep time of 20 milliseconds
		repaintTimer = new RepaintTimer( this, 20 );
		
		threadExecutor = Executors.newCachedThreadPool(); // initialise threadExecutor
		setBackground( Color.WHITE ); // set panel background colour to white
		setOpaque( true ); // opaque is true so panel background colour can be seen
		addMouseListener( new MouseListener() ); // add mouse listener to panel
		threadExecutor.execute( repaintTimer ); // execute the repaint timer in one thread
		ballCounter = 0; // initialise ball counter to zero
		randomGenerator = new Random(); // initialise random number generator
	} // end constructor

	public void paintComponent( Graphics g )
	{
		super.paintComponent( g ); // call superclass method
		Graphics2D g2d = ( Graphics2D ) g; // declare Graphics2D object
		
		for( Ball ball : ballArray ) // loop through array of balls
		{		
			if( ball != null ) // if ball is not null
			{
				// set paint to the colour of the ball
				g2d.setPaint( ball.getBallColour() );
			
				// paint the ball
				g2d.fill( new Ellipse2D.Double( ball.getXCoord(), // horizontal position of the ball
												ball.getYCoord(), // vertical position of the ball
												ball.getDiameter(), // diameter of the ball
												ball.getDiameter() ) // diameter of the ball
						);
				
				g2d.setPaint( Color.BLACK ); // set the colour to black for the shadows
				
				// double variable to represent diameter of the shadow
				// this is a percentage of the ball's diameter depending on how far from
				// the bottom of the window the ball is
				double shadowDiameter = ( double ) ball.getDiameter() * ( ( double ) ball.getYCoord() / ( double ) panelHeight );
				
				// paint the shadow
				g2d.fill( new Ellipse2D.Double( ball.getXCoord(), // same horizontal position as the ball
												panelHeight - ( shadowDiameter / 2 ), // vertical position at bottom of panel
												shadowDiameter * 1.5, // shadow is 1.5 times the width of the ball
												shadowDiameter / 2 // shadow is half the height of the ball
											  )
						);
			} // end if
		} // end for loop
	} // end method paintComponent
	
	// inner class for mouse listener
	public class MouseListener extends MouseAdapter
	{
		// override method mousePressed
		public void mousePressed( MouseEvent e )
		{
			if( ballCounter < MAX_NUMBER_OF_BALLS ) // if max number of balls hasn't been reached
			{
				// add a new ball to the array
				ballArray[ ballCounter ] = new Ball(  ( int ) getPreferredSize().getWidth(), // set max width
								                        ( int ) getPreferredSize().getHeight(), // set max height
								                        e.getX(), // initial horizontal position determined by mouse
								                        e.getY(), // initial vertical position determined by mouse
								                        10 ); // diameter of 10 pixels
				
				// set the ball colour to a random colour
				ballArray[ ballCounter ].setBallColour( new Color( randomGenerator.nextInt( 256 ),
									     					       randomGenerator.nextInt( 256 ),
									     					       randomGenerator.nextInt( 256 )
									     					      )
									     		      );
								  
				threadExecutor.execute( ballArray[ ballCounter ] ); // execute the ball in its own thread
				
				ballCounter++;
			}
		} // end method mousePressed
	} // end inner class MouseListener
} // end class BallPanel