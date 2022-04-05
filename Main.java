import java.io.*;
import java.util.*;
/**
 *. Main method that runs the solveMaze program and instructs the user on how 
 *. to use the program. 
 *  also includes a try catch method to handle the error when a user inputs
 *  a file that does not exist. 
 * 
 *  @author Sabrina Hatch, Anh Nguyen
 *. @version Spring 2022
 */
class Main {
  public static void main(String[] args) {
    System.out.println("\n"
        + "Hello! Welcome to the Java Maze Solver! to solve your maze, either enter 'java MazeSolver' followed directly by the name of the file you want to check, or use the following notation to read in file: 'java SpellChecker < NAME_OF_FILE'. Please make sure to type in an input before you hit enter or else the program will not work correctly! In that case, please restart the program and try again."
        + "\n");
		Scanner file = null;
		try 
		{
			file = new Scanner(new File("maze2"));
		} 
		catch (FileNotFoundException e) 
		{
			System.err.println("Cannot locate file.");
			System.exit(-1);
		}
    Maze maze = new Maze(MazeReader.mazeElements(file));
    MazeViewer viewer = new MazeViewer(maze);
		maze.solveMaze();
    System.out.println("Waiting for new maze... Please exit the Maze Display window and enter a new file to be read.");
  }
}