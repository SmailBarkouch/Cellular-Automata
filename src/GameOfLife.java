package us.sbarkouch.gameoflife;

import java.util.Scanner;
import javax.swing.JFrame;

public class GameOfLife
{
	public static Board inst;
    
    public static void main(String[] args)
    {
    	JFrame window = new JFrame("Smail's Game Of Life");
        Scanner scan = new Scanner(System.in);
        int chos = 4;
        int coord1;
    	int coord2;
    	int gen;
        
        window.setSize(1000,2000);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inst = new Board(window);
        
        window.add(inst);
        window.setVisible(true);
        
        String over = "comfirm";
        
        while(over.equals("comfirm"))
        {
            System.out.println("Welcome to the Game Of Life!");
            System.out.println("To begin, chose the coordinates of your alive cells, from the top left!");
            System.out.println("I will allow you to chose " + chos + " points on the map.");
            for(int i = 0; i < chos; i++)
            {
                coord1 = scan.nextInt();
                coord2 = scan.nextInt();
                inst.setCell(coord1, coord2);
                inst.repaint();
            }
            
            System.out.println("Good, now this is the \"enjoyable\" part!");
            System.out.println("Type a number to traverse that many generations on the board");
            System.out.println("If you want to quit, enter a non-numerical value, if you want to restart, type \"restart\".");
            over = "repeat";
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
            scan.close();
        }
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