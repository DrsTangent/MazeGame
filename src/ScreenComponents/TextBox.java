package ScreenComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
/*
 * TANK PROJECT
 * @author : Ali Hussain
 */
public class TextBox {
	protected int width = 250;
	protected int height = 40;
	protected int baseX = 0;
	protected int baseY = 0;
	protected Color color = Color.WHITE;
	protected String Text = "";
	public TextBox(int baseX, int baseY) // default Button Size
	{
		this.baseX = baseX;
		this.baseY = baseY;
	}
	public TextBox(int baseX, int baseY, int width, int height, String Text) 
	{
		this.baseX = baseX;
		this.baseY = baseY;
		this.width = width;
		this.height = height;
		this.Text = Text;
	}
	public TextBox(int baseX, int baseY, int width, int height, String Text, Color c) 
	{
		this.baseX = baseX;
		this.baseY = baseY;
		this.width = width;
		this.height = height;
		this.Text = Text;
		this.color = c;
	}
	public TextBox(int baseX, int baseY, int width, int height) 
	{
		this.baseX = baseX;
		this.baseY = baseY;
		this.width = width;
		this.height = height;
	}
	//setters//
	public void setBase(int X, int Y)
	{
		this.baseX = X;
		this.baseY = Y;
	}
	public void setBounds(int width, int height) 
	{
		this.width = width;
		this.height = height;
	}
	public void setText(String data)
	{
		this.Text = data;
	}
	public String getText()
	{
		return Text;
	}
	public void draw(Graphics g)
	{
		g.setColor(this.color);
		g.fillRect(baseX, baseY, width, height);
		
		Font font = new Font("Comics", Font.BOLD, 12);
		
		FontMetrics metrics = g.getFontMetrics(font);
		
		// Determine the X coordinate for the text
	    int x = baseX + (width - metrics.stringWidth(this.Text)) / 2;
	    // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
	    int y = baseY + ((height - metrics.getHeight()) / 2); 
	    
		HUDSdisplay.drawText(g, this.Text, x, y, font);
	}
	
}
