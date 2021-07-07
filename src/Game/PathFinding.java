package Game;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;

import DataStrucutes.aStarPriority;
import DataStrucutes.dQueue;
import DataStrucutes.dStack;

public class PathFinding {

	private static Thread thread; 
	private static dQueue<dStack<Cell>> cellStackQueue;
	private static aStarPriority aStarList;
	private static boolean pathFound;
	public static void BFS(Cell Starter)
	{
		cellStackQueue = new dQueue<dStack<Cell>>();
		pathFound = false;
		
		//makeallUnvisied(Starter);
		GridSystem.allUnvisited();
		GridSystem.setAllWhite();
		dStack<Cell> newStack = new dStack<Cell>();
		newStack.add(Starter);
		cellStackQueue.add(newStack);
		thread = new Thread();                                                             
        thread.start();
		addingNode();
	}
	public static void addingNode()
	{
		if(pathFound == true)
		{
			dStack<Cell> newStack = new dStack<Cell>();
			while(cellStackQueue.size() > 0)
			{
				newStack = cellStackQueue.remove();
			}
			while(!newStack.isEmpty())
			{
				newStack.pop().setCellColor(new Color(205,205,205));
			}
			try {
	            thread.join();                                                                    
	        }
	        catch (InterruptedException e){                                                         
	            e.printStackTrace();
	        }
		}
		else
		{
			if(cellStackQueue.isEmpty())
				return;
			
			addValidNeighbours(cellStackQueue, cellStackQueue.remove());
			addingNode();
		}	
	}
	//A* Algorithm//
	public static void aStar(Cell Starter)
	{
		aStarList = new aStarPriority();
		pathFound = false;
		
		//makeallUnvisied(Starter);
		GridSystem.allUnvisited();
		GridSystem.setAllWhite();
		dStack<Cell> newStack = new dStack<Cell>();
		newStack.add(Starter);
		aStarList.add(newStack);
		thread = new Thread();                                                             
        thread.start();
		AaddingNode();
	}
	public static void AaddingNode()
	{
		if(pathFound == true)
		{
			dStack<Cell> newStack = aStarList.remove();
			while(!newStack.isEmpty())
			{
				newStack.pop().setCellColor(new Color(205,205,205));
			}
			try {
	            thread.join();                                                                    
	        }
	        catch (InterruptedException e){                                                         
	            e.printStackTrace();
	        }
		}
		else
		{
			if(aStarList.isEmpty())
				return;
			
			addValidNeighbours(aStarList, aStarList.remove());
			AaddingNode();
		}	
	}
	//Adding Neighbours Functions//
	private static void addValidNeighbours(aStarPriority aStarStacks, dStack<Cell> currentCellStack)
	{
		if(currentCellStack.isEmpty())
			return;
		try {
			Thread.sleep(Game.algoSpeed);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		currentCellStack.peek().setCellColor(new Color(0, 200, 200));
		for(int i = 0; i < 4; i++)
		{
			if(currentCellStack.peek().access[i] != null &&  !currentCellStack.peek().access[i].getVisited())
			{
				dStack<Cell> newStack = (dStack<Cell>) currentCellStack.clone();
				newStack.add(currentCellStack.peek().access[i]);
				currentCellStack.peek().access[i].setVisited(true);
				currentCellStack.peek().access[i].setCellColor(new Color(255,255,51));
				aStarStacks.add(newStack);
				if(newStack.peek().getEnding())
				{
					pathFound = true;
					break;
				}
			}
		}
	}
	private static void addValidNeighbours(dQueue<dStack<Cell>> stacksQueue, dStack<Cell> currentCellStack)
	{
		if(currentCellStack.isEmpty())
			return;
		try {
			Thread.sleep(Game.algoSpeed);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		currentCellStack.peek().setCellColor(new Color(0, 200, 200));
		for(int i = 0; i < 4; i++)
		{
			if(currentCellStack.peek().access[i] != null &&  !currentCellStack.peek().access[i].getVisited())
			{
				dStack<Cell> newStack = (dStack<Cell>) currentCellStack.clone();
				newStack.add(currentCellStack.peek().access[i]);
				currentCellStack.peek().access[i].setVisited(true);
				currentCellStack.peek().access[i].setCellColor(new Color(255,255,51));
				stacksQueue.add(newStack);
				if(newStack.peek().getEnding())
				{
					pathFound = true;
					break;
				}
			}
		}
	}
}
