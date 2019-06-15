package us.sbarkouch.gameoflife;

import java.util.Scanner;
import javax.swing.JFrame;
import java.util.Timer;
import java.util.TimerTask;

public class GameOfLife
{
	public static Board inst;
    
    public static void main(String[] args)
    {
    	/*
    	 * Initializes almost all used variables within the game.
    	 */  
    	JFrame window = new JFrame("Smail's Game Of Life");
        Scanner scan = new Scanner(System.in);
    	int gen;
    	Timer repeatGen = new Timer();
        
    	/*
    	 * Set values for the JFrame as well as initalizing a board and putting that on the JFrame. Also
    	 * adding a listener to the board.
    	 */  
        window.setSize(500,600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inst = new Board(window);
        window.add(inst);
        window.addComponentListener(inst);
        window.addMouseListener(inst);
        window.setVisible(true);
        
        /*
    	 * Gives some basic information about the Game and how to interact with the board.
    	 */  
        String over = "comfirm";
        System.out.println("Welcome to the Game Of Life!");
        System.out.println("To begin, chose the coordinates of your alive cells, from the top left!");
        System.out.println("First choose your color scheme! Type \"1\" to choose an rgb based color scheme and type \"2\" to choose a black and white color scheme.");
        
        /*
    	 * Accepts a value used to determine the color scheme of the board.
    	 */  
        try {
        	inst.setColorSch(scan.nextInt());
        } catch(Exception e) {
        	inst.setColorSch(1);
        }
        
        /*
    	 * Responds with text how to enter points on the board, and use values to put coordinates
    	 * on the board.
    	 */  
		System.out.println("First type in the number of points you want to enter, then the coordinates!");
		int chose = scan.nextInt();
        for(int i = 0; i < chose; i++)
        {
            inst.setCell(scan.nextInt(), scan.nextInt());
            inst.repaint();
        }
        
        /*
    	 * Explains the two modes and allows the player to choose one mode.
    	 */  
        System.out.println("Good, now this is the \"enjoyable\" part!");
        System.out.println("You have two modes.");
        System.out.println("Mode 1 automatically progresses generations at your chosen delay, Mode 2 skips generations based on your inputed number.");
        System.out.println("Type \"1\" or \"2\" to choose that selected variation.");
        gen = scan.nextInt();
        if(gen == 2)
        {
            System.out.println("Type a number to traverse that many generations on the board");
            System.out.println("If you want to quit, simply exit the window.");
            over = "repeat";
            
            /*
        	 * The second mode, the player can skip generations to anything of his choosing.
        	 */  
            while(over.equals("repeat"))
            {
                try 
                {
                	gen = scan.nextInt();
                	repGen(gen);
                } catch (Exception e) {
                	over = scan.nextLine();
                }
            }
        } else {
        	/*
        	 * The first mode, the player will automatically progress generations based on input
        	 * passed into the timer.
        	 */  
        	System.out.println("Type the number of generations you want to progress, then the delay between each successive generations (in milliseconds).");
        	try {
        		int progress = scan.nextInt();
        		long delay = scan.nextLong();
        		repeatGen.schedule(new TimerTask() {
        			public void run() {
        				repGen(progress);
            			}
            		}, 0, (long)delay);
        	} catch(Exception e) {
        		System.out.print("Why you trying to break my game! That's it, no game for you!");
        	}
        }
        scan.close();
    }
    
    /*
	 * Used to repeat generations given by the user. Mostly used in mode 2.
	 */  
    public static void repGen (int rep)
    {
        for(int i = 0; i < rep; i++)
        {
        	inst.resetAllNeigh();
            inst.progressGen();
        }
    }
}