
/**
 *. Class that contains the logic (using recursion) to solve a maze 
 * 
 *  @author Sabrina Hatch, Anh Nguyen
 *. @version Spring 2022
 */
public class Maze implements DisplayableMaze {

	/** field storing height of maze grid or number of rows */
	private int height;

	/** field storing width of maze grid or number of columns */
	private int width;

	/** field storing start position of maze grid */
	private MazeLocation start;

	/** field storing finish position of maze grid */
	private MazeLocation finish;

	/** field storing maze grid */
	private MazeContents[][] maze;


	/**
  * @param takes in a 2D character array (mazeArray) of all the characters        * inside the maze
  * @return returns a regular int array "start" that includes the "x    
  * and y-like coords" of the start postion from the 2D array (mazeArray) of      * all the elements in the maze
  */
	private int[] startPosition(Character[][] mazeArray){
		int[] start = new int[2];
		for (int i = 0; i < mazeArray.length; i++) {
    	for (int j = 0; j < mazeArray[i].length; j++) {
      	if (mazeArray[i][j] == 'S') {
					start[0] = i;
					start[1] = j;
      	}
    	}
 		}
		return start;
	}
	
	/**
  * @param takes in a 2D character array (mazeArray) of all the characters        * inside the maze
  * @return returns a regular int array "finish" that includes the "x
  * and y-like coords" of the finish postion from the 2D array (mazeArray) of     * all the elements in the maze
  */
	private int[] finishPosition(Character[][] mazeArray){
		int[] finish = new int[2];
		for (int i = 0; i < mazeArray.length; i++) {
    	for (int j = 0; j < mazeArray[i].length; j++) {
      	if (mazeArray[i][j] == 'F') {
					finish[0] = i;
					finish[1] = j;
      	}
    	}
 		}
		return finish;
	}
  /**
  * @param takes in a 2D character array (mazeArray) of all the characters        * inside the maze
  * @return returns a 2D "MazeContents" type array "maze" that includes all the   * elements of the maze in a grid like structure
  */
	private MazeContents[][] mazeArrayToContents(Character[][] mazeArray) {
		MazeContents[][] maze = new MazeContents[mazeArray.length][mazeArray[0].length];
		for (int i = 0; i < mazeArray.length; i++) {
    	for (int j = 0; j < mazeArray[i].length; j++) {
      	char element = mazeArray[i][j];
				if (element == '#') {
					maze[i][j] = MazeContents.WALL;
				} else if (element == '.' || element == 'S' || element == 'F') {
					maze[i][j] = MazeContents.OPEN;
				}
    	}
 		}
		return maze;
	}

	/** constructor for the maze
  * @param takes in a 2D character array (mazeArray) of all the characters        * inside the maze
  * @return N/A does not return anything
  */
	public Maze(Character[][] mazeArray) { 
		this.height = mazeArray.length;
		this.width = mazeArray[0].length;
		this.start = new MazeLocation(startPosition(mazeArray)[0], startPosition(mazeArray)[1]);
		this.finish = new MazeLocation(finishPosition(mazeArray)[0], finishPosition(mazeArray)[1]);
		this.maze = mazeArrayToContents(mazeArray);
	}


	
	/** getter for the height of the maze (the number of rows)
  * @param N/A there are no parameters
  * @returns height of maze grid 
  */
	public int getHeight() {
		return height;
	}

	
  /** getter for the width of the maze (the number of columns)
  * @param N/A there are no parameters
  * @returns width of maze grid
  */
	public int getWidth() {
		return width;
	}

	/** gets the contents of maze grid at a specific position in the grid
  * @param 2 integers "i" & "j" which represent coord. like pairs for each        * position in the maze grid
  * @returns contents of maze grid at row i, column j, or flags a particular 
  * position in the maze grid as a wall (meaning it cannot be traversed)
  */
	public MazeContents getContents(int i, int j) {
		if (i < height && j < width) {
			return maze[i][j];
		} else {
			return MazeContents.WALL;
		}
	}

	/** 
  * gets the location of the finish position 
  * @param N/A, has no parameters
  * @return location of maze at start point
  */
	public MazeLocation getStart() {
		return start;
	}

	/** 
  * gets the location of the finish position 
  * @param N/A, has no parameters
  * @return location of maze at finish point
  */
	public MazeLocation getFinish() {
		return finish;  
	}

  /**
  * gives the solveMaze method the tools & logic to solve the maze methodically
  * @param currentPosition which is the exact position within the maze grid       * that the sprite is currently at
  * @return returns a boolean that tells you whether the maze is solveable or not
  */
  public boolean mazeAlg(MazeLocation currentPosition)
  {	
		// Delay code to see animation
		try { Thread.sleep(50);	} catch (InterruptedException e) {};
		
		// Base case: if the current postition is at finish point return true
    if (currentPosition.equals(getFinish())) {
      System.out.println("Finished!");
      // Quits program once you are finished and returns true.
			return true;
		}
		
		// Checks if the current position is explorable, if not go back and choose a different direction. 
		if (!maze[currentPosition.getRow()][currentPosition.getCol()].isExplorable) 
		{
			return false;
		}

		// Marks current location as VISITED
		maze[currentPosition.getRow()][currentPosition.getCol()] = MazeContents.VISITED;

		// If current location is explorable, walks around to find the next place to go

		// Go WEST
		if (currentPosition.getCol() >= 0) // Checks that we can go left, WEST
		{
			if (mazeAlg(currentPosition.neighbor(MazeDirection.WEST))) { 
				// Set current position as PATH
				maze[currentPosition.getRow()][currentPosition.getCol()] = MazeContents.PATH; 
				return true;
			}
		}

		// Go EAST
		if (currentPosition.getCol() < width ) // Checks that we can go right, EAST
		{
			if (mazeAlg(currentPosition.neighbor(MazeDirection.EAST))) { 
				// Set current position as PATH
				maze[currentPosition.getRow()][currentPosition.getCol()] = MazeContents.PATH;
				return true;
			} 
		}

		// Go NORTH
		if (currentPosition.getRow() >= 0) // Check that we can go up, NORTH.
		{
			if (mazeAlg(currentPosition.neighbor(MazeDirection.NORTH))) { 
				// Set current position as PATH
				maze[currentPosition.getRow()][currentPosition.getCol()] = MazeContents.PATH;
				return true;
			} 
		}  

		// Go SOUTH
		if (currentPosition.getRow() <  height) // Check that we can go down, SOUTH.
		{
			if (mazeAlg(currentPosition.neighbor(MazeDirection.SOUTH))) { 
				// Set current position as PATH
				maze[currentPosition.getRow()][currentPosition.getCol()] = MazeContents.PATH;
				return true;
			} 
		} 
		maze[currentPosition.getRow()][currentPosition.getCol()] = MazeContents.DEAD_END;
		// Neighborhood is blocked, backtrack to most recent recursion
		return false;
  }

  /**
  * runs the algorithm on the maze and solves it; also tells the user if there is a solveable path or not. 
  * @param N/A no parameters
  * @return N/A nothing is returned
  */
	public void solveMaze() {
		boolean existPath = mazeAlg(start);
		if (existPath) {
			System.out.println("There exists a path!");
		} else {
			System.out.println("No path found");
		}
	}
}