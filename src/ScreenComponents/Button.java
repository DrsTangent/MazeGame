package ScreenComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

/*
 *USCD Unfolding Map PROJECT
 *@author Ali Hussain
*/


public class Button {

	private int width = 130;
	private int height = 40;
	private int baseX = 0;
	private int baseY = 0;
	private Color color = Color.YELLOW;
	private String title = null;
	private String pressedTitle = null;
	private boolean pressedButton = false;
	//Constructors//
	public Button(int baseX, int baseY) // default Button Size
	{
		this.baseX = baseX;
		this.baseY = baseY;
	}
	public Button(int baseX, int baseY, int width, int height, String title) 
	{
		this.baseX = baseX;
		this.baseY = baseY;
		this.width = width;
		this.height = height;
		this.title = title;
	}
	public Button(int baseX, int baseY, int width, int height, String title, Color c) 
	{
		this.baseX = baseX;
		this.baseY = baseY;
		this.width = width;
		this.height = height;
		this.title = title;
		this.color = c;
	}
	public Button(int baseX, int baseY, int width, int height) 
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
	public void setButton(int width, int height) 
	{
		this.width = width;
		this.height = height;
	}
	public void setTitle(String data)
	{
		this.title = data;
		if(this.pressedTitle == null)
		{
			this.pressedTitle = this.title;
		}
	}
	public void setPTitle(String data)
	{
		this.pressedTitle = data;
	}
	public void setColor(Color c)
	{
		this.color = c;
	}
	//Getters//
	public int getX()
	{
		return this.baseX;
	}
	public int getY()
	{
		return this.baseY;
	}
	public int getWidth()
	{
		return this.width;
	}
	public int getheight()
	{
		return this.height;
	}
	public String getTitle()
	{
		return this.title;
	}
	public String getTitle2()
	{
		return this.pressedTitle;
	}
	public Color getColor()
	{
		return this.color;
	}
	public boolean isPressed()
	{
		return this.pressedButton;
	}
	//Methods//
	public void draw(Graphics g)
	{
		g.setColor(this.color);
		g.fillRect(baseX, baseY, width, height);
		
		Font font = new Font("Comics", Font.BOLD, 12);
		
		FontMetrics metrics = g.getFontMetrics(font);
		
		// Determine the X coordinate for the text
	    int x = baseX + (width - metrics.stringWidth(this.title)) / 2;
	    // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
	    int y = baseY + ((height - metrics.getHeight()) / 2); 
	    
		if(this.pressedButton && pressedTitle != null)
			HUDSdisplay.drawText(g, this.pressedTitle, x, y, font);
		else
			HUDSdisplay.drawText(g, this.title, x, y, font);
	}
	public boolean isInside(float mouseX, float mouseY)
	{
		if(mouseX > baseX && mouseX < baseX + width
				&& mouseY > baseY && mouseY < baseY + height)
			return true;
		else 
			return false;
	}
	public void clicked()
	{
		this.pressedButton = this.pressedButton?false:true; 
	}
}
