package us.sbarkouch.gameoflife;

public class Cell
{
    private int state; // Will be either 0 or 1, 0 denoting a dead state and 1 denoting an alive state
    private int prevState; // The state of a cell from the last generation
    private int neighbors; // The amount of live neighbors around a cell, at most 8
    
    public Cell () // setting all values to 0 since they need to be updated
    {
        state = 0; // all cells are orignally dead util set otherwise or by the player
        prevState = 0;
        neighbors = 0; // unchecked
    }
    
    public Cell (int state) // setting all values to 0, but state becomes 1 as per player choice
    {
        this.state = state; // becomes 1
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
    
    public void updState () // changes the state based on neighbors
    {
    	prevState = state;
        if(neighbors < 3 || neighbors > 4) // these are death cases, from overpopulation or underpopulation
        {
            state = 0;
        } else if (neighbors == 3) { // reproduction case
        	state = 1;
        }
    }
}