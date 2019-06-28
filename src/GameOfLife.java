package us.sbarkouch.gameoflife;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

public class GameOfLife extends JFrame implements ItemListener, KeyListener, MouseListener
{
	//add zoom later
	
	// Variables that will deal with game functions
	private Timer timer;
	private Board inst;
	
	/*
	 * The variables will be used on the menu bar to edit aspects of the game and interact
	 * with what the game has.
	 */
	private JMenuBar bar;
	private JMenu attributes;
	private JMenu about;
	private JMenu startOp;
	private JMenu boardRules;
	private JMenu visualOp;
	private JMenu enterOp;
	
	private JRadioButton start;
	private JRadioButton grid;
	
	private JButton restart;
	private JButton updRules;
	private JButton skipButton;
	private JButton autoButton;
	private JButton coordButton;
	
	private JMenuItem skipInfo;
	private JMenuItem autoInfo;
	private JMenuItem rulesInfo;
	private JMenuItem coordInfo;
	
	private JTextField delay;
	private JTextField genPerDelay;
	private JTextField genSkip;
	private JTextField boardSize;
	private JTextField dead1;
	private JTextField dead2;
	private JTextField res;
	private JTextField coord1;
	private JTextField coord2;
	
	private JComboBox<String> colors;
	
	public GameOfLife(String title)
	{
		
		// Setting up JFrame
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 500);
		
		timer = new Timer();
		
		// Creates a board with the GameOfLife rules
		inst = new Board(this, 3, 2, 3);
		
		// Creates the menu bar as well as the highest order components within it
		bar = new JMenuBar();
		attributes = new JMenu("Attributes");
		about = new JMenu("About");
		startOp = new JMenu("Start Options");
		boardRules = new JMenu("Edit Rules");
		visualOp = new JMenu("Visual Options");
		enterOp = new JMenu("Enter Options");
		
		// Radio buttons that control the game game from stoping/starting and turning off and on the grid
		start = new JRadioButton("Start/Stop", false);
		grid = new JRadioButton("Turn the Grid on/off");
		
		// Buttons used to update the board, mostly from gathered JTextFields
		restart = new JButton("Restart");
		updRules = new JButton("Update");
		coordButton = new JButton("Enter Coordinates");
		skipButton = new JButton("Skip");
		autoButton = new JButton("Update");
		
		// Items that give info about the functions
		skipInfo = new JMenuItem("Type requested generations to skip, then press enter");
		autoInfo = new JMenuItem("Type in the delay, and type in generation per that delay");
		rulesInfo = new JMenuItem("Type in key functioning neighbor rules[#-#], use -1 to deactivate");
		coordInfo = new JMenuItem("Enter the x and y values to plot a coordinate");
		
		// Text fields used to edit the game's functions
		delay = new JTextField("150");
		genPerDelay = new JTextField("1");
		coord1 = new JTextField("");
		coord2 = new JTextField("");
		genSkip = new JTextField("0");
		dead1 = new JTextField("2");
		boardSize = new JTextField("50");
		dead2 = new JTextField("3");
		res = new JTextField("3");
		
		String [] color = {"Black and White", "RGB"};
		colors = new JComboBox<String>(color);
		
		// Highlighting over these JTextFields will give this info
		dead1.setToolTipText("Any cell with less than this neighbors will die");
		dead2.setToolTipText("Any cell with more than this neighbors will die");
		res.setToolTipText("Any cell with exactly this number of neighbors will become alive");
		boardSize.setToolTipText("The dimensions of both sides of the board");
		
		// Setting identifiers that are used in the actionlisteners
		autoButton.setName("AutoButton");
		skipButton.setName("SkipButton");
		boardSize.setName("Board Size");
		restart.setName("Restart");
		start.setName("Start/Stop");
		updRules.setName("Update");
		grid.setName("Grid");
		coordButton.setName("Coordinates");
		
		grid.setSelected(true);
		
		add(inst);
		addMouseListener(inst);
		setJMenuBar(bar);
		
		// Adding different menus in hierarchy 
		bar.add(start);
		bar.add(restart);
		bar.add(attributes);
		bar.add(enterOp);
		bar.add(about);
		
		attributes.add(startOp);
		attributes.add(boardRules);
		attributes.add(visualOp);
		attributes.add(boardSize);
		
		enterOp.add(coordInfo);
		enterOp.add(coord1);
		enterOp.add(coord2);
		enterOp.add(coordButton);
		
		startOp.add(autoInfo);
		startOp.add(delay);
		startOp.add(genPerDelay);
		startOp.add(autoButton);
		startOp.addSeparator();
		startOp.add(skipInfo);
		startOp.add(genSkip);
		startOp.add(skipButton);
		
		//visualOp.add(colors);
		visualOp.add(grid);
		
