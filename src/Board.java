package us.sbarkouch.gameoflife;

import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Graphics; 
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JFrame; 

public class Board extends JComponent implements MouseListener, ComponentListener
{
	/*
	 * Used variables to control board graphics as well as determine the number of
	 * neighbors present around a cell.
	 */
	private static final long serialVersionUID = 1L;
	private Cell[][] cel; 
	private Cell[][] tempCel;
	public Dimension celDem;
	public static final int split = 50;
	private int colorSch;
	private Point loc;
    
	/*
	 * Constructor that creates all cells in this given run
	 * and creates the tempCel to store uneditied boards for
	 * logic.
	 */
    public Board () 
    {
    	cel = new Cell[split][split];
    	tempCel = new Cell[split][split];
        for(int height = 0; height < split; height++)
        {
        	for(int width = 0; width < split; width++)
        	{
        		cel[height][width] = new Cell();
        		tempCel[height][width] = new Cell();
        	}
        }
        
        celDem = new Dimension(10,10); 
        }
    
	/*
	 * Uses the JFrame to construct the Board. tempCel
	 * is also constructed and used for logic.
	 */
    public Board (JFrame j) 
    {
    	celDem = new Dimension();
    	tempCel = new Cell[split][split];
    	cel = new Cell [split][split];
    	for(int height = 0; height < split; height++)
        {
        	for(int width = 0; width < split; width++)
        	{
        		cel[height][width] = new Cell();
        		tempCel[height][width] = new Cell();
        	}
        }
    }
    
	/*
	 * Used to put graphical aspects on the JFrame, determines the color of cells on the board
	 * as well as determining where each cell will be placed.
	 */
    public void paintComponent (Graphics g) 
    {
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
                	g.setColor(temp); 
                    g.fillRect((int) p.getX(), (int) p.getY(), (int) celDem.getWidth() / split, (int) celDem.getHeight() / split);
                    g.setColor(Color.BLACK); 
                    g.drawRect((int) p.getX(), (int) p.getY(), (int) celDem.getWidth() / split, (int) celDem.getHeight() / split);
            }
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
    public Color detCelColBW (int state) 
    {
        if(state == 1) 
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
    public void setColorSch(int choose)
    {
    	if(choose == 1)
    	{
    		colorSch = 1;
    	} else {
    		colorSch = 2;
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
            	if(cel[width][height].getState() == 1)
            	{
            		tempCel[width][height] = new Cell(1);
            	} else {
            		tempCel[width][height] = new Cell();
            	}
            }
        }
        for(int width = 0; width < split; width++)
        {
            for(int height = 0; height < split; height++)
            {
            	
                calcNeighbors(width, height);
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
        cel[x][y] = new Cell(1);
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
        			if(tempCel[width+hori][height+vert].getState() == 1 && ((hori != 0 && vert != 0) || (hori != 0 && vert == 0) || (hori == 0 && vert != 0))) 
        			{
        				cel[width][height].incrNeighbors(); 
        			}
        		} catch(Exception e) {
        			
        		}
        	}
        }
        
    }

	@Override
	public void mouseClicked(MouseEvent arg0) {
		loc = arg0.getLocationOnScreen();
		if(cel[(int) loc.getX() / split][(int) loc.getY() / split].getState() == 0)
		{
			cel[(int) (loc.getX() / split)][(int) (loc.getY() / split)] = new Cell(1);
			System.out.println(loc.getX());
			System.out.println(split);
			System.out.println(celDem.getWidth());
		} else {
			cel[(int) (loc.getX() / split)][(int) (loc.getY() / split)] = new Cell(0);
		}
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	 /*
	  * Used to give the dimension of the page after each resize.
	  */
	public void componentResized (ComponentEvent e)
    {
    	celDem = e.getComponent().getBounds().getSize();
    }

	public void componentHidden(ComponentEvent e) {}
	public void componentMoved(ComponentEvent e) {}
	public void componentShown(ComponentEvent e) {}

}