/*
@Author: Ali Hussain
@Project: Maze Game
*/

package Game;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class GridSystem {
	public enum Walls{
		TOP,
		BOTTOM,
		RIGHT,
		LEFT
	};
	private int level;
	public static Cell grid[][];
	public static Cell playerCell;
	public static BufferedImage playerPic;
	public static BufferedImage home;
	public static int currentX, currentY;
	private int wSize, hSize;
	public GridSystem(int level)
	{
		try{
			playerPic = ImageIO.read(getClass().getResource("/player.png"));
			home = ImageIO.read(getClass().getResource("/home.png"));
        }
        catch (IOException e){                             
            e.printStackTrace();
        }
		this.level = level;
		wSize = Game.WIDTH / (level*5);
		hSize = Game.HEIGHT / (level*5);
		grid = new Cell[level*5][level*5];
	}
	public static void allUnvisited()
	{
		for(int i = 0; i<grid.length; i++)
		{
			for(int j = 0; j<grid.length; j++)
			{
				grid[i][j].setVisited(false);
			}
		}
		
	}
	public static void setAllWhite()
	{
		for(int i = 0; i<grid.length; i++)
		{
			for(int j = 0; j<grid.length; j++)
			{
				grid[i][j].setCellColor(Color.WHITE);
			}
		}
	}
	public void makeGrid()
	{
		for(int i = 0; i < level*5; i++)
		{
			for (int j = 0; j < level * 5; j++)
			{
				Cell t = new Cell(i*wSize, j*hSize, wSize);
				grid[i][j] = t;
			}
		}
		grid[0][0].setPlayer(true);
		currentX = 0;
		currentY = 0;
		grid[level*5 - 1][level*5 - 1].setEnding(true);
	}
	public Cell startingPoint()
	{
		return grid[currentX][currentY];
	}
	public void moveUP()
	{
		if(grid[currentX][currentY].access[0] != null)
		{
			grid[currentX][currentY].setPlayer(false);
			grid[currentX][--currentY].setPlayer(true);
		}
	}
	public void moveRIGHT()
	{
		if(grid[currentX][currentY].access[1] != null)
		{
			grid[currentX][currentY].setPlayer(false);
			grid[++currentX][currentY].setPlayer(true);
		}
	}
	public void moveBOTTOM()
	{
		if(grid[currentX][currentY].access[2] != null)
		{
			grid[currentX][currentY].setPlayer(false);
			grid[currentX][++currentY].setPlayer(true);
		}
	}
	public void moveLEFT()
	{
		if(grid[currentX][currentY].access[3] != null)
		{
			grid[currentX][currentY].setPlayer(false);
			grid[--currentX][currentY].setPlayer(true);
		}
	}
	public void renderGrid(Graphics g)
	{
		for(int i = 0; i < level*5; i++)
		{
			for (int j = 0; j < level * 5; j++)
			{
				grid[i][j].renderCell(g);
			}
		}
	}
}
