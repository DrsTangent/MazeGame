package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import ScreenComponents.HUDSdisplay;
/*
 * MazeProject
 * @author : Ali Hussain
 */
public final class WinnerScreen {
	
	public static void render(Graphics g)
	{
		Font fnt0 = new Font("Comics", Font.BOLD, 25);
	    String Text = "Press Enter to Go to the Main Menu";
	    HUDSdisplay.centerText(g, Text, (Game.WIDTH + Game.menuWIDTH)/2, 500, fnt0, Color.BLACK);
	}
}
