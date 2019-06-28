package us.sbarkouch.gameoflife;

public class Cell
{
	/*
	 * Holds the state of the object, the state it used to have and the neighbors present around it.
	 */
    private int state; 
    private int prevState; 
    private int neighbors; 
    private int res;
    private int dead1;
    private int dead2;
    
    /*
	 * Constructs a cell with a custom state, and custom death and ressurrection values.
	 */
    public Cell (int state, int res, int dead1, int dead2) 
    {
        this.state = state; 
        prevState = 0;
        neighbors = 0;
        this.res = res;
        this.dead1 = dead1;
        this.dead2 = dead2;
    }
    
    /*
	 * Used to increment the number of neighbors.
	 */ 
    public void incrNeighbors () 
    {
        neighbors++;
    }
    
    /*
	 * Changes state to 0 or 1 based on rules, and prevState updated.
	 */  
    public void setState (int state) 
    {
        prevState = this.state; // becomes current state
        this.state = state; // state updated
    }
    
    /*
	 * Returns the current state.
	 */  
    public int getState () // returns the currect state
    {
        return state;
    }
    
    /*
	 * Returns the current previous state.
	 */  
    public int getPrevState () // returns previous state
    {
        return prevState;
    }
    
    /*
	 * Resets all neighbors.
	 */  
    public void resetNeigh()
    {
    	neighbors = 0;
    }
    
    /*
	 * Returns the amount of neighbors around the cell.
	 */  
    public int getNeigh()
    {
    	return neighbors;
    }
    
    /*
	 * Changes its state and previous state based on the instance variable neighbors,
	 * as well as provided conditions.
	 */  
    public void updState () 
    {
    	prevState = state;
        if(neighbors < dead1 || neighbors > dead2 && dead1 != -1 && dead2 != -1) 
        {
            state = 0;
        } 
        if (neighbors == res) 
        {
        	state = 1;
        }
    }
}