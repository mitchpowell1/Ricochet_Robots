package GameComponents;

import java.awt.Color;


/**
 * Corresponds to robot square, can move up, down, left or right until it hits a wall and
 * occupies a square
 * @author Mitch Powell
 *
 */
public class Robot {
	private Color color;
	private String letter;
	private TwoTuple location;
	private Board board;
	
	/**
	 * Constructs a robot of a particular color
	 * @param col the color of the robot
	 * @param the let to represent the robot on the console output board
	 */
	public Robot(Color col, String let){
		this.color = col;
		this.letter = let;
	}
	
	/**
	 * Constructs a robot of a particular color at a particular location
	 * @param col the color of the robot
	 * @param loc the location of the robot
	 */
	public Robot(Color col, String let, TwoTuple loc){
		this.color = col;
		this.location = loc;
		this.letter = let;
	}
	
	/**
	 * Returns the color of the robot
	 * @return the color of the robot
	 */
	public Color getColor(){
		return color;
	}
	
	/**
	 * Returns the row the robot is currently in
	 * @return the index of the row the robot currently occupies
	 */
	public int getRow(){
		return location.getAValue();
	}
	
	/**
	 * Returns the column the robot currently occupies
	 * @return the index of the robot's current column
	 */
	public int getCol(){
		return location.getBValue();
	}
	
	/**
	 * Sets the color of the robot
	 * @param col The color for the robot to be
	 */
	public void setColor(Color col){
		this.color = col;
	}
	
	/**
	 * Returns the current location of the robot
	 * @return the current location of the robot
	 */
	public TwoTuple getLocation(){
		return location;
	}
	
	/**
	 * Sets the location of the robot
	 * @param loc the location of the robot
	 */
	public void setLocation(TwoTuple loc){
		this.location = loc;
	}
	
	/**
	 * Returns the first letter of the color. Used for string output of a board.
	 * @return The first letter of the color of the robot
	 */
	public String getFirstLetter(){
		return letter;
	}

	/**
	 * The string to be used for concatenation
	 */
	public String toString(){
		return letter + " robot located at row " + location.getAValue()+ ", column " + location.getBValue();
	}

}
