package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import ScreenComponents.Button;
import ScreenComponents.HUDSdisplay;
/*
 * TANK PROJECT
 * @author : Ali Hussain
 */
public class Starter {

    Font fnt0 = new Font("Comics", Font.BOLD, 25);
    private static Button Easy = new Button((Game.WIDTH + Game.menuWIDTH)/2 - 100, 400, 200, 50, "Easy");
	private static Button Medium = new Button((Game.WIDTH + Game.menuWIDTH)/2 - 100, 460, 200, 50, "Medium");
	private static Button Hard = new Button((Game.WIDTH + Game.menuWIDTH)/2 - 100, 520, 200, 50, "Hard");
	public Starter()
	{
		Easy.setColor(new Color(0, 200, 200));
		Medium.setColor(Color.WHITE);
		Hard.setColor(new Color(0, 200, 200));
	}
    public void render(Graphics g) { 
    	String Text = "Press Enter to Start the Game";
    	FontMetrics metrics = g.getFontMetrics(fnt0);
    	int txtWidth = metrics.stringWidth(Text);
        HUDSdisplay.drawText(g, Text, (Game.WIDTH + Game.menuWIDTH)/2 - txtWidth/2, 350, fnt0, Color.WHITE);
        
        Easy.draw(g);
		Medium.draw(g);
		Hard.draw(g);
    }
    public static void StartFunction(float mouseX, float mouseY)
	{
    	if(Easy.isInside(mouseX, mouseY))
		{
			Easy.setColor(Color.WHITE);
			Medium.setColor(new Color(0, 200, 200));
			Hard.setColor(new Color(0, 200, 200));
			Game.LEVEL = 3;
		}
		else if(Medium.isInside(mouseX, mouseY))
		{
			Easy.setColor(new Color(0, 200, 200));
			Medium.setColor(Color.WHITE);
			Hard.setColor(new Color(0, 200, 200));
			Game.LEVEL = 6;
		}
		else if(Hard.isInside(mouseX, mouseY))
		{
			Easy.setColor(new Color(0, 200, 200));
			Medium.setColor(new Color(0, 200, 200));
			Hard.setColor(Color.WHITE);
			Game.LEVEL = 10;
		}
	}
}
