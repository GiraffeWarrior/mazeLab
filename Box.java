import processing.core.PApplet;
import java.util.ArrayList;
public class Box {
	float x;
	float y;
	float width;
	float height;
	int color;
	boolean blocked;
	boolean isOccupied;
	boolean isGoal;
	ArrayList<Box> adjacent;
	public Box(float x, float y, float width, float height, int color, boolean blocked, boolean isOccupied, boolean isGoal, ArrayList<Box> adjacent) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		this.blocked = blocked;
		this.isOccupied = isOccupied;
		this.isGoal = isGoal;
		this.adjacent = adjacent;
	}
	public void drawBox(PApplet Main) {
		Main.fill(color);
		Main.rect(x, y, width, height);
	}
}
