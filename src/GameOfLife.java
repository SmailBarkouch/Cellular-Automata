import java.swing.*;
import java.util.Scanner;

public class GameOfLife
{
    public JFrame window;
    public Scanner scan;
    public Board inst;
    public int chos = 20;
    public int coord1, coord2;
    
    public static void main(String[] args)
    {
        window = new JFrame("Smail's Game Of Life");
        scan = new Scanner(System.in);
        
        window.setSize(500,500);
        window.setDefaultCloseOperation(JFrame.CLOSE_ON_EXIT);
        inst = new Board(window);
        
        window.add(inst);
        
        String over = "comfirm";
        
        while(over == "comfirm")
        {
            System.out.println("Welcome to the Game Of Life!");
            System.out.println("To begin, chose the coordinates of your alive cells, from the top left!");
            System.out.println("I will allow you to chose " + chos + " points on the map.");
            for(int i = 0; i < chos; i++)
            {
                coord1 = scan.nextInt();
                coord2 = scan.nextInt();
                board.Cell(coord1, coord2, new Cell(1);
            }
            
            System.out.println("Good, now this is the \"enjoyable\" part!");
            System.out.println("Type a number to progress that number of generations.");
            System.out.println("If want to restart the game, type\"repeat\", otherwise type anything else to quit");
            over = "repeat";
            while(over == "repeat")
            {
                over = scan.nextLine();
                try
                {
                    repGen(Integer.parseInt(over));
                } catch (IllegalArgumentException exception) {
                    break;
                }
            }
        }
    }
    
    public void repGen (int rep)
    {
        for(int i = 0; i < rep; i++)
        {
            inst.progressGen();
        }
    }
    
    
}