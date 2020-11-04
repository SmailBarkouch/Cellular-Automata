# Smail's Game of Life

**NOTE: This is not clean code, or efficent. It was written when I first started programming for AP Computer Science.**

<p> This is my take of Conway's Game of Life. I am doing this as an end of the year project for McNair Academic
Highschool AP CompSci class. This is going to try to function as close to Conway's idea, with some ideas being my
own.<p>

### Information:
<p>Conway’s Game of Life is a cellular automaton created by John Horton Conway in 1970. It is called a 0 player game because points are set and manipulated by pre-set inputs. These initial values will be placed on a two-dimensional plane, or specifically an array.

Moore’s Neighborhood, or the eight cells surrounding every cell, will determine what happens in the next generation. A generation in respect to the game is advancing a stage in which rules are enacted. These rules dictate what will happen as the game progresses.

### Rules:
Any live cell with fewer than two live neighbours die
Any live cell with two or three live neighbours progresses on to the next generation
Any live cell with more than three live neighbours dies
Any dead cell with exactly three live neighbours becomes alive

My specific edition of this game will have options for other rules, which can be turned on and off at will.

### Patterns:
Some common patterns are...

Still Life: A pattern that doesn’t change in shape or size from generation to generation
Oscillator: A pattern that changes in shape and/or size from generation to generation, but returns to their original pattern and position within a finite number of generations.
SpaceShip: A pattern that translates itself across the board.

### Implementation

This is written Java, using awt and swing.
