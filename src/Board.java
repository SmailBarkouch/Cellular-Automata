import java.swing.*;
import javax.awt.*;

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
        celDem = new Dimension(j.getSize().getWidth / 25,j.getSize().getHeight / 25);
    }
    
    public void paintComponent (Graphics g)
    {
        Color temp;
        for(int height = 0; height < cel.length; height++)
        {
            for(int width = 0; width < cel[0].length; width++)
            {
                Point p = new Point(width*10;height*10);
                temp = detCelCol(cel[height][width].getState(), cel[height][width].getPrevState())
                g.setColor(temp);
                
                g.fillRect(p,celDem);
                g.setColor(Color.BLACK);
                g.drawRect(p,celDem);
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
            return COLOR.GREEN;
        } else {
            return COLOR.BLUE;
        }
    }
    
    private void progressGen ()
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
        if(cel[width-1][height] == 1)
            cel[width][height].incrNeighbors();
        if(cel[width-1][height-1] == 1)
            cel[width][height].incrNeighbors();
        if(cel[width][height-1] == 1)
            cel[width][height].incrNeighbors();
        if(cel[width+1][height] == 1)
            cel[width][height].incrNeighbors();
        if(cel[width+1][height+1] == 1)
            cel[width][height].incrNeighbors();
        if(cel[width][height+1] == 1)
            cel[width][height].incrNeighbors();
        if(cel[width+1][height-1] == 1)
            cel[width][height].incrNeighbors();
        if(cel[width-1][height+1] == 1)
            cel[width][height].incrNeighbors();
    }
}