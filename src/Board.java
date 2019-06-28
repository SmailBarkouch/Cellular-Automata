package us.sbarkouch.gameoflife;

import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Graphics; 
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JFrame; 

public class Board extends JComponent implements MouseListener
{
	/*
	 * Used variables to control board graphics as well as determine the number of
	 * neighbors present around a cell.
	 */
	private static final long serialVersionUID = 1L;
	private Cell[][] cel; 
	private Dimension celDem;
	private int split = 50;
	private int colorSch;
	private Point loc1;
	private Point loc2;
	private long delay;
	private int genPerDelay;
	private int res;
	private int dead1;
	private int dead2;
	private boolean gridOp;
    
	/*
	 * Uses the JFrame to construct the Board. tempCel
	 * is also constructed and used for logic.
	 */
    public Board (JFrame j, int res, int dead1, int dead2) 
    {
    	celDem = new Dimension();
    	delay = 150;
    	genPerDelay = 1;
    	this.res = res;
    	this.dead1 = dead1;
    	this.dead2 = dead2;
    	gridOp = true;
    	resetBoard();
    }
    
    public void resetBoard()
    {
    	cel = new Cell [split][split];
    	for(int height = 0; height < split; height++)
        {
        	for(int width = 0; width < split; width++)
        	{
        		cel[height][width] = new Cell(0, res, dead1, dead2);
        	}
        }
    	repaint();
    }
    
	/*
	 * Used to put graphical aspects on the JFrame, determines the color of cells on the board
	 * as well as determining where each cell will be placed.
	 */
    public void paintComponent (Graphics g) 
    {
    	celDem = getRootPane().getSize();
    	Color temp;
        for(int width = 0; width < cel.length; width++) 
        {
            for(int height = 0; height < cel[0].length; height++) 
            {
                Point p = new Point((int) (celDem.getWidth() / split * width),(int) (celDem.getHeight() / split * height)); 
                if(colorSch == 1)
                {
                	temp = detCelColRGB(cel[height][width].getState(), cel[height][width].getPrevState()); 
                } else {
                	temp = detCelColBW(cel[height][width].getState());
                }
                if(temp == Color.WHITE && gridOp)
                {
                	g.setColor(temp);
                    g.fillRect((int) p.getX(), (int) p.getY(), (int) celDem.getWidth() / split, (int) celDem.getHeight() / split);
                } else if (temp == Color.BLACK) {
                	g.fillRect((int) p.getX(), (int) p.getY(), (int) celDem.getWidth() / split, (int) celDem.getHeight() / split);
                }
                constructGrid(g, (int) p.getX(), (int) p.getY(), (int) celDem.getWidth() / split, (int) celDem.getHeight() / split);
            }
        }
    }
    
    // Constructs a grid based on whether the user prefered it or not
    public void constructGrid (Graphics g, int x, int y, int width, int height)
    {
    	if(gridOp) 
    	{
    		g.setColor(Color.BLACK);
    		g.drawRect(x, y, width, height);
    	}
    	
    }
    
	/*
	 * Determines the color of what a cell should be in RGB values, based on the state and prevState.
	 */
    public Color detCelColRGB (int state, int prevState) 
    {
        if(state == 0 && prevState == 0) 
        {
            return Color.WHITE; 
            } else if(state == 0 && prevState == 1) { 
            	return Color.RED;
        } else if(state == 1 && prevState == 0) { 
        	return Color.BLUE;
        } else { 
        	return Color.GREEN;
        }
    }
    
    /*
	 * Determines the color of what a cell should be in Black and White values, based on the current state.
	 */
    public Color detCelColBW (int colorSch) 
    {
        if(colorSch == 1) 
        {
            return Color.BLACK; 
        } else {
        	return Color.WHITE;
        }
    }
    
    /*
	 * Sets the board's colorSch that is used to determine if RGB or Black and White values will
	 * be used.
	 */
    public void setColorSch(int scolorSch)
    {
    	if(colorSch == 1)
    	{
    		this.colorSch = 1;
    	} else {
    		this.colorSch = 2;
    	}
    }
    
    /*
	 * Progresses a generation on the board. Does this by creating a duplicate board on tempCel
	 * and then using that board for another method that determines the amount of neighbors around each cell.
	 */
    public void progressGen () 
    {
        for(int width = 0; width < split; width++)
        {
            for(int height = 0; height < split; height++)
            {
            	
                calcNeighbors(width, height);
            }
        }
        for(int width = 0; width < split; width++)
        {
            for(int height = 0; height < split; height++)
            {
                cel[width][height].updState();
            }
        }
        repaint();
    }
    
