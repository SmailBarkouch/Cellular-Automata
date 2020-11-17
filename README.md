# Cellular Automata

### Information:
Cellular Automata is a model studied in automata theory. A cellular automaton consists of a grid of cells with a finite number of states (in this case, on or off). For each increase in time (in any unit definied), the cells around a particular cell will determine its state.

In this cellular automata program, users are given the option to...
- change the size of the board
- change what amount of neighbors determine if a cell will be turned off, on, or persist
- change what amount of time causes the board to update
- change visiual settings
- plot on the board or highlight and selected cells to turn them off or on

### Conway's Game of Life Rules (Default Settings):
Any live cell with fewer than two live neighbours die
Any live cell with two or three live neighbours progresses on to the next generation
Any live cell with more than three live neighbours dies
Any dead cell with exactly three live neighbours becomes alive

### Implementation

The program is split into three different classes. The `Cell` class contains key information like its state and the amount of neighbors around it. The `Board` class handles key operations between cells. The `GameOfLife` class handles the UI aspects of the model as well as listeners to register information

This is written in Java, using awt and swing.

**NOTE: This is not clean code. It was written when I first started programming for AP Computer Science.**
