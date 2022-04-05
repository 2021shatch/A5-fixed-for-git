import java.util.*;
import java.io.*;
/**
* takes the file and runs it through MazeReader and solveMaze()
*/
public class MazeSolver {

  /** 
	*	Program to solve maze, can take in command line input or 
  * @param a String array
  * @return N/A nothing is returned
  */
	public static void main(String[] args){
		if (args.length == 0) {
			Scanner file = new Scanner(System.in);
			Maze maze = new Maze(MazeReader.mazeElements(file));
    	MazeViewer viewer = new MazeViewer(maze);
			maze.solveMaze();
		} else {
			Scanner file = null;
	    try 
			{
				file = new Scanner(new File(args[0]));
			} 
			catch (FileNotFoundException e) 
			{
				System.err.println("Cannot locate file.");
				System.exit(-1);
			}
			Maze maze = new Maze(MazeReader.mazeElements(file));
    	MazeViewer viewer = new MazeViewer(maze);
			maze.solveMaze();
		}
	}
}