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
	
	private static final int BOARD_SIZE = 16;
	private static final int ROBOT_NUMBER = 4;
	
	public static void main(String[] args) throws FileNotFoundException {
		
		Board board = makeBoard();	
		board.createRobots(ROBOT_NUMBER);
		System.out.println(board);
		System.out.println("Robot 1: " +board.getRobots()[0]);
		System.out.println(board.getSquareAdjacencies(0,0).toString());
		
	}
	
	public static Board makeBoard() throws FileNotFoundException{
		AdjacencyReader reader = new AdjacencyReader("boardAdjacencies",BOARD_SIZE);
		TwoTuple[][][] adjacencyList = reader.Read();
		Board board = new Board(BOARD_SIZE);
		for(int row=0; row< adjacencyList.length; row++){
			for(int col=0; col<adjacencyList.length; col++){
				board.setSquareAdjacencies(row,col,adjacencyList[row][col]);
			}
		}
		return board;
	}
}
