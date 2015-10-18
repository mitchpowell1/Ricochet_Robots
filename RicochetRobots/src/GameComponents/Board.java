package GameComponents;

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

public class Board {

	private final int size;
	private Square[][] squares;
	private Robot[] robots;
	
	public Board(int si){
		this.size = si;
		squares = generateBoard(si);
	}
	
	public Square[][] generateBoard(int boardSize){
		Square[][] board = new Square[boardSize][boardSize];
		this.robots = new Robot[4];
		for(int row =0; row<boardSize; row++){
			for(int col = 0; col<boardSize; col++){
				if(boardSize == 16 && (((row == 7)||(row ==8)) && ((col == 7)||(col == 8)))){
					board[row][col] = new Square(row,col,null,false,false,true);
				} else{
					board[row][col] = new Square(row,col,null,false,false,false);
				}
			}
		}
		return board;
	}
	
	public String toString(){
		String boardString = new String();
		for(int row = 0; row<size; row++){
			for(int col = 0; col<size; col++){
				if(squares[row][col].getOcc()){
					for(int rob = 0; rob < robots.length; rob++){
						if(robots[rob].getRow() == row && robots[rob].getCol() == col){
							boardString += robots[rob].getFirstLetter()+" ";
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
	
	public Robot[] getRobots(){
		return robots;
	}
	
	public void createRobots(int robNum){
		if(robNum > 4){
			System.out.println("Adding 4 Robots to the board");
			robNum = 4;
		}
		int totalBots = 0;
		ArrayList<String> colChoices = new ArrayList();
		colChoices.add("Red");
		colChoices.add("Blue");
		colChoices.add("Yellow");
		colChoices.add("Green");
		while(totalBots < robNum){
			Random rand = new Random();
			int randRow = rand.nextInt(size);
			int randCol = rand.nextInt(size);
			if(!squares[randRow][randCol].getIsCenter() && !squares[randRow][randCol].getTarget()){
				int colChoice = rand.nextInt(colChoices.size());
				String colString = colChoices.get(colChoice);
				colChoices.remove(colChoice);
				Robot rob = new Robot(colString,new TwoTuple(randRow,randCol));
				robots[totalBots] = rob;
				squares[randRow][randCol].setOcc(true);
				totalBots+=1;
			}
		}
	}
	
	public void setSquareAdjacencies(int row, int col, TwoTuple[] newadj){
		squares[row][col].setAdjacencies(newadj);
	}
	
	public TwoTuple[] getSquareAdjacencies(int row, int col){
		return squares[row][col].getAdjacencies();
	}
	
}