    /*
	 * Resets all neighbors values on each cell for the board.
	 */
    public void resetAllNeigh()
    {
    	for(int width = 0; width < split; width++)
        {
            for(int height = 0; height < split; height++)
            {
                cel[width][height].resetNeigh();
            }
        }
    }
    
    /*
	 * Used to set points on the board determined by the player.
	 */
    public void setCell(int x, int y) 
    {
        cel[x][y] = new Cell(1, res, dead1, dead2);
        repaint();
    }
    
    /*
	 * Determines the number of neighbors around the board. Does this by utilising tempCel, a copy of the board,
	 * and comparing what cells are alive on a not yet edited board.
	 */
    public void calcNeighbors (int width, int height) 
    {
        for(int hori = -1; hori < 2; hori++) 
        {
        	for(int vert = -1; vert < 2; vert++) 
        	{
        		try {
        			if(cel[width+hori][height+vert].getState() == 1 && !(hori == 0 && vert == 0)) 
        			{
        				cel[width][height].incrNeighbors();
        			}
        		} catch(Exception e) {
        			
        		}
        	}
        }
        
    }
    
	// repeats generations, used only by the timer in the gameoflife to automatically continue
	public void repGen ()
    {
        for(int i = 0; i < genPerDelay; i++)
        {
        	resetAllNeigh();
            progressGen();
        }
    }
    
    // Mouse click listener used to turn cells on and off directly from clicking it
	@Override
	public void mouseClicked(MouseEvent arg0) 
	{
		loc1 = arg0.getLocationOnScreen();
		loc1.setLocation(loc1.getX(),loc1.getY()-76);
		if(cel[(int) (loc1.getY() / (celDem.getHeight() / split))][(int) (loc1.getX() / (celDem.getWidth() / split))].getState() == 0)
		{
			cel[(int) (loc1.getY() / (celDem.getHeight() / split))][(int) (loc1.getX() / (celDem.getWidth() / split))] = new Cell(1, res, dead1, dead2);
		} else {
			cel[(int) (loc1.getY() / (celDem.getHeight() / split))][(int) (loc1.getX() / (celDem.getWidth() / split))] = new Cell(0, res, dead1, dead2);
		}
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) 
	{
		loc1 = arg0.getLocationOnScreen();
		loc1.setLocation(loc1.getX(), loc1.getY() - 76);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) 
	{
		try {
			loc2 = arg0.getLocationOnScreen();
			loc2.setLocation(loc2.getX(), loc2.getY() - 76);
			highlightCells();
		} catch(Exception e) {
			
		}
	}
	
	// Prepares the board to be switched into different dimensions
	public void prepSplit (int split)
	{
		this.split = split;
		resetBoard();
	}
	
	public void highlightCells()
	{
		for(int i = (int) (loc1.getY() / (celDem.getHeight() / split)); i < (int) (loc2.getY() / (celDem.getHeight() / split)); i++)
		{
			for(int x = (int) (loc1.getX() / (celDem.getWidth() / split)); x < (int) (loc2.getX() / (celDem.getWidth() / split)); x++)
			{
				if(cel[i][x].getState() == 0)
				{
					cel[i][x] = new Cell(1, res, dead1, dead2);
				} else {
					cel[i][x] = new Cell(0, res, dead1, dead2);
				}
			}
		}
		repaint();
	}
	
	/*
	 * Various getter and setter methods used to put values from JTextFields onto them, and then onto all or some of
	 * the various cells.
	 */
	public void setDead1 (int dead1)
	{
		this.dead1 = dead1;
	}
	
	public void setDead2 (int dead2)
	{
		this.dead2 = dead2;
	}
	
	public void setCelDem (Dimension celDem)
	{
		this.celDem = celDem;
	}
	
	public void setRes (int res)
	{
		this.res = res;
	}

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}
	
	public int getGenPerDelay()
	{
		return genPerDelay;
	}
	
	public void setGenPerDelay(int genPerDelay)
	{
		this.genPerDelay = genPerDelay;
	}
	
	public void setGridOp (boolean gridOp)
	{
		this.gridOp = gridOp;
		repaint();
	}

}