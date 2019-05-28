import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class Board extends JComponent
{
    private Cell[][] cel;
    private Dimension celDem;
    
    public Board ()
    {
        cel = new Cell[25][25];
        celDem = new Dimension(10,10);
    }
    
    public Board (JFrame j)
    {
        cel = new Cell[25][25];
        celDem = new Dimension(j.getSize().width / 25,j.getSize().height / 25);
    }
    
    public void paintComponent (Graphics g)
    {
        Color temp;
        for(int height = 0; height < cel.length; height++)
        {
            for(int width = 0; width < cel[0].length; width++)
            {
                Point p = new Point(width*10,height*10);
                temp = detCelCol(cel[height][width].getState(), cel[height][width].getPrevState());
                g.setColor(temp);
                
                g.fillRect((int) p.getX(), (int) p.getY(), (int) celDem.getHeight(), (int) celDem.getWidth());
                g.setColor(Color.BLACK);
                g.drawRect((int) p.getX(), (int) p.getY(), (int) celDem.getHeight(), (int) celDem.getWidth());
            }
        }
    }
    
    private Color detCelCol (int state, int prevState)
    {
        if(state == 0 && prevState == 0)
        {
            return Color.WHITE;
        } else if(state == 0 && prevState == 0) {
            return Color.RED;
        } else if(state == 1 && prevState == 0) {
            return Color.GREEN;
        } else {
            return Color.BLUE;
        }
    }
    
    public void progressGen ()
    {
        for(int width = 0; width < cel.length; width++)
        {
            for(int height = 0; height < cel[0].length; height++)
            {
                calcNeighbors(width, height);
                cel[width][height].updState();
            }
        }
    }
    
    public void setCell(int x, int y, Cell a)
    {
        cel[x][y] = a;
    }
    
    public void calcNeighbors (int width, int height)
    {
        for(int vert = -1; vert < cel.length; vert++)
        {
        	for(int hori = -1; hori < cel[vert].length; hori++)
        	{
        		if(cel[vert][hori].getState() == 1 && vert != 0 && hori != 0)
        			cel[vert][hori].incrNeighbors();
        	}
        }
    }
}