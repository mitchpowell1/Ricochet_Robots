package GameComponents;

public class Board {

	private final int size;
	private Square[][] squares;
	
	public Board(int si){
		this.size = si;
		squares = generateBoard(si);
	}
	
	public Square[][] generateBoard(int boardSize){
		Square[][] board = new Square[boardSize][boardSize];
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
				boardString += squares[row][col];
			}
			boardString += "\n";
		}
		return boardString;
	}
	
}
