package GameComponents;

public class Square{
	private int row;
	private int col;
	private Square[] adjacencies;
	private boolean occ;
	private boolean target;
	private boolean isCenter;
	
	public Square(int r, int c, Square[] adj, boolean occupied, boolean targ, boolean cent){
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
	
	public Square[] getAdjacencies() {
		return adjacencies;
	}
	
	public void setAdjacencies(Square[] adj){
		this.adjacencies = adj;
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
		if(occ){
			return "1 ";
		} else if(isCenter){
			return "\u25A9 ";
		} else{
			return "0 ";
		}
	}
	
}