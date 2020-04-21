# Smail's Game of Life

**NOTE: This is not clean code, or efficent. It was written when I first started programming for AP Computer Science.**

<p> This is my take of Conway's Game of Life. I am doing this as an end of the year project for McNair Academic
Highschool AP CompSci class. This is going to try to function as close to Conway's idea, with some ideas being my
own.<p>

### Information
<p>Conway’s Game of Life is a cellular automaton created by John Horton Conway in 1970. It has no players but instead the game is played by pre-set inputs. These initial values will be placed on a two-dimensional plane, or specifically an array. The values inputted by the user denote whether a specific cell will be alive or dead. Based on that value the game will have different outcomes and various patterns.

Moore’s Neighborhood, or the eight cells surrounding every cell, will determine what happens in the next generation. A generation in respect to the game is advancing a stage in which rules are enacted. These rules dictate what will happen as the game progresses.

Rules:

Any live cell with fewer than two live neighbours die
Any live cell with two or three live neighbours progresses on to the next generation
Any live cell with more than three live neighbours dies
Any dead cell with exactly three live neighbours becomes alive

My specific edition of this game will have options for other rules, which can be turned on and off at will before the game.

Patterns:

Within the game there are various types of patterns. Those can be sorted into many different groups, but the most patterns created are one of three groups.

Still Life: A pattern that doesn’t change in shape or size from generation to generation

Oscillator: A pattern that changes in shape and/or size from generation to generation, but returns to their original pattern and position within a finite number of generations.

SpaceShip: A pattern that translates itself across the board.


### Application

Due to the unusual and unprecedented ways the pattern grows and evolves, it can be used to represent a wide array of ideas. Some use this to explain that complexity and organization can appear in the absence of a designer. Daniel Dennett has used the game to explain life and its attributes, consciousness and free will, from what would be simple rules of the universe.

The game also displays undecidability. In that a computer having an initial patter and a later pattern, no algorithm can decide if a later specific pattern is going to appear. Supporting the halting problem. In that a computer can not determine whether a program will run forever from an given input.

The purpose of this project is to learn about these previously stated programming concepts and build an understanding of using visual aspects of java. Utilising Canvas as well as other objects. This project will also be very array heavy and requires lots of logic, which will push me towards getting a better understanding of what programming is all about. Getting me out of my comfort zone and into other projects that are more challenging and utilise more of my understanding of java.

### Implementation

This is written Java, using basic awt and swing.
