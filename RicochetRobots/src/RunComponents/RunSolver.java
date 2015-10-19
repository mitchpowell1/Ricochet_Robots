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
	
	public static void main(String[] args) throws FileNotFoundException {
		
		Board board = makeBoard();	
		board.createRobots();
		board.revertAdjacencies();
		GameComponents.Robot bot = board.getRobots()[0];
		System.out.println(bot.toString());
		System.out.println(board);
		for(int i=0; i < 20; i++){
			board.moveBotUp(bot);
			System.out.println(bot.toString());
			System.out.println(board);
			board.moveBotRight(bot);
			System.out.println(bot.toString());
			System.out.println(board);
			board.moveBotDown(bot);
			System.out.println(bot.toString());
			System.out.println(board);
			board.moveBotLeft(bot);
			System.out.println(bot.toString());
			System.out.println(board);
		}
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
