package RunComponents;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import GameComponents.AdjacencyReader;
import GameComponents.Board;
import GameComponents.Robot;
import GameComponents.TwoTuple;

/**
 * Class containing the main method, Eventually will contain methods to create
 * the robots and then place them on the board, and then will call methods to
 * run each of the different solving algorithms as they get developed
 * 
 * @author Mitch Powell
 *
 */
public class RunSolver {

	private static final int BOARD_SIZE = 16;

	public static void main(String[] args) throws FileNotFoundException {

		Runtime runtime = Runtime.getRuntime();
		long startTime = System.currentTimeMillis();
		Board board = makeBoard("boardAdjacencies", BOARD_SIZE);
		Robot redBot = new Robot(java.awt.Color.RED, "R");
		Robot blueBot = new Robot(java.awt.Color.BLUE, "B");
		Robot greenBot = new Robot(java.awt.Color.GREEN, "G");
		Robot yellowBot = new Robot(java.awt.Color.YELLOW, "Y");
		redBot.setObjectBot(true);
		ArrayList<Robot> botList = new ArrayList<Robot>();
		botList.add(redBot);
		botList.add(blueBot);
		botList.add(greenBot);
		botList.add(yellowBot);
		for(Robot bot: botList){
			bot.setBoard(board);
		}
		board.placeRobots(botList);
		board.pickRandTargetSquare();
		System.out.println(board.toString());
		BreadthFirstSolver bfs = new BreadthFirstSolver(board,botList);
		bfs.solve();
		System.out.println(board.getState());
		long endTime = System.currentTimeMillis();
		System.out.println("Time elapsed: "+(endTime-startTime)+" milliseconds");
	}

	/**
	 * Creates a new Ricochet robots board
	 * 
	 * @param file
	 *            the file to scan to initialize square adjacencies
	 * @param board_size
	 *            the number of squares in a row/column
	 * @return the Board object that was created
	 * @throws FileNotFoundException
	 */
	public static Board makeBoard(String file, int board_size)
			throws FileNotFoundException {
		AdjacencyReader reader = new AdjacencyReader(file, board_size);
		TwoTuple[][][] adjacencyList = reader.Read();
		Board board = new Board(BOARD_SIZE);
		//for each row
		for (int row = 0; row < adjacencyList.length; row++) {
			// for each square in the row
			for (int col = 0; col < adjacencyList.length; col++) {
				//for each item in the adjacency list
				ArrayList<TwoTuple> adjacencies = new ArrayList<TwoTuple>();
				for(int adj = 0; adj<adjacencyList[row][col].length; adj ++)
					if(adjacencyList[row][col][adj] != null){
						adjacencies.add(adjacencyList[row][col][adj]);
					}
				board.setSquareAdjacencies(row, col, adjacencies);
			}
		}
		return board;
	}
}
