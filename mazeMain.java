import processing.core.PApplet;

import java.util.ArrayList;
import java.util.HashMap;


public class mazeMain extends PApplet{



	
	maze m = new maze(14,14,0.25);

	Box[][] boxList;

	int playerPosX = 0;
	int playerPosY = 0;
	HashMap<Box, Box> path = new HashMap<Box, Box>();

	public void settings() {
		size(700, 700);
	}
	public void setup(){
		boxList = m.drawMaze(this);
		m.fillAdjLists();
		System.out.println(m.toString());
		boolean doable = m.breadthFirstSearch(boxList[0][0]).goalFound;
		if(!doable) {
			setup();
		}
	}
	public void keyPressed() {
		if(key == CODED) {
			if(keyCode == UP) {	
				boolean isSquareBlocked = m.isSquareBlocked(playerPosX, playerPosY-1);
				if(!isSquareBlocked) {
					m.makeMove(playerPosX, playerPosY, playerPosX, playerPosY-1);
					//should I move this into player class, probably.
				}
			}
			if(keyCode == DOWN) {
				boolean isSquareBlocked = m.isSquareBlocked(playerPosX, playerPosY+1);
				if(!isSquareBlocked) {
					m.makeMove(playerPosX, playerPosY, playerPosX, playerPosY+1);
				}
			}
			if(keyCode == RIGHT) {
				boolean isSquareBlocked = m.isSquareBlocked(playerPosX+1, playerPosY);
				if(!isSquareBlocked) {
					m.makeMove(playerPosX, playerPosY, playerPosX+1, playerPosY);
				}
			}
			if(keyCode == LEFT) {
				boolean isSquareBlocked = m.isSquareBlocked(playerPosX-1, playerPosY);
				if(!isSquareBlocked) {
					m.makeMove(playerPosX, playerPosY, playerPosX-1,playerPosY);
				}
			}
		}
		if(key == ' ') {
			path = m.breadthFirstSearch(boxList[0][0]).path;
			ArrayList<Box> p = m.returnPath(boxList[0][0], path);
			for(Box b : p) {
				b.color = 255;
			}
		}
		if(key == ',') {
			path = m.depthFirstSearch(boxList[0][0]).path;
			ArrayList<Box> p = m.returnPath(boxList[0][0], path);
			for(Box b : p) {
				b.color = 150;
			}
		}
		
		if (key == 'f') {
			setup();
		}
	
	}
	
	public void draw() {
	
		for(int i = 0; i <m.numCols; i ++) {
			for(int j = 0; j < m.numRows; j ++) {
				Box b = boxList[i][j];
				b.drawBox(this);

				if(b.isOccupied) {
					player p = new player(b.x + b.width/2, b.y + b.height/2, b.width/3, b.height/2);
					p.drawPlayer(this);
					playerPosX = (int)(b.x/b.width);
					playerPosY = (int)(b.y/b.height);
				}
			}
		}




	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("mazeMain");
	}

}
