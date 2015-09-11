/**
 * 
 */
package GameComponents;

/**
 * @author Mitch Powell
 *
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
	
	public boolean isCenter() {
		return isCenter;
	}
	public void setCenter(boolean isCenter) {
		this.isCenter = isCenter;
	}
	public boolean isOccupied() {
		return isOccupied;
	}
	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}
	public boolean isNorthBlocked() {
		return northBlocked;
	}
	public void setNorthBlocked(boolean northBlocked) {
		this.northBlocked = northBlocked;
	}
	public boolean isSouthBlocked() {
		return southBlocked;
	}
	public void setSouthBlocked(boolean southBlocked) {
		this.southBlocked = southBlocked;
	}
	public boolean isEastBlocked() {
		return eastBlocked;
	}
	public void setEastBlocked(boolean eastBlocked) {
		this.eastBlocked = eastBlocked;
	}
	public boolean isWestBlocked() {
		return westBlocked;
	}
	public void setWestBlocked(boolean westBlocked) {
		this.westBlocked = westBlocked;
	}
	
	
}
