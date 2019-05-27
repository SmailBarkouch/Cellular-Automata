public class Cell
{
    private int state;
    private int prevState;
    private int neighbors;
    
    public Cell ()
    {
        state = 0;
        prevState = 0;
        neighbors = 0;
    }
    
    public Cell (int state)
    {
        state = state;
        prevState = 0;
        neighbors = 0;
    }
    
    public void incrNeighbors (int neighbors)
    {
        neighbors++;
    }
    
    public void setState (int state)
    {
        prevState = this.state;
        this.state = state;
    }
    
    public int getState ()
    {
        return state;
    }
    
    public int getPrevState ()
    {
        return prevState;
    }
    
    public void updState ()
    {
        if(neighbors < 2 || neighbors > 3)
        {
            prevState = state;
            state = 0;
        } else if (neighbors == 3) {
            prevState = state;
            state = 1;
        } else {
            prevState = state;
        }
    }
}