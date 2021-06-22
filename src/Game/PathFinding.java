package Game;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class PathFinding {

	private static Thread thread; 
	private static Queue<Stack<Cell>> cellStackQueue;
	private static ArrayList<Stack<Cell>> aStarList;
	private static boolean pathFound;
	public static void BFS(Cell Starter)
	{
		cellStackQueue = new LinkedList<Stack<Cell>>();
		pathFound = false;
		
		//makeallUnvisied(Starter);
		GridSystem.allUnvisited();
		GridSystem.setAllWhite();
		Stack<Cell> newStack = new Stack<Cell>();
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
			while(cellStackQueue.size() > 1)
			{
				cellStackQueue.remove();
			}
			Stack<Cell> newStack = cellStackQueue.remove();
			while(newStack.size() > 0)
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
		aStarList = new ArrayList<Stack<Cell>>();
		pathFound = false;
		
		//makeallUnvisied(Starter);
		GridSystem.allUnvisited();
		GridSystem.setAllWhite();
		Stack<Cell> newStack = new Stack<Cell>();
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
			while(aStarList.size() > 1)
			{
				aStarList.remove(0);
			}
			Stack<Cell> newStack = aStarList.remove(0);
			while(newStack.size() > 0)
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
			
			addValidNeighbours(aStarList, minimumDistanceStack(aStarList));
			AaddingNode();
		}	
	}
	
	private static Stack<Cell> minimumDistanceStack(ArrayList<Stack<Cell>> arrayList)
	{
		Stack<Cell> smallestDistanceS = arrayList.get(0);
		int index = 0;
		for(int i = 0; i<arrayList.size(); i++)
		{
			if(arrayList.get(index).peek().distance() > arrayList.get(i).peek().distance())
			{
				index = i;
			}
		}
		smallestDistanceS = arrayList.get(index);
		arrayList.remove(index);
		return smallestDistanceS;
	}
	//Adding Neighbours Functions//
	private static void addValidNeighbours(ArrayList<Stack<Cell>> aStarStacks, Stack<Cell> currentCellStack)
	{
		if(currentCellStack.size() == 0)
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
				Stack<Cell> newStack = (Stack<Cell>) currentCellStack.clone();
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
	private static void addValidNeighbours(Queue<Stack<Cell>> stacksQueue, Stack<Cell> currentCellStack)
	{
		if(currentCellStack.size() == 0)
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
				Stack<Cell> newStack = (Stack<Cell>) currentCellStack.clone();
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
