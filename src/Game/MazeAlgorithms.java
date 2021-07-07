package Game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import Game.GridSystem.Walls;

public class MazeAlgorithms {
	private static Thread thread;
	
	//DFS / Recursive Back Tracking//
	
	public static void reverseBackTracking()
	{
		thread = new Thread();
		thread.start();
		
		DFSsettingUP();
		settingSEPoints();
		
		
		DFSmazeHelper(GridSystem.currentX,GridSystem.currentY);
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void DFSsettingUP()
	{
		for(int i = 0; i < GridSystem.grid.length; i++)
		{
			for(int j = 0; j < GridSystem.grid[0].length; j++)
			{
				GridSystem.grid[i][j].setCellColor(new Color(0, 200, 200));
				GridSystem.grid[i][j].setEnding(false);
				GridSystem.grid[i][j].setPlayer(false);
				GridSystem.grid[i][j].setVisited(false);
				for(int k = 0; k < 4; k++)
				{
					GridSystem.grid[i][j].access[k] = null;
				}
			}
		}
	}
	private static void DFSmazeHelper(int x, int y)
	{
		try {
			thread.sleep(Game.algoSpeed);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GridSystem.grid[x][y].setVisited(true);
		GridSystem.grid[x][y].setCellColor(Color.WHITE);
		//Storing Walls//
		ArrayList<Walls> neighbours = new ArrayList<Walls>(); //Unvisited Neighbours
		while(true)
		{
			neighbours = new ArrayList<Walls>();
			if(validUnvisited(x, y-1)) // top
				neighbours.add(Walls.TOP);
			if(validUnvisited(x+1, y)) // right
				neighbours.add(Walls.RIGHT);
			if(validUnvisited(x, y+1)) // bottom
				neighbours.add(Walls.BOTTOM);
			if(validUnvisited(x-1, y))//LEFT
				neighbours.add(Walls.LEFT);
			if(neighbours.size() == 0)
				return;
			Random rand = new Random();
			neighbours.trimToSize();
			int randNum = Math.abs(rand.nextInt())%neighbours.size();
			Walls chosenWall = neighbours.get(randNum);
			if(chosenWall == Walls.TOP)
			{
				GridSystem.grid[x][y].access[0] = GridSystem.grid[x][y-1];
				GridSystem.grid[x][y-1].access[2] = GridSystem.grid[x][y];
				DFSmazeHelper(x, y-1);
			}
			else if(chosenWall == Walls.BOTTOM)
			{
				GridSystem.grid[x][y].access[2] = GridSystem.grid[x][y+1];
				GridSystem.grid[x][y+1].access[0] = GridSystem.grid[x][y];
				DFSmazeHelper( x, y+1);
			}
			else if(chosenWall == Walls.LEFT)
			{
				GridSystem.grid[x-1][y].access[1] = GridSystem.grid[x][y];
				GridSystem.grid[x][y].access[3] = GridSystem.grid[x-1][y];
				DFSmazeHelper( x-1, y);
			}
			else if(chosenWall == Walls.RIGHT)
			{
				GridSystem.grid[x+1][y].access[3] = GridSystem.grid[x][y];
				GridSystem.grid[x][y].access[1] = GridSystem.grid[x+1][y];
				DFSmazeHelper(x+1, y);
			}
			else
			{
				break;
			}
		}
	}
	//Prims Algo//
	private static ArrayList<Object[]> primList;
	private static ArrayList<Object[]> MST;
	public static void primsAlgo()
	{
		
		primSettingUP();
		
		primHelper1();
		thread = new Thread();
		thread.start();
		makeMST();
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void primSettingUP()
	{
		Random rand = new Random();
		
		for(int i = 0; i < GridSystem.grid.length; i++)
		{
			for(int j = 0; j < GridSystem.grid[0].length; j++)
			{
				GridSystem.grid[i][j].setCellColor(new Color(0, 200, 200));
				GridSystem.grid[i][j].setVisited(false);
				GridSystem.grid[i][j].setEnding(false);
				GridSystem.grid[i][j].setPlayer(false);
				int distance;
				//right
				if(checkAccess(i+1,j))
				{
					distance = rand.nextInt(10) + 1;
					GridSystem.grid[i][j].distance[1] = distance;
					GridSystem.grid[i+1][j].distance[3] = distance;
					GridSystem.grid[i][j].access[1] = GridSystem.grid[i+1][j];
					GridSystem.grid[i+1][j].access[3] = GridSystem.grid[i][j];
				}
				//bottom
				if(checkAccess(i,j+1))
				{
					distance = rand.nextInt(10) + 1;
					GridSystem.grid[i][j].distance[2] = distance;
					GridSystem.grid[i][j+1].distance[0] = distance;
					GridSystem.grid[i][j].access[2] = GridSystem.grid[i][j+1];
					GridSystem.grid[i][j+1].access[0] = GridSystem.grid[i][j];
				}
			}
		}
		settingSEPoints();
	}
	private static void primHelper1()
	{
		Object Array[] = new Object[3];
		// Node - Key - Parent //
		primList = new ArrayList<Object[]>();
		MST = new ArrayList<Object[]>();
		for(int i = 0; i < GridSystem.grid.length; i++)
		{
			for(int j = 0; j < GridSystem.grid[0].length; j++)
			{
				Array = new Object[4];
				Array[0] = GridSystem.grid[i][j]; // current Cell
				Array[1] = 11; // as max distance is 10 so 11 will work as Infinity //
				Array[2] = null; // Parent Cell
				primList.add(Array);
			}
		}
		primHelp2(primList.get(GridSystem.currentY));
	}
	private static void primHelp2(Object[] currentArray)
	{
		if(currentArray == null)
			return;
		Cell currentCell = (Cell) currentArray[0];
		Cell parentCell = (Cell) currentArray[2];
		int key = (int) currentArray[1];
		currentCell.setVisited(true);

		Cell currentCellAccess[] = currentCell.access;
		//As it is Visited//
		//Remove This Object from Array List//
		MST.add(currentArray);
		primList.remove(currentArray);
		//
		for(int i = 0; i < 4; i++)
		{
			if(currentCell.distance[i] >= 1)
			{
				ParentUpdate (currentCellAccess[i], currentCell.distance[i], currentCell, i);
			}
		}
		currentCell.access[0] = null;
		currentCell.access[1] = null;
		currentCell.access[2] = null;
		currentCell.access[3] = null;
		primHelp2(lowestKey());
	}
	private static Object[] lowestKey()
	{
		if(primList.size() == 0)
			return null;
		
		Object[] lowestArray = primList.get(0);
		for(int i = 0; i<primList.size(); i++)
		{
			if((int)primList.get(i)[1] < (int)lowestArray[1])
			{
				lowestArray = primList.get(i);
			}
		}
		
		return lowestArray;
	}
	private static void ParentUpdate (Cell currentCell, int key, Cell parentCell, int pos)
	{
		for(int i = 0; i<primList.size(); i++)
		{
			if((Cell)primList.get(i)[0] == currentCell && (int)primList.get(i)[1] > key)
			{
				primList.get(i)[1] = key;
				primList.get(i)[2] = parentCell;
				primList.get(i)[3] = pos;
			}
		}
	}
	private static void makeMST()
	{
		for(int i = 0; i<MST.size(); i++)
		{
			Cell cell = (Cell) MST.get(i)[0];
			cell.setCellColor(Color.WHITE);
			if(MST.get(i)[2] != null) // parent not null
			{
				try {
					thread.sleep(Game.algoSpeed);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Cell parentCell = (Cell) MST.get(i)[2];
				cell.access[((int) MST.get(i)[3]+2)%4] = parentCell;
				parentCell.access[(int) MST.get(i)[3]] = cell;
			}
		}
	}
	//*KRUSKAL IMPLEMENTATION*//
	//Type-II Visualization//
	public static void kruskalAlgo2()
	{
		thread = new Thread();
		thread.start();
		
		kruskal p = new kruskal(convertToMatrix());
		EdgeData[] EdgesCollection = p.btskruskal();
		int length = (int) Math.sqrt(p.Matrix.length);
		
		for(int i = 0; i<EdgesCollection.length; i++)
		{
			int y = EdgesCollection[i].start/length;
			int x = EdgesCollection[i].start%length;
			int y2 = EdgesCollection[i].end/length;
			int x2 = EdgesCollection[i].end%length;
			Cell src = GridSystem.grid[x][y];
			Cell des = GridSystem.grid[x2][y2];
			
			src.setCellColor(Color.WHITE);
			des.setCellColor(Color.WHITE);
			
			//top//
			if(checkAccess(x,y-1) && des == GridSystem.grid[x][y-1])
			{
				src.access[0] = des;
				des.access[2] = src;
			}
			//right//
			else if(checkAccess(x+1,y) && des == GridSystem.grid[x+1][y])
			{
				src.access[1] = des;
				des.access[3] = src;
			}
			//bottom//
			else if(checkAccess(x,y+1) && des == GridSystem.grid[x][y+1])
			{
				src.access[2] = des;
				des.access[0] = src;
			}
			//left//
			else if(checkAccess(x-1,y) && des == GridSystem.grid[x-1][y])
			{
				src.access[3] = des;
				des.access[1] = src;
			}
			//Pause for Visualizing//
			try {
				thread.sleep(Game.algoSpeed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Wait to end the Thread//
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Type-I Visualization//
	public static void kruskalAlgo()
	{
		thread = new Thread();
		thread.start();
		
		int[][] matrix = kruskalMST();
		int length = (int) Math.sqrt(matrix.length);
		for(int i = 0; i<matrix.length; i++)
		{
			int x = i/length;
			int y = i%length;
			
			Cell cell = GridSystem.grid[y][x];
			cell.setCellColor(Color.WHITE);
			//top 
			if(i-length >= 0 && i-length<matrix.length && matrix[i][i-length] != 0)
			{
				cell.access[0] = GridSystem.grid[y][x-1];
			}
			//Right 
			if(i+1 >= 0 && i+1<matrix.length && matrix[i][i+1] != 0)
			{
				cell.access[1] = GridSystem.grid[y+1][x];
			}
			//Bottom 
			if(i+length >= 0 && i+length<matrix.length && matrix[i][i+length] != 0)
			{
				cell.access[2] = GridSystem.grid[y][x+1];
			}
			//Left 
			if(i-1 >= 0 && i-1<matrix.length && matrix[i][i-1] != 0)
			{
				cell.access[3] = GridSystem.grid[y-1][x];
			}
			try {
				thread.sleep(Game.algoSpeed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static int[][] convertToMatrix()
	{
		kruskalSettingUp();
		int matrixOfCells[][] = new int[GridSystem.grid.length*GridSystem.grid.length][GridSystem.grid.length*GridSystem.grid.length];
		for(int j = 0; j < GridSystem.grid.length; j++)
		{
		      for(int i = 0; i < GridSystem.grid.length; i++)
		      {
		          if( GridSystem.grid[i][j].distance[0] != 0) // TOP
		          {
		            matrixOfCells[GridSystem.grid.length*j + i][(GridSystem.grid.length)*(j-1) + i] = GridSystem.grid[i][j].distance[0];
		          }
		          if( GridSystem.grid[i][j].distance[1] != 0) // RIGHT
		          {
		            matrixOfCells[GridSystem.grid.length*j + i][(GridSystem.grid.length)*j + i+ 1] = GridSystem.grid[i][j].distance[1];
		          }
		          if( GridSystem.grid[i][j].distance[2] != 0) // BOTTOM
		          {
		            matrixOfCells[GridSystem.grid.length*j + i][(GridSystem.grid.length)*(j+1) + i] = GridSystem.grid[i][j].distance[2];
		          }
		          if( GridSystem.grid[i][j].distance[3] != 0) // LEFT
		          {
		            matrixOfCells[GridSystem.grid.length*j + i][(GridSystem.grid.length)*j + i - 1] = GridSystem.grid[i][j].distance[3];
		          }
		      }
		}
	    
	    return matrixOfCells;
	 }
	private static void kruskalSettingUp()
	{
		Random rand = new Random();
		
		for(int i = 0; i < GridSystem.grid.length; i++)
		{
			for(int j = 0; j < GridSystem.grid[0].length; j++)
			{
				GridSystem.grid[i][j].setCellColor(new Color(0, 200, 200));
				GridSystem.grid[i][j].setVisited(false);
				GridSystem.grid[i][j].setEnding(false);
				GridSystem.grid[i][j].setPlayer(false);
				int distance;
				//right
				if(checkAccess(i+1,j))
				{
					distance = rand.nextInt(10) + 1;
					GridSystem.grid[i][j].distance[1] = distance;
					GridSystem.grid[i+1][j].distance[3] = distance;
					GridSystem.grid[i][j].access[1] = null;
					GridSystem.grid[i+1][j].access[3] = null;
				}
				//bottom
				if(checkAccess(i,j+1))
				{
					distance = rand.nextInt(10) + 1;
					GridSystem.grid[i][j].distance[2] = distance;
					GridSystem.grid[i][j+1].distance[0] = distance;
					GridSystem.grid[i][j].access[2] = null;
					GridSystem.grid[i][j+1].access[0] = null;
				}
			}
		}
		settingSEPoints();
	}
	public static int[][] kruskalMST()
	{
		kruskal p = new kruskal(convertToMatrix());
		return p.MST();
	}
	//Helping Methods//
	private static boolean validUnvisited(int x, int y)
	{
		if(x < 0 || y < 0 || x >= GridSystem.grid[0].length || y >= GridSystem.grid.length)
			return false;
		return !GridSystem.grid[x][y].getVisited();
	}
	private static boolean checkAccess(int x, int y)
	{
		if(x < 0 || y < 0 || x >=GridSystem.grid.length || y >= GridSystem.grid.length)
			return false;
		return true;
	}
	private static void settingSEPoints()
	{
		Random rand = new Random();
		int num = rand.nextInt(GridSystem.grid.length);
		GridSystem.currentX = 0;
		GridSystem.currentY = num;
		GridSystem.grid[0][num].setPlayer(true);
		GridSystem.grid[GridSystem.grid[0].length-1][num].setEnding(true);
		Cell.setDestPoint(GridSystem.grid[GridSystem.grid[0].length-1][num]);
	}
}
class EdgeData {
    int start; // starting point of edge
    int end;   // end of edge
    int weight; // weight of edges
    public EdgeData(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }	
}
//KRUSKAL IMPLEMENTATION FOR MATRIX//
class kruskal {

	public int counter=0;
	public int vertices[];
	public int[][] Matrix;    // adjacency matrix
    public static final int INF = Integer.MAX_VALUE;   // maximum

	public kruskal(int matrices[][]) {
		vertices = new int[matrices.length];
		for(int i = 0; i<matrices.length; i++)
		{
			vertices[i] = i;
		}
		int vert = vertices.length;
		
		Matrix = new int[vert][vert];
        for (int i = 0; i < vert; i++){
            for (int j = 0; j < vert; j++){
            	if(matrices[i][j] != 0)
            	{
            		Matrix[i][j] = matrices[i][j];
            	}
            	else
            	{
            		Matrix[i][j] = INF;
            	}
            }
        }
        
        for (int i = 0; i < vert; i++){
            for (int j = i+1; j < vert; j++){
                if (Matrix[i][j]!=INF){
                    counter++;
                }
            }
        }
	}
	public int getPosition(int ch) {
        for(int i=0; i<vertices.length; i++){
            if(vertices[i]==ch){
                return i;
            }
        }
        return -1;
    }
	public void printMatrix() {
        System.out.printf("Martix Graph:\n");
        for (int i = 0; i < vertices.length; i++) {
            for (int j = 0; j < vertices.length; j++)
                System.out.printf("%3d ", Matrix[i][j]);
            System.out.printf("\n");
        }
    }

    private EdgeData[] getEdges() {
        int index=0;
        EdgeData[] edges;
 
        edges = new EdgeData[counter];
        //A half of the triangles are counted, because the undirected graph is symmetric,
        for (int i=0; i < vertices.length; i++) {
            for (int j=i+1; j < vertices.length; j++) {
                if (Matrix[i][j]!=INF) {
                    edges[index++] = new EdgeData(vertices[i], vertices[j], Matrix[i][j]);
                }
            }
        }
 
        return edges;
    }
    public int gettheEnd(int[] tends, int i) {
        while (tends[i] != 0){
            i = tends[i];
        }
        return i;
    }
    
    public void sortEdges(EdgeData[] edges, int elen) {
    	 
        for (int i=0; i<elen; i++) {
            for (int j=i+1; j<elen; j++) {
                if (edges[i].weight > edges[j].weight) {
                    
                    EdgeData tmp = edges[i];
                    edges[i] = edges[j];
                    edges[j] = tmp;
                }
            }
        }
    }
    

    public EdgeData[] btskruskal() {
        int index = 0;
        int[] indexarray = new int[counter];
        EdgeData[] result = new EdgeData[Matrix.length - 1];
        EdgeData[] edges;                      
        
        edges = getEdges();
        
        sortEdges(edges, counter);
 
        for (int i=0; i<counter; i++) {
            int p1 = getPosition(edges[i].start); 
            int p2 = getPosition(edges[i].end);   
            int m = gettheEnd(indexarray, p1);     
            int n = gettheEnd(indexarray, p2);       
 
            
            if (m != n) {
                indexarray[m] = n;
                result[index++] = edges[i];   
            }
        }
 
        int length = 0;
        for (int i = 0; i < index; i++){
            length += result[i].weight;
        }
        /*
         //For Showing Edges Data
        System.out.println("Kruskal: ");
        for (int i = 0; i < index; i++){
            System.out.println( result[i].start +" "+ result[i].end + " "+ result[i].weight);
        }
        System.out.printf("\n");
        */
        return result;
    }
    public int[][] MST()
    {
    	EdgeData Edges[] = btskruskal();
    	int MST[][] = new int[Edges.length+1][Edges.length+1];
    	for(int i = 0; i<Edges.length; i++)
    	{
    		MST[Edges[i].start][Edges[i].end] = Edges[i].weight;
    		MST[Edges[i].end][Edges[i].start] = Edges[i].weight;
    	}
    	return MST;
    }
 
}


