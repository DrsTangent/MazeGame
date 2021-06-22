package Game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Cell implements Comparable<Cell>{
	int x;
	int y;
	int size;
	private boolean hasPlayer;
	private boolean endingPoint;
	private boolean visited;
	private static Cell destPoint;
	public Cell access[] = new Cell[4]; // Pattern // top, right, bottom, left 
	public int distance[] = new int[4];
	private Color cellColor;
	public Cell(int x, int y, int size)
	{
		visited = false;
		hasPlayer = false;
		endingPoint = false;
		this.x = x;
		this.y = y;
		this.size = size;
		for(int i = 0; i < access.length; i++)
			access[i] = null;
		cellColor = Color.WHITE;
	}
	public void setVisited(boolean t)
	{
		visited = t;
	}
	public boolean getVisited()
	{
		return visited;
	}
	public void setPlayer(boolean t)
	{
		hasPlayer = t;
	}
	public void setEnding(boolean t)
	{
		endingPoint = t;
	}
	public boolean getEnding()
	{
		return endingPoint;
	}
	public void setCellColor(Color c)
	{
		this.cellColor = c;
	}
	public void renderCell(Graphics g)
	{
		//Giving Color to Cell//
		
		g.setColor(cellColor);
		g.fillRect(x, y, size, size);
		
		if(hasPlayer)
		{
			g.drawImage(GridSystem.playerPic, x, y, size, size, null);
		}
		else if(endingPoint)
		{
			g.drawImage(GridSystem.home, x, y, size, size, null);
		}
		//Priningting Walls//
		g.setColor(Color.BLACK);
		
		if(access[0] == null)
		{
			g.drawLine(x, y, x+size, y);
		}
		if(access[1] == null)
		{
			g.drawLine(x+size, y, x+size, y+size);
		}
		if(access[2] == null)
		{
			g.drawLine(x+size, y+size, x, y+size);
		}
		if(access[3] == null)
		{
			g.drawLine(x, y+size, x, y);
		}
		Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
	}
	public static void setDestPoint(Cell d)
	{
		destPoint = d;
	}
	public float distance()
	{
		float dis = (float) Math.sqrt((this.x - destPoint.x) * (this.x - destPoint.x) + (this.y - destPoint.y)*(this.y - destPoint.y) );
		return dis;
	}
	@Override
	public int compareTo(Cell o) {
		return (int) (o.distance() - this.distance());
	}
}
