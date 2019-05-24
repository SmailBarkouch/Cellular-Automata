<h1>Smail's Game of Life</h1>
<p> This is my take of Conway's Game of Life. I am doing this as an end of the year project for McNair Academic
Highschool AP CompSci class. This is going to try to function as close to Conway's idea, with some ideas being my
own.<p>

<h3>Information</h3>
<p>Conway’s Game of Life is a cellular automaton created by John Horton Conway in 1970. It has no players but instead the game is played by pre-set inputs. These initial values will be placed on a two-dimensional plane, or specifically an array. The values inputted by the user denote whether a specific cell will be alive or dead. Based on that value the game will have different outcomes and various patterns.

Moore’s Neighborhood, or the eight cells surrounding every cell, will determine what happens in the next generation. A generation in respect to the game is advancing a stage in which rules are enacted. These rules dictate what will happen as the game progresses.

Rules:
Any live cell with fewer than two live neighbours die
Any live cell with two or three live neighbours progresses on to the next generation
Any live cell with more than three live neighbours dies
Any dead cell with exactly three live neighbours becomes alive

My specific edition of this game will have options for other rules, which can be turned on and off at will before the game.

Within the game there are various types of patterns. Those can be sorted into many different groups, but the most patterns created are one of three groups.

Still Life: A pattern that doesn’t change in shape or size from generation to generation

Oscillator: A pattern that changes in shape and/or size from generation to generation, but returns to their original pattern and position within a finite number of generations.

SpaceShip: A pattern that translates itself across the board.


<h3>Application</h3>
Due to the unusual and unprecedented ways the pattern grows and evolves, it can be used to represent a wide array of ideas. Some use this to explain that complexity and organization can appear in the absence of a designer. Daniel Dennett has used the game to explain life and its attributes, consciousness and free will, from what would be simple rules of the universe.

The game also displays undecidability. In that a computer having an initial patter and a later pattern, no algorithm can decide if a later specific pattern is going to appear. Supporting the halting problem. In that a computer can not determine whether a program will run forever from an given input.

The purpose of this project is to learn about these previously stated programming concepts and build an understanding of using visual aspects of java. Utilising Canvas as well as other objects. This project will also be very array heavy and requires lots of logic, which will push me towards getting a better understanding of what programming is all about. Getting me out of my comfort zone and into other projects that are more challenging and utilise more of my understanding of java.

<h3>Implementation</h3>
This game will require input from the user before starting. This input will be used throughout the rest of the program and none else will be required. The input from the user is determining whether different cells on the grid will be alive or dead. The user will have to click a point on the grid to make it alive, and again to make it dead. 
Realistically this project can be done all in one class. Though since it would be more interesting, I have split it into two different classes, with special additions. The first class will be the called Smail Game of Life and the second class will be called Cell. Smail Game of Life will hold the 2d array of cell objects as well as all game conditions. The game conditions being the four listed above as well as some others. It will also hold the visual aspects of the game, being the subclass of Canvas and implementing the interface Runnable. 
Runnable will get rid of TesterClasses and introduce threading if I chose to expand this project in the future. While Canvas will help me design the board without using ascii. The other class, Cell, will contain instance variable determining whether it is alive or dead, as well as a color it has on the board. This color will be used to display a cell that just appeared as well as a cell that just disappeared. This will have no methods on its own.

