package us.sbarkouch.gameoflife;

public class Cell
{
	/*
	 * Holds the state of the object, the state it used to have and the neighbors present around it.
	 */
    private int state; 
    private int prevState; 
    private int neighbors; 
    
    /*
	 * Constructs a default dead cell.
	 */
    public Cell () 
    {
        state = 0; 
        prevState = 0;
        neighbors = 0; 
    }
    
    
    /*
	 * Constructs a cell with a custom state, only used to give an alive cell.
	 */
    public Cell (int state) 
    {
        this.state = state; 
        prevState = 0;
        neighbors = 0;
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
	 * holding the amount of neighbors in the cell.
	 */  
    public void updState () 
    {
    	prevState = state;
        if(neighbors < 2 || neighbors > 3) 
        {
            state = 0;
        } 
        if (neighbors == 3) 
        {
        	state = 1;
        }
    }
}