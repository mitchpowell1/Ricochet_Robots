package GameComponents;

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
	
	public String toString(){
		return "("+aValue+","+bValue+")";
	}
}
