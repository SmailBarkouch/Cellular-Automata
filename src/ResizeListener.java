package us.sbarkouch.gameoflife;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class ResizeListener implements ComponentListener
{
	public Dimension celDem;
	
	public ResizeListener ()
	{
		celDem = new Dimension();
	}
	
	public ResizeListener (int width, int height)
	{
		celDem = new Dimension(width, height);
	}
	
	public void componentResized (ComponentEvent e)
    {
    	celDem = e.getComponent().getBounds().getSize();
    }

	public void componentHidden(ComponentEvent e) {}
	public void componentMoved(ComponentEvent e) {}
	public void componentShown(ComponentEvent e) {}
}
