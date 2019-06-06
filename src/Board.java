package us.sbarkouch.gameoflife;

import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Graphics; 
import java.awt.Point;
import javax.swing.JComponent;
import javax.swing.JFrame; 

public class Board extends JComponent
{

	private static final long serialVersionUID = 1L;
	private Cell[][] cel; 
	private Cell[][] tempCel;
	public Dimension celDem;
	public static final int split = 50;
	public ResizeListener listen;
	private int colorSch;
    
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
    
    public Board (JFrame j) 
    {
    	listen = new ResizeListener (j.getWidth() / split, j.getHeight() / split);
    	celDem = listen.celDem;
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
    
    public void paintComponent (Graphics g) 
    {
    	celDem = listen.celDem;
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
    
    public Color detCelColBW (int state) 
    {
        if(state == 1) 
        {
            return Color.BLACK; 
        } else {
        	return Color.WHITE;
        }
    }
    
    public void setColorSch(int choose)
    {
    	if(choose == 1)
    	{
    		colorSch = 1;
    	} else {
    		colorSch = 2;
    	}
    }
    
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
    
    public void setCell(int x, int y) 
    {
        cel[x][y] = new Cell(1);
    }
    
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

}