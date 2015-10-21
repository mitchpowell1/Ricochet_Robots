package GameComponents;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/** TODO:
 * 1) Make functions to move robots to adjacent squares
 * 2) Make functions to change relevant adjacencies upon occupation/de-occupation of a square
 * 4) Make some motherfucking solver algorithms
 *
 * 
 * @author Mitch Powell
 *
 */

/**
 * A board containing squares, robots and targets
 * 
 * @author Mitch Powell
 *
 */
public class Board {

	private final int size;
	private Square[][] squares;
	private Robot[] robots;

	/**
	 * Creates a board of a particular size
	 * 
	 * @param si
	 *            the size for the board to be
	 */
	public Board(int si) {
		this.size = si;
		squares = generateBoard(si);
	}

	/**
	 * Creates a two dimensional array of squares [row][column]
	 * 
	 * @param boardSize
	 *            the number of squares in a row or a column
	 * @return the array of squares
	 */
	public Square[][] generateBoard(int boardSize) {
		Square[][] board = new Square[boardSize][boardSize];
		this.robots = new Robot[4];
		for (int row = 0; row < boardSize; row++) {
			for (int col = 0; col < boardSize; col++) {
				if (boardSize == 16
						&& (((row == 7) || (row == 8)) && ((col == 7) || (col == 8)))) {
					board[row][col] = new Square(row, col, null, false, false,
							true);
				} else {
					board[row][col] = new Square(row, col, null, false, false,
							false);
				}
			}
		}
		return board;
	}

	/**
	 * String to be used for concatenation
	 */
	public String toString() {
		String boardString = new String();
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				if (squares[row][col].getOcc()) {
					for (int rob = 0; rob < robots.length; rob++) {
						if (robots[rob].getRow() == row
								&& robots[rob].getCol() == col) {
							boardString += robots[rob].getFirstLetter() + " ";
						}
					}
				} else {
					boardString += squares[row][col];
				}
			}
			boardString += "\n";
		}
		return boardString;
	}

	/**
	 * Returns the list of robots currently on the board
	 * 
	 * @return
	 */
	public Robot[] getRobots() {
		return robots;
	}

	/**
	 * Creates four robots and places them on the board at random
	 */
	public void createRobots() {
		ArrayList<Robot> robChoices = new ArrayList<Robot>();
		Robot red = new Robot(Color.RED, "R");
		Robot blue = new Robot(Color.BLUE, "B");
		Robot yellow = new Robot(Color.YELLOW, "Y");
		Robot green = new Robot(Color.GREEN, "G");
		robChoices.add(red);
		robChoices.add(blue);
		robChoices.add(yellow);
		robChoices.add(green);

		placeRobots(robChoices);
		modifyAdjacencies();
	}

	/**
	 * Checks each square in the same row as a robot and changes the modified
	 * adjacency list of that square if needed
	 * 
	 * @param bot
	 *            the robot whose row is being checked/changed
	 */
	public void modRows(Robot bot) {
		int botRow = bot.getRow();
		int botCol = bot.getCol();
		// for each square in the same row as the robot
		for (Square square : squares[botRow]) {
			// if the square is not one of the center squares
			if (!square.getIsCenter()) {
				// if the square is to the left of the robot
				if (square.getCol() < botCol) {
					// for each value in the squares adjacency list
					for (int adj = 0; adj < 4; adj++) {
						TwoTuple adjacency = square.getModAdjacencies()[adj];
						// if the adjacency is not null and the adjacent square
						// is in the row and is to the right of the robot
						if ((adjacency != null)
								&& (adjacency.getAValue() == botRow)
								&& (adjacency.getBValue() >= botCol)) {
							// change the adjacency to one square to the left of
							// the robot
							square.setModAdjacency(adj, new TwoTuple(botRow,
									botCol - 1));
						}
					}
					// otherwise, if the square is to the right of the robot
				} else if (square.getCol() > botCol) {
					// for every entry in that squares adjacency list
					for (int adj = 0; adj < 4; adj++) {
						TwoTuple adjacency = square.getModAdjacencies()[adj];
						// if the adjacency is not null and the adjacent square
						// is in the row and is to the left of the robot
						if ((adjacency != null)
								&& (adjacency.getAValue() == botRow)
								&& (adjacency.getBValue() <= botCol)) {
							// change the adjacency to one square to the right
							// of the robot
							square.setModAdjacency(adj, new TwoTuple(botRow,
									botCol + 1));
						}
					}
				}
			}
		}
	}

	/**
	 * Checks each square in the same column of the robot and changes the values
	 * of the squares modified adjacency list should such a change be necessary.
	 * 
	 * @param bot
	 *            The robot whose column to check
	 */
	public void modCols(Robot bot) {
		int botCol = bot.getCol();
		int botRow = bot.getRow();
		// for each square in the robot's column
		for (Square[] row : squares) {
			Square square = row[botCol];
			// if the square is not in the center
			if (!square.getIsCenter()) {
				// if the square is above the robot
				if (square.getRow() < botRow) {
					// for each entry in the squares adjacency matrix
					for (int adj = 0; adj < 4; adj++) {
						TwoTuple adjacency = square.getModAdjacencies()[adj];
						// if the adjacency is not null and the adjacent square
						// is in the robot's column and the
						// adjacent square is above the robot
						if ((adjacency != null)
								&& (adjacency.getBValue() == botCol)
								&& (adjacency.getAValue() >= botRow)) {
							square.setModAdjacency(adj, new TwoTuple(
									botRow - 1, botCol));
						}
					}
					// otherwise if the square is below the robot
				} else if (square.getRow() > botRow) {
					// for each adjacency in the square's adjacency list
					for (int adj = 0; adj < 4; adj++) {
						TwoTuple adjacency = square.getModAdjacencies()[adj];
						// if the adjacency is not null and the adjacent square
						// is in the robot's columna\ and is below the robot
						if ((adjacency != null)
								&& (adjacency.getBValue() == botCol)
								&& (adjacency.getAValue() <= botRow)) {
							square.setModAdjacency(adj, new TwoTuple(
									botRow + 1, botCol));
						}
					}
				}
			}

		}
	}

	/**
	 * Goes through and checks/modifies the adjacencies for each square in each
	 * row and column of every robot on the board.
	 */
	public void modifyAdjacencies() {
		revertAdjacencies();
		for (Robot bot : robots) {
			modRows(bot);
			modCols(bot);
		}

	}

	/**
	 * Sets the modified adjacency list of every square equal to its original
	 * adjacency list
	 */
	public void revertAdjacencies() {
		for (Square[] row : squares) {
			for (Square col : row) {
				col.setModAdjacencies(col.getAdjacencies());
			}
		}
	}

	/**
	 * Places four robots on the board at random
	 * 
	 * @param robChoices
	 *            the list of robots to place on the board
	 */
	public void placeRobots(ArrayList<Robot> robChoices) {
		int totalBots = 0;
		while (totalBots < 4) {
			Random rand = new Random();
			int randRow = rand.nextInt(size);
			int randCol = rand.nextInt(size);
			if (!squares[randRow][randCol].getIsCenter()
					&& !squares[randRow][randCol].getTarget()
					&& !squares[randRow][randCol].getOcc()) {
				int robChoice = rand.nextInt(robChoices.size());
				Robot rob = robChoices.get(robChoice);
				robChoices.remove(robChoice);
				rob.setLocation(new TwoTuple(randRow, randCol));
				robots[totalBots] = rob;
				squares[randRow][randCol].setOcc(true);
				totalBots += 1;
			}
		}
		modifyAdjacencies();
	}

	/**
	 * Sets the adjacency list for a particular square
	 * 
	 * @param row
	 *            the row value of the square in question
	 * @param col
	 *            the column value of the square in question
	 * @param newadj
	 *            the list of adjacencies to set the square's adjacency list
	 *            equal to
	 */
	public void setSquareAdjacencies(int row, int col, TwoTuple[] newadj) {
		squares[row][col].setAdjacencies(newadj);
	}

	/**
	 * Returns the Modified adjacency list for a particular square
	 * 
	 * @param row
	 *            the row value of a square
	 * @param col
	 *            the column value of a square
	 * @return the square's modified adjacency list
	 */
	public TwoTuple[] getModSquareAdjacencies(int row, int col) {
		return squares[row][col].getModAdjacencies();
	}

	/**
	 * Moves a particular Robot up (North) should be changed to a robot method.
	 * 
	 * @param bot
	 *            the robot to be moved
	 */
	public void moveBotUp(Robot bot) {
		TwoTuple[] adjacencies = squares[bot.getRow()][bot.getCol()]
				.getModAdjacencies();
		for (int i = 0; i < adjacencies.length; i++) {
			if ((adjacencies[i] != null)
					&& (adjacencies[i].getBValue() == bot.getCol())
					&& (adjacencies[i].getAValue() < bot.getRow())) {
				squares[bot.getRow()][bot.getCol()].setOcc(false);
				bot.setLocation(adjacencies[i]);
				squares[adjacencies[i].getAValue()][adjacencies[i].getBValue()]
						.setOcc(true);
				modifyAdjacencies();
			}
		}
	}

	/**
	 * Moves a particular Robot down (South). Should be changed to a robot
	 * method.
	 * 
	 * @param bot
	 *            the robot to be moved
	 */
	public void moveBotDown(Robot bot) {
		TwoTuple[] adjacencies = squares[bot.getRow()][bot.getCol()]
				.getModAdjacencies();
		for (int i = 0; i < adjacencies.length; i++) {
			if ((adjacencies[i] != null)
					&& (adjacencies[i].getBValue() == bot.getCol())
					&& (adjacencies[i].getAValue() > bot.getRow())) {
				squares[bot.getRow()][bot.getCol()].setOcc(false);
				bot.setLocation(adjacencies[i]);
				squares[bot.getRow()][bot.getCol()].setOcc(true);
				modifyAdjacencies();
			}
		}
	}

	/**
	 * Moves a particular Robot left (West). Should be changed to a robot
	 * method.
	 * 
	 * @param bot
	 *            the robot to be moved
	 */
	public void moveBotLeft(Robot bot) {
		TwoTuple[] adjacencies = squares[bot.getRow()][bot.getCol()]
				.getModAdjacencies();
		for (int i = 0; i < adjacencies.length; i++) {
			if ((adjacencies[i] != null)
					&& (adjacencies[i].getAValue() == bot.getRow())
					&& (adjacencies[i].getBValue() < bot.getCol())) {
				squares[bot.getRow()][bot.getCol()].setOcc(false);
				bot.setLocation(adjacencies[i]);
				squares[bot.getRow()][bot.getCol()].setOcc(true);
				modifyAdjacencies();
			}
		}
	}

	/**
	 * Moves a particular Robot right (East). Should be changed to a robot
	 * method.
	 * 
	 * @param bot
	 *            the robot to be moved
	 */
	public void moveBotRight(Robot bot) {
		TwoTuple[] adjacencies = squares[bot.getRow()][bot.getCol()]
				.getModAdjacencies();
		for (int i = 0; i < adjacencies.length; i++) {
			if ((adjacencies[i] != null)
					&& (adjacencies[i].getAValue() == bot.getRow())
					&& (adjacencies[i].getBValue() > bot.getCol())) {
				squares[bot.getRow()][bot.getCol()].setOcc(false);
				bot.setLocation(adjacencies[i]);
				squares[bot.getRow()][bot.getCol()].setOcc(true);
				modifyAdjacencies();
			}
		}
	}

}
