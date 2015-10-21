package GameComponents;

/***
 * An instance of the square class corresponds to a single square on the board.
 * A square has row and column data, as well as an initial list of adjacencies
 * and a modified list of adjacencies that is edited when robots are
 * placed/moved on the board. A square also has data regarding whether or not it
 * is occupied, whether or not it is the target square, and whether not it is in
 * the center of the board.
 * 
 * @author Mitch Powell
 *
 */
public class Square {
	private int row;
	private int col;
	private TwoTuple[] adjacencies;
	private TwoTuple[] modAdjacencies;
	private boolean occ;
	private boolean target;
	private boolean isCenter;

	/**
	 * Constructor for a new square
	 * 
	 * @param r
	 *            the row the square occupies
	 * @param c
	 *            the column the square occupies
	 * @param adj
	 *            the list of initial adjacencies for the square
	 * @param occupied
	 *            the current status of robot occupation of the square
	 * @param targ
	 *            the current status of the square as the target square for the
	 *            objective robot
	 * @param cent
	 *            the status of the square as a center piece on the board.
	 */
	public Square(int r, int c, TwoTuple[] adj, boolean occupied, boolean targ,
			boolean cent) {
		this.row = r;
		this.col = c;
		this.adjacencies = adj;
		this.occ = occupied;
		this.target = targ;
		this.isCenter = cent;
	}

	/**
	 * Gets the row value for the square
	 * 
	 * @return The row the square resides in (0 is the topmost row, 15 is the
	 *         bottom-most row for a 16x16 board)
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Sets the row location of the square in the board
	 * 
	 * @param r
	 *            the row value for the square
	 */
	public void setRow(int r) {
		this.row = r;
	}

	/**
	 * Returns the column value for the square
	 * 
	 * @return the column that the square resides in (0 is the leftmost column,
	 *         15 is the rightmost column for a 16x16 board)
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Sets the column location of the square in the board.
	 * 
	 * @param c
	 *            the column number for the square to occupy
	 */
	public void setCol(int c) {
		this.col = c;
	}

	/**
	 * Returns the initial list of adjacencies for the square
	 * 
	 * @return the initial list of adjacencies for the square
	 */
	public TwoTuple[] getAdjacencies() {
		return adjacencies;
	}

	/**
	 * initialize the initial list of adjacencies for the square
	 * 
	 * @param adj
	 *            the list of adjacencies for the square.
	 */
	public void setAdjacencies(TwoTuple[] adj) {
		this.adjacencies = adj;
	}

	/**
	 * Change one particular adjacency in a square's modified adjacency list
	 * 
	 * @param adjNum
	 *            the number of the item in the list
	 * @param newAdj
	 *            the new adjacency value to occupy the given index of the
	 *            modified adjacency list
	 */
	public void setModAdjacency(int adjNum, TwoTuple newAdj) {
		this.modAdjacencies[adjNum] = newAdj;
	}

	/**
	 * Returns the modified list of adjacencies for the square
	 * 
	 * @return the modified list of adjacencies
	 */
	public TwoTuple[] getModAdjacencies() {
		return modAdjacencies;
	}

	/**
	 * Sets the list of modified adjacencies for the square
	 * 
	 * @param adj
	 *            the list of modified adjacencies for the square
	 */
	public void setModAdjacencies(TwoTuple[] adj) {
		this.modAdjacencies = adj.clone();
	}

	/**
	 * Returns whether or not the square is occupied
	 * 
	 * @return true if occupied, false if unoccupied
	 */
	public boolean getOcc() {
		return occ;
	}

	/**
	 * Change the occupation of the square
	 * 
	 * @param o
	 *            the new occupation of the square
	 */
	public void setOcc(boolean o) {
		this.occ = o;
	}

	/**
	 * Returns whether or not the square is the target of the objective robot
	 * 
	 * @return true if it is the target, false if it is not the target
	 */
	public boolean getTarget() {
		return target;
	}

	/**
	 * Sets the square to be the target of the objective robot
	 * 
	 * @param t
	 *            the new target status of the square
	 */
	public void setTarget(boolean t) {
		this.target = t;
	}

	/**
	 * Returns whether or not the square is in the center of the board.
	 * 
	 * @return true if in the center, false if it is not in the center.
	 */
	public boolean getIsCenter() {
		return isCenter;
	}

	/**
	 * Sets the new center status of the square
	 * 
	 * @param c
	 *            true if the square is a center piece, false otherwise
	 */
	public void setIsCenter(boolean c) {
		this.isCenter = c;
	}

	/**
	 * String used for concatenation
	 */
	public String toString() {
		if (isCenter) {
			return "X ";
		} else {
			return "_ ";
		}
	}

}