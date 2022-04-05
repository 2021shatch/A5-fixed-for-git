import java.util.*;
/**
 * Reads the maze file and return a 2D character array containing maze contents.
 */
 
public class MazeReader {

/**
* creates a 2D character array of all the contents in the maze file
* @param Scanner file - will be used to read in the file for the maze line by line 
* @return a 2D character array "arrayMatrix" which contains all the elements in the maze file
*/
  
	public static Character[][] mazeElements(Scanner file)
  {
   ArrayList<Character[]> mazeList = new ArrayList<Character[]>();
		
		while (file.hasNextLine()) 
		{
			String line = file.nextLine();
			ArrayList<Character> mazeRow = new ArrayList<Character>();
      for (int i = 0; i < line.length(); i++) {
				mazeRow.add(line.charAt(i));
			}
			Character[] arrayRow = mazeRow.toArray(new Character[mazeRow.size()]);
			mazeList.add(arrayRow);
		}
		Character[][] arrayMatrix = mazeList.toArray(new Character[mazeList.size()][]);
    return arrayMatrix;
  }
}