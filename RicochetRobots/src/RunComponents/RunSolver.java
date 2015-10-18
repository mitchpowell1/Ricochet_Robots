package RunComponents;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import GameComponents.AdjacencyReader;
import GameComponents.Board;
import GameComponents.TwoTuple;

public class RunSolver {
	
	private static final int boardSize = 16;
	
	public static void main(String[] args) throws FileNotFoundException{
		
		
		AdjacencyReader reader = new AdjacencyReader("boardAdjacencies",boardSize);
		TwoTuple[][][] adjacencyList = reader.Read();
		Board board = new Board(boardSize);
		System.out.println(board);
		for(int row=0; row< adjacencyList.length; row++){
			for(int col=0; col<adjacencyList.length; col++){
				for(TwoTuple tup : adjacencyList[row][col]){
					if(tup != null){
						System.out.print(tup);
					}
				}
				//print a comma to seperate square adjacencies
				System.out.print(", ");
			}
			System.out.println();
		}
	}

}
