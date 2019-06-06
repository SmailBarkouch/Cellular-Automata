package us.sbarkouch.gameoflife;

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
        this.state = state; 
        prevState = 0;
        neighbors = 0;
    }
    
    public void incrNeighbors () // rather than setting a neighbor value, incrementing is easier
    {
        neighbors++;
    }
    
    public void setState (int state) // changes state to 0 or 1 based on rules, and prevState updated
    {
        prevState = this.state; // becomes current state
        this.state = state; // state updated
    }
    
    public int getState () // returns the currect state
    {
        return state;
    }
    
    public int getPrevState () // returns previous state
    {
        return prevState;
    }
    
    public void resetNeigh()
    {
    	neighbors = 0;
    }
    
    public int getNeigh()
    {
    	return neighbors;
    }
    
    public void updState () // changes the state based on neighbors
    {
    	prevState = state;
        if(neighbors < 2 || neighbors > 3) // these are death cases, from overpopulation or underpopulation
        {
            state = 0;
        } 
        if (neighbors == 3) // reproduction case
        {
        	state = 1;
        }
    }
}