package GameComponents;

/**
 * Keeps a row, column coordinate pair for a square
 * kind of a silly addition, should be phased out if possible.
 * 
 * @author Mitch Powell
 *
 */
public class TwoTuple {
	private Integer aValue;
	private Integer bValue;
	
	/**
	 * Tuple constructor
	 * @param r the value to be stored as the Row
	 * @param c the value to be stored as the Column
	 */
	public TwoTuple(Integer a, Integer b){
		this.aValue = a;
		this.bValue = b;
	}
	
	/**
	 * Sets the row value
	 * @param newA The new row value
	 */
	public void setAValue(int newA){
		this.aValue = newA;
	}
	
	/**
	 * Returns the row value
	 * 
	 * @return the row value
	 */
	public Integer getAValue(){
		return aValue;
	}
	
	/**
	 * Sets the column value
	 * @param newB the new column value
	 */
	public void setBValue(int newB){
		this.bValue = newB;
	}
	
	/**
	 * Returns the current column value
	 * @return the current column value
	 */
	public Integer getBValue(){
		return bValue;
	}
	
	/**
	 * String to be used for concatenation
	 */
	public String toString(){
		return "("+aValue+","+bValue+")";
	}
}
