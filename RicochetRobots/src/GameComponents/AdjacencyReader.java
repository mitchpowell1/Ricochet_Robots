package GameComponents;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Reads in an adjacency list for the squares in a ricohet robots board
 * @author Mitch Powell
 *
 */
public class AdjacencyReader {
private File readFile;
private int size;

	/**
	 * Constructor of an adjacency reader instance
	 * @param file the file to be read
	 * @param s the number of squares in a row/column (assumes square board)
	 */
	public AdjacencyReader(String file, int s){
		this.readFile = new File(file);
		this.size = s;
	}
	
	/**
	 * Reads the given file
	 * @return A three dimensional array of two tuples, [row][column][adjacencies]
	 * @throws FileNotFoundException
	 */
	public TwoTuple[][][] Read() throws FileNotFoundException{
		TwoTuple[][][] adjacencies = new TwoTuple[size][size][4];
		Scanner fileScanner = new Scanner(readFile);
		for(int row=0; row<size; row++){
			for(int col=0; col<size; col++){
				if(fileScanner.hasNext()){
					String[] squareAdjacencies = fileScanner.next().split(",");
					if(squareAdjacencies[0].equals("X")){
						adjacencies[row][col][0] = new TwoTuple(null,null);
					} else {
						for(int k=0; k<squareAdjacencies.length; k++){
							adjacencies[row][col][k] = new TwoTuple(Integer.parseInt(squareAdjacencies[k].substring(0,1),16), Integer.parseInt(squareAdjacencies[k].substring(1),16));
						}
					}
				}
			}
		}
		fileScanner.close();
		return adjacencies;
		
	}
}