		boardRules.add(rulesInfo);
		boardRules.add(dead1);
		boardRules.add(dead2);
		boardRules.add(res);
		boardRules.add(updRules);
		
		//Listeners for it being clicked, entered or it being selected/deselected
		colors.addItemListener(this);
		start.addItemListener(this);
		boardSize.addKeyListener(this);
		restart.addMouseListener(this);
		coordButton.addMouseListener(this);
		autoButton.addMouseListener(this);
		skipButton.addMouseListener(this);
		updRules.addMouseListener(this);
		grid.addItemListener(this);
		
		setVisible(true);
		
	}
	

	 // If a key is pressed, specifically enter, then the split variable will be changed.
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER && ((JTextField) arg0.getSource()).getName().equals("Board Size")) 
		{
			try {
				inst.prepSplit(Integer.parseInt(boardSize.getText()));
				inst.repaint();
			} catch(Exception e) {}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}
	@Override
	public void keyTyped(KeyEvent arg0) {}
	
	public static void main(String[] args)
	{
		GameOfLife g = new GameOfLife("Cellular Automaton");
	}
	
	/*
	 * If an item's state is changed, like for Combo Boxes or Radio Buttons, then this will trigger.
	 * In this case it is for the start/stop button.
	 */
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		if(arg0.getStateChange() == ItemEvent.SELECTED && ((JRadioButton) arg0.getSource()).getName().equals("Start/Stop"))
		{
			startSim(true);
		} else if (arg0.getStateChange() == ItemEvent.DESELECTED) {
			startSim(false);
		}
		
		if(arg0.getStateChange() == ItemEvent.SELECTED && ((JRadioButton)arg0.getSource()).getName().equals("Grid"))
		{
			inst.setGridOp(true);
		} else if (arg0.getStateChange() == ItemEvent.DESELECTED && (((JRadioButton)arg0.getSource()).getName().equals("Grid"))) {
			System.out.println("Grid reached!");
			inst.setGridOp(false);
		}
	}
	
	/*
	 * Creates a timer based on the board's delay value and the board's generation update per delay.
	 * The boolean accepted is to determine if the timer should be canceled or created.
	 */
	private void startSim(boolean startStop) {
		
		try {
			timer.cancel();
		} catch(Exception e) {
			
		}
		timer = new Timer();
		if(startStop)
		{
			timer.schedule(new TimerTask() {
				public void run()
				{
					inst.repGen();
				}
			}, 0, inst.getDelay());
		}
			
		
	}

	/*
	 * If the mouse is clicked for specific JComponents, then this actionlistener will be activated.
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		/*
		 * If the name string for the JButton is Coordinates, then a specific cell on the board will
		 * be switched on or off.
		 */
		if(((JButton) arg0.getSource()).getName().equals("Coordinates"))
		{
			try {
			inst.setCell(Integer.parseInt(coord1.getText()), Integer.parseInt(coord2.getText()));
			} catch(Exception e) {}
		}
		
		// The board is reset
		if(((JButton) arg0.getSource()).getName().equals("Restart"))
		{
			inst.resetBoard();
		}
		
		// Key rules for the automata are changed 
		if(((JButton) arg0.getSource()).getName().equals("Update"))
		{
			int tempDead1 = 2;
			int tempDead2 = 3;
			int tempRes = 3;
			try {
				tempDead1 = Integer.parseInt(dead1.getText());
				tempDead2 = Integer.parseInt(dead2.getText());
				tempRes = Integer.parseInt(res.getText());
				inst.setDead1(Integer.parseInt(dead1.getText()));
				inst.setDead2(Integer.parseInt(dead2.getText()));
				inst.setRes(Integer.parseInt(res.getText()));
				inst.resetBoard();
			} catch(Exception e) {
				inst.setDead1(tempDead1);
				inst.setDead2(tempDead2);
				inst.setRes(tempRes);
			}		
		}
		 
		// Changes the timer values based on what is in the JTextField
		if(((JButton) arg0.getSource()).getName().equals("AutoButton"))
		{
			int tempGenPerDelay = 1;
			int tempDelay = 150;
			try {
				tempGenPerDelay = Integer.parseInt(genPerDelay.getText());
				tempDelay = Integer.parseInt(delay.getText());
				inst.setGenPerDelay(Integer.parseInt(genPerDelay.getText()));
				inst.setDelay(Integer.parseInt(delay.getText()));
			} catch(Exception e) {
				inst.setDelay(tempDelay);
				inst.setGenPerDelay(tempGenPerDelay);
			}
		}
		
		// Progress straight to a specific generation
		try {
			if(((JButton)arg0.getSource()).getName().equals("SkipButton") && Integer.parseInt(genSkip.getText()) >= 0)
			{
				for(int i = 0; i < Integer.parseInt(genSkip.getText()); i++)
				{
					inst.resetAllNeigh();
					inst.progressGen();
				}
			}
		} catch(Exception e) {}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}
}