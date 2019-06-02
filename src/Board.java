package us.sbarkouch.gameoflife;

import java.awt.Color; // used for Canvas
import java.awt.Dimension; // used to store the dimensions of all cells
import java.awt.Graphics; // used to feed into the JCompnent
import java.awt.Point; // used to store the coordinates of a cell
import javax.swing.JComponent; // used to add on a JFrame
import javax.swing.JFrame; // used to build a certain cell size based on the JFrame

public class Board extends JComponent
{

	private static final long serialVersionUID = 1L;
	protected Cell[][] cel = new Cell[25][25]; // the board of cells
    private Dimension celDem; // the dimensions of the cell
    
    public Board () // sets the default board size and dimensions in reference to the JFrame
    {
        for(int height = 0; cel.length < 25; height++)
        {
        	for(int width = 0; cel[0].length < 25; width++)
        	{
        		cel[height][width] = new Cell();
        	}
        }
        celDem = new Dimension(10,10); // 10 pixels by 10 pixels on the JFrame
    }
    
    public Board (JFrame j) // sets the cell dimensions based on the size of the JFrame
    {
    	for(int height = 0; height < cel.length; height++)
        {
        	for(int width = 0; width < cel[0].length; width++)
        	{
        		cel[height][width] = new Cell();
        	}
        }
        celDem = new Dimension(j.getSize().width / 25,j.getSize().height / 25); // the JFrame needs to be a multiple of 25
    }
    
    public void paintComponent (Graphics g) // method overrided from the JFrame
    {
        for(int height = 0; height < cel.length; height++) // checks the cells on the board from up to down
        {
            for(int width = 0; width < cel[0].length; width++) // checks cells on the board from left to right
            {
                Point p = new Point(width*25,height*25); // creates coordinate on the JFrame based on the combination of width and height
                Color temp = detCelCol(cel[height][width].getState(), cel[height][width].getPrevState()); // sets a color based on the previous state and currecnt state, fed into detCelCol method
                g.setColor(temp); // sets the Graphics method to a color, all created objects will be this color until changed otherwise
                g.fillRect((int) p.getX(), (int) p.getY(), (int) celDem.getHeight(), (int) celDem.getWidth()); // creates a solid square based on the point variable and dimension variabel
                g.setColor(Color.BLACK); // sets the outline for the cell on the JFrame
                g.drawRect((int) p.getX(), (int) p.getY(), (int) celDem.getHeight(), (int) celDem.getWidth()); // creates outline of a square
            }
        }
    }
    
    public Color detCelCol (int state, int prevState) // determines cell color based on the state and the previous state
    {
        if(state == 0 && prevState == 0) // if it was dead before and is dead right now, it is just a normal white square
        {
            return Color.WHITE; // uses final variable from the Color class
        } else if(state == 0 && prevState == 1) { // if it was alive before and is now dead, the cell is marked a red square
            return Color.RED;
        } else if(state == 1 && prevState == 0) { // if the cell was dead before and is now dead the cell is marked as a green square
            return Color.BLUE;
        } else { // if all those conditions aren't fullfilled, it must have been alive and still is, being blue
            return Color.GREEN;
        }
    }
    
    public void progressGen () // progresses a generation, using the rules
    {
        for(int width = 0; width < cel.length; width++)
        {
            for(int height = 0; height < cel[0].length; height++)
            {
                calcNeighbors(width, height);
                cel[width][height].updState();
            }
        }
        repaint();
    }
    
    public void resetAllNeigh()
    {
    	for(int width = 0; width < cel.length; width++)
        {
            for(int height = 0; height < cel[0].length; height++)
            {
                cel[width][height].resetNeigh();
            }
        }
    }
    
    public void setCell(int x, int y) // makes a cell chosen by the player living
    {
        cel[x][y] = new Cell(1);
    }
    
    public void calcNeighbors (int width, int height) // finds the number of neighbors for a cell and updates the individual cell's instance variable
    {
        for(int hori = -1; hori < 2; hori++) // cells above and bellow the chosen cell
        {
        	for(int vert = -1; vert < 2; vert++) // cells to the left and right of the chosen cell
        	{
        		try {
        			if(cel[width+hori][height+vert].getState() == 1 && vert != 0 && hori != 0) // make sure the current cell isn't counted
        				cel[width][height].incrNeighbors(); // increments the number of neighbors
        		} catch(Exception e) {
        			
        		}
        	}
        }
    }
}