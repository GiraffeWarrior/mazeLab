


import processing.core.PApplet;


public class player {
	float x;
	float y;
	float width;
	float height;
	public player(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void drawPlayer(PApplet Main) {
		Main.fill(200, 50, 50);
		Main.rect(x - width/2, y - height/2, width, height);
	}
}
