package swarmBots.Rover17Utilities;

import java.util.ArrayList;

public class CountRepeats {
	private final int REPEAT_LIMIT = 20;
	private ArrayList<Coordinates> countList;
	private int backwardCount;
	private int moveCounter;
	private int realCounter;
	
	public CountRepeats() {
		this.countList = new ArrayList<Coordinates>();
	}
	/*
	 * gets the top of the stack and compares it with the new tile, if it is
	 * not equal it's a new move else it's a duplicate
	 */
	public boolean isRepeating(Coordinates tile) {
		boolean repeating = false;
		if (!tile.equals(countList.get(countList.size() - 1))) {
			countList.add(tile);
			if (backwardCount > 0)
				backwardCount--;
			moveCounter++;
			realCounter++;
		} else {//it's a duplicate tile
			countList.remove(countList.size() - 1);
			backwardCount++;
			moveCounter--;
			realCounter++;
		}

		 // preventing the array from being over sized 
		if (countList.size() > REPEAT_LIMIT)
			countList.remove(0);
		/*
		 * if there are about 20 continued repeated tiles, the rover repeats the move;
		 * OR, @param realCounter >3*moveCounter, the rover is repeating the move in a smaller loop
		 * and backwardCount does not reach to 20; Therefore, the path should be changed
		 */
		if (backwardCount > REPEAT_LIMIT || realCounter > 3*moveCounter){
			resetVars();
			repeating = true;
		}
		return repeating;
	}
	
	public void resetVars(){
		backwardCount = 0;
		moveCounter = 0;
		realCounter = 0;
	}
}
