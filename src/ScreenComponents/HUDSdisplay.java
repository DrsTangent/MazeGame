package ScreenComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import Game.Game;
/*
 * TANK PROJECT
 * @author : Ali Hussain
 */
public class HUDSdisplay {
	public static void drawText(Graphics g, String Text, int x, int y, int size)
	{
		g.setColor(Color.BLACK);
		
		Graphics2D g2d = (Graphics2D) g;

		Font font = new Font("Comics", Font.BOLD, size);
		g2d.setFont(font);
		
		FontMetrics fontMetrics = g2d.getFontMetrics();
		 
		// Draw a string such that the top-left corner is at x, y
		g2d.drawString(Text, x, y+fontMetrics.getAscent());
		
	}
	
	public static void drawText(Graphics g, String Text, int x, int y, Font font)
	{
		g.setColor(Color.BLACK);
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setFont(font);
		
		FontMetrics fontMetrics = g2d.getFontMetrics();
		 
		// Draw a string such that the top-left corner is at x, y
		g2d.drawString(Text, x, y+fontMetrics.getAscent());
		
	}
	
	public static void drawText(Graphics g, String Text, int x, int y, Font font, Color color)
	{
		g.setColor(color);
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setFont(font);
		
		FontMetrics fontMetrics = g2d.getFontMetrics();
		 
		// Draw a string such that the top-left corner is at x, y
		g2d.drawString(Text, x, y+fontMetrics.getAscent());
		
	}
}
