import processing.core.PApplet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

public class maze {
	int numRows;
	int numCols;
	double probBlocked;
	Box maze[][];
	static float boxWidth;
	static float boxHeight;
	Box last;
	
	
	public maze(int numRows, int numCols, double probBlocked) {
		this.numRows = numRows;
		this.numCols = numCols;
		this.probBlocked = probBlocked;
		maze = new Box[numRows][numCols];
	
		
	}
	public Box[][] drawMaze(PApplet Main) {
		boxWidth = Main.width/(float)numCols;
		boxHeight = Main.height/(float)numRows;
		ArrayList<Box> filler = new ArrayList<Box>();
		int color = 200;
		boolean blocked = false;
		for(int i = 0; i < numRows; i ++) {
			for(int j = 0; j < numCols; j ++) {
				double chance = Math.random();
				if(chance < probBlocked) {
					color = 0;
					blocked = true;
				}
				if(chance >= probBlocked) {
					color = 200;
					blocked = false;
				}
				Box b = new Box(j * boxWidth, i* boxHeight, boxWidth, boxHeight, color, blocked, false, false, filler);
				maze[i][j] = b;
			
			}
			
		}
		Box first = maze[0][0];
		first.color = 200;
		first.blocked = false;
		first.isOccupied = true;
		last = maze[numRows-1][numCols-1];
		last.color = 200;
		last.blocked = false;
		last.isGoal = true;
		return maze;
	}
	public ArrayList<Box> findAdj(Box box) {
		ArrayList<Box> temp = new ArrayList<Box>();
		ArrayList<Box> fin = new ArrayList<Box>();
		int j = (int) (box.x/box.width);
		int i =	(int) (box.y/box.height);
		
		if(i-1 >= 0 ) {
			//up
			temp.add(maze[i-1][j]);
			
		}
		if(i + 1 < numRows) {
			//down
			temp.add(maze[i+1][j]);
			
		}
		if(j -1 >= 0 ) {
			//left
			temp.add(maze[i][j -1]);
		}
		if(j+1< numCols) {
			//right
			temp.add(maze[i][j + 1]);
			
		}
		for(int n = 0; n <= temp.size()-1; n ++) {
			Box t = temp.get(n);
			if(!t.blocked) {
				fin.add(t);
			}
	}
		
		
		return fin;
			}
	public void fillAdjLists() {
		for(int i = 0; i < numRows; i ++) {
			for(int j = 0; j < numCols; j ++) {
				maze[i][j].adjacent = findAdj(maze[i][j]);
			}
		}
	}
		
	public boolean isOccupied(int i, int j) {
		boolean isOccupied = false;
		return isOccupied;
	}
	
	
	
	
	public boolean isSquareBlocked(int i, int j) {
		boolean isSquareBlocked;
		//check maze array for blocked variable, i and j should be maze array coordinates now
		
		if(i <numCols && j< numRows && i> -1 && j > -1) {
			Box b = maze[j][i];
		if(b.blocked) {
			isSquareBlocked = true;
		}
		else {
			isSquareBlocked = false;
		}
		}
		else {
			isSquareBlocked = true;
			}
		
		return isSquareBlocked;
	}
		
	
	public void makeMove(int initX, int initY, int finX, int finY) {
		Box b = maze[finY][finX];
		b.isOccupied = true;
		Box box = maze[initY][initX];
		box.isOccupied = false;
	
	}
	public String toString() {
		String mazeString = "";

		for(int i = 0; i < numRows; i ++) {

		mazeString += "|";
			for(int j = 0; j < numCols; j ++) {
				String section;
				Box b = maze[i][j];
				if(b.blocked) {
					section = "  ";
				}
				else {
					section = "||";
				}
				mazeString += section;
			}
			mazeString += "|\n";
		}
		return mazeString;
	}
	public ArrayList<Box> returnPath(Box startSquare, HashMap<Box, Box> path){
		
		ArrayList<Box> fin = new ArrayList<Box>();
		Box pred = returnBox(last, path);
		fin.add(pred);
		while(path.containsKey(pred)) {
			pred = returnBox(pred, path);
			fin.add(pred);
		}
		return fin;
	}
	public Box returnBox(Box box, HashMap<Box, Box> path) {
		Box pred = path.get(box);
		return pred;
	}
	public inventory depthFirstSearch(Box startSquare) {
		stackList path = new stackList();
		HashSet<Box> reachable = new HashSet<Box>();
		HashMap<Box, Box> predecessorMap = new HashMap<Box, Box>();
		if(!reachable.contains(startSquare)) {
		path.push(startSquare);
		reachable.add(startSquare);
		}
		boolean goalfound = false;
		while(path.head!= null && !goalfound) {
			stackNode current = path.pop();
			Box cBox = current.data;
			if(!reachable.contains(cBox)) {
				reachable.add(cBox);
			}
			
			if(cBox.isGoal) {
				goalfound = true;
				cBox.color = 255;
			}
			else {
				ArrayList<Box> adjacent = cBox.adjacent;
				for(int i = 0; i <= adjacent.size()-1; i ++) {
					if(!reachable.contains(adjacent.get(i))) {
						path.push(adjacent.get(i));
						reachable.add(adjacent.get(i));
						predecessorMap.put(adjacent.get(i),cBox);
					}
				}
				}
			}
			inventory inv = new inventory(predecessorMap, goalfound);
		
		return inv;
	}
	
	
	public inventory breadthFirstSearch(Box startSquare) {
		queueList<Box> path = new queueList<Box>();
		HashSet<Box> reachable = new HashSet<Box>();
		HashMap<Box, Box> predecessorMap = new HashMap<Box, Box>();
		if(!reachable.contains(startSquare)) {
		path.enqueue(startSquare);
		reachable.add(startSquare);
		}
		boolean goalfound = false;
		while(path.head!= null && !goalfound) {
			queueNode<Box> current = path.dequeue();
			Box cBox = current.data;
			if(!reachable.contains(cBox)) {
				reachable.add(cBox);
			}
		
			if(cBox.isGoal) {
				goalfound = true;
			}
			else {
				ArrayList<Box> adjacent = cBox.adjacent;
				for(int i = 0; i <= adjacent.size()-1; i ++) {
					if(!reachable.contains(adjacent.get(i))) {
						path.enqueue(adjacent.get(i));
						reachable.add(adjacent.get(i));
						predecessorMap.put(adjacent.get(i),cBox);
					}
				}
				}
			}
			
		inventory inv = new inventory(predecessorMap, goalfound);
		
		return inv;
	}	
	
}
