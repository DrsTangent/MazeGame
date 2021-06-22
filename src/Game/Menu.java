package Game;

import java.awt.Color;
import java.awt.Graphics;

import ScreenComponents.Button;
import ScreenComponents.TextBox;

public class Menu {
	//Maze Algorithms//
	private static TextBox mazeText = new TextBox(600, 0, 200, 50, "Maze Algorithms");
	private static Button mazeAlgo1 = new Button(600, 100, 200, 50, "Backtracker DFS");
	private static Button mazeAlgo2 = new Button(600, 150, 200, 50, "Prims Algorithm");
	private static Button mazeAlgo3 = new Button(600, 200, 200, 50, "Kruskal Algorithm");
	//Path Finding"
	private static TextBox pathText = new TextBox(600, 300, 200, 50, "Path Finding");
	private static Button pathAlgo1 = new Button(600, 400, 200, 50, "Breath First Search");
	private static Button pathAlgo2 = new Button(600, 450, 200, 50, "A* Algorithm");
	//Difficulty Selector
	private static Button Slow = new Button(600, 550, 60, 50, "Slow");
	private static Button Medium = new Button(600+70, 550, 60, 50, "Medium", new Color(200, 200, 200));
	private static Button Fast = new Button(600+140, 550, 60, 50, "Fast");
	public static void render(Graphics g)
	{
		//Back Ground
		g.setColor(new Color(0, 200, 200));
		g.fillRect(600, 0, 200, 600);
		
		//Maze
		mazeText.draw(g);
		mazeAlgo1.draw(g);
		mazeAlgo2.draw(g);
		mazeAlgo3.draw(g);
		pathText.draw(g);
		pathAlgo1.draw(g);
		pathAlgo2.draw(g);
		Slow.draw(g);
		Medium.draw(g);
		Fast.draw(g);
	}
	
	public static void StartFunction(float mouseX, float mouseY)
	{
		if(mazeAlgo1.isInside(mouseX, mouseY))
		{
			MazeAlgorithms.reverseBackTracking();
		}
		else if(mazeAlgo2.isInside(mouseX, mouseY))
		{
			MazeAlgorithms.primsAlgo();
		}
		else if(mazeAlgo3.isInside(mouseX, mouseY))
		{
			MazeAlgorithms.kruskalAlgo2();
		}
		else if(pathAlgo1.isInside(mouseX, mouseY))
		{
			PathFinding.BFS(GridSystem.grid[GridSystem.currentX][GridSystem.currentY]);
		}
		else if(pathAlgo2.isInside(mouseX, mouseY))
		{
			PathFinding.aStar(GridSystem.grid[GridSystem.currentX][GridSystem.currentY]);
		}
		else if(Slow.isInside(mouseX, mouseY))
		{
			Slow.setColor(new Color(200, 200, 200));
			Medium.setColor(new Color(255, 255, 50));
			Fast.setColor(new Color(255, 255, 50));
			Game.algoSpeed = 40;
		}
		else if(Medium.isInside(mouseX, mouseY))
		{
			Slow.setColor(new Color(255, 255, 50));
			Medium.setColor(new Color(200, 200, 200));
			Fast.setColor(new Color(255, 255, 50));
			Game.algoSpeed = 20;
		}
		else if(Fast.isInside(mouseX, mouseY))
		{
			Slow.setColor(new Color(255, 255,50));
			Medium.setColor(new Color(255, 255, 50));
			Fast.setColor(new Color(200, 200, 200));
			Game.algoSpeed = 10;
		}
	}
}
