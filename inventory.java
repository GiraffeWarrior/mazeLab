import java.util.HashMap;

public class inventory {
	HashMap<Box, Box> path;
	boolean goalFound;
	public inventory(HashMap<Box, Box> path, boolean goalFound) {
		this.path = path;
		this.goalFound = goalFound;
	}
}
