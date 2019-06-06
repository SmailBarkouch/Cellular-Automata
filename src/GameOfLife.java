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
    	JFrame window = new JFrame("Smail's Game Of Life");
        Scanner scan = new Scanner(System.in);
        int coord1;
    	int coord2;
    	int chose = 13;
    	int gen;
    	Timer repeatGen = new Timer();
        
        window.setSize(500,600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inst = new Board(window);
        window.add(inst);
        window.addComponentListener(inst.listen);
        window.setVisible(true);
        
        String over = "comfirm";
        System.out.println("Welcome to the Game Of Life!");
        System.out.println("To begin, chose the coordinates of your alive cells, from the top left!");
        System.out.println("First choose your color scheme! Type \"1\" to choose an rgb based color scheme and type \"2\" to choose a black and white color scheme.");
        try {
        	inst.setColorSch(scan.nextInt());
        } catch(Exception e) {
        	inst.setColorSch(1);
        }
		System.out.println("Now enter " + chose + " coordinates, the coordinates start from the top left corner, starting from 0.");
        for(int i = 0; i < chose; i++)
        {
        	coord1 = scan.nextInt();
            coord2 = scan.nextInt();
            inst.setCell(coord1, coord2);
            inst.repaint();
        }
        
        System.out.println("Good, now this is the \"enjoyable\" part!");
        System.out.println("You have two modes.");
        System.out.println("Mode 1 automatically progresses generations at your chosen delay, Mode 2 skips generations based on your inputed number.");
        System.out.println("Type \"1\" or \"2\" to chose that selected variation.");
        gen = scan.nextInt();
        if(gen == 2)
        {
            System.out.println("Type a number to traverse that many generations on the board");
            System.out.println("If you want to quit, simply exit the window.");
            over = "repeat";
            
            while(over.equals("repeat"))
            {
                try 
                {
                	gen = scan.nextInt();
                	repGen(gen);
                } catch (Exception e) {
                }
            }
        } else {
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
    
    public static void repGen (int rep)
    {
        for(int i = 0; i < rep; i++)
        {
        	inst.resetAllNeigh();
            inst.progressGen();
        }
    }
}