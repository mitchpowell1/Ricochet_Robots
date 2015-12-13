package GameComponents;

public class BoardState {
	private String currentState;
	private BoardState prevState;
	
	public BoardState(String cur, BoardState prev){
		this.currentState = cur;
		this.prevState = prev;
	}
	
	public BoardState getPrev(){
		return prevState;
	}
	
	public String getCur(){
		return currentState;
	}
	
	public boolean hasPrev(){
		if(prevState == null){
			return false;
		} else {
			return true;
		}
	}
}
