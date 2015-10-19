package GameComponents;

public class Square{
	private int row;
	private int col;
	private TwoTuple[] adjacencies;
	private TwoTuple[] modAdjacencies;
	private boolean occ;
	private boolean target;
	private boolean isCenter;
	
	public Square(int r, int c, TwoTuple[] adj, boolean occupied, boolean targ, boolean cent){
		this.row = r;
		this.col = c;
		this.adjacencies = adj;
		this.occ = occupied;
		this.target = targ;
		this.isCenter = cent;
	}
	
	public int getRow(){
		return row;
	}
	
	public void setRow(int r){
		this.row = r;
	}
	
	public int getCol(){
		return col;
	}
	
	public void setCol(int c){
		this.col = c;
	}
	
	public TwoTuple[] getAdjacencies() {
		return adjacencies;
	}
	
	public void setAdjacencies(TwoTuple[] adj) {
		this.adjacencies = adj;
	}
	
	public void setModAdjacency(int adjNum, TwoTuple newAdj){
		this.modAdjacencies[adjNum] = newAdj;
	}
	
	public TwoTuple[] getModAdjacencies(){
		return modAdjacencies;
	}
	
	public void setModAdjacencies(TwoTuple[] adj){
		this.modAdjacencies = adj;
	}
	
	public boolean getOcc(){
		return occ;
	}
	
	public void setOcc(boolean o){
		this.occ = o;
	}
	
	public boolean getTarget(){
		return target;
	}
	
	public void setTarget(boolean t){
		this.target = t;
	}
	
	public boolean getIsCenter(){
		return isCenter;
	}
	
	public void setIsCenter(boolean c){
		this.isCenter = c;
	}
	
	public String toString(){
		if(isCenter){
			return "X ";
		} else{
			return "_ ";
		}
	}
	
}