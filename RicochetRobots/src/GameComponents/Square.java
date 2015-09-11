/**
 * 
 */
package GameComponents;

/**
 * Describes a square on the ricochet robots game board
 * @author Mitch Powell
 */
public class Square {
	private int xLocation;
	private int yLocation;
	private boolean isCenter;
	private boolean isOccupied;
	private boolean northBlocked;
	private boolean southBlocked;
	private boolean eastBlocked;
	private boolean westBlocked;

	/**
	 * Constructor for the Square class
	 * 
	 * @param xLocation The X-Coordinate
	 * @param yLocation The Y-Coordinate
	 * @param center Describes whether or not the square resides in the center of the board
	 * @param occupant Whether or not there is a robot in the square
	 * @param north True if the northern border is blocked
	 * @param south True if the southern border is blocked
	 * @param east True if the eastern border is blocked
	 * @param west True if the western border is blocked
	 */
	public Square(int xLocation, int yLocation, boolean center,boolean occupant,boolean north,boolean south,boolean east,boolean west){
		this.xLocation = xLocation;
		this.yLocation = yLocation;
		this.isCenter = center;
		this.isOccupied = occupant;
		this.northBlocked = north;
		this.southBlocked = south;
		this.eastBlocked = east;
		this.westBlocked = west;
	}
	
	/**
	 * Is the square in the center of the game board?
	 * @return True or False
	 */
	public boolean isCenter() {
		return isCenter;
	}
	
	/**
	 * Current status of square occupancy
	 * @return True or False
	 */
	public boolean isOccupied() {
		return isOccupied;
	}
	
	/**
	 * Changes current occupation status of square
	 * @param isOccupied The new occupation status of the square
	 */
	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}
	
	/**
	 * Checks if the northern border has a wall
	 * @return True or False
	 */
	public boolean isNorthBlocked() {
		return northBlocked;
	}
	
	/**
	 * Checks if the southern border has a wall
	 * @return True or False
	 */
	public boolean isSouthBlocked() {
		return southBlocked;
	}

	/**
	 * Checks if the eastern border has a wall
	 * @return True or False
	 */
	public boolean isEastBlocked() {
		return eastBlocked;
	}

	/**
	 * Checks if the western border has a wall
	 * @return
	 */
	public boolean isWestBlocked() {
		return westBlocked;
	}

	/**
	 * Sets the X-Coordinate
	 * @param xLocation The new X-Coordinate of the square
	 */
	public void setxLocation(int xLocation) {
		this.xLocation = xLocation;
	}

	/**
	 * Sets the Y-Coordinate
	 * @param yLocation The new Y-Coordinate of the square
	 */
	public void setyLocation(int yLocation) {
		this.yLocation = yLocation;
	}

	/**
	 * Sets the northern wall
	 * @param northBlocked the new blocked status of the northern border
	 */
	public void setNorthBlocked(boolean northBlocked) {
		this.northBlocked = northBlocked;
	}

	/**
	 * Sets the southern wall
	 * @param southBlocked the new blocked status of the southern border
	 */
	public void setSouthBlocked(boolean southBlocked) {
		this.southBlocked = southBlocked;
	}

	/**
	 * Sets the eastern wall
	 * @param eastBlocked the new blocked status of the eastern border
	 */
	public void setEastBlocked(boolean eastBlocked) {
		this.eastBlocked = eastBlocked;
	}

	/**
	 * Sets the wester wall
	 * @param westBlocked the new blocked status of the western border
	 */
	public void setWestBlocked(boolean westBlocked) {
		this.westBlocked = westBlocked;
	}

	
}
