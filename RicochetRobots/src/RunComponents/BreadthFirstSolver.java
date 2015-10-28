package RunComponents;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import GameComponents.Board;
import GameComponents.BoardState;
import GameComponents.Robot;
import GameComponents.Square;
import GameComponents.TwoTuple;
 
/**
 * Ricochet Robot solver using a breadth first search algorithm
 * @author Mitch Powell
 */
public class BreadthFirstSolver {
	private Board board;
	private String target;
	private ArrayList<Robot> robots;
	private ArrayList<String> visitedStates;
	
	public BreadthFirstSolver(Board b, ArrayList<Robot> r){
		this.board = b;
		robots = new ArrayList<Robot>();
		for(Robot bot: r){
			robots.add(bot);
			}
		this.visitedStates = new ArrayList<String>();
		this.target = board.getTarget();
		}
	
	// Rearranging the robots now saves a lot of operations later on.
	public void rearrangeBots(){
		Robot temp;
		for(int i=1; i<robots.size(); i++){
			if(robots.get(i).isObjectBot()){
				temp = robots.get(i);
				robots.set(i, robots.get(0));
				robots.set(0, temp);
			}
		}
	}
	
	public boolean checkWin(String state){
		if(state.substring(0, 2).equals(target)){
			return true;
		} else {
			return false;
		}
	}
	
	public void solve(){
		rearrangeBots();
		visitedStates.add(board.getState());
		LinkedList<BoardState> moveQueue = new LinkedList<BoardState>();
		for(Robot bot : robots){
			Square square = board.getSquare(bot.getRow(), bot.getCol());
			for(TwoTuple adjacency : square.getModAdjacencies()){
				bot.moveTo(adjacency);
				moveQueue.offer(new BoardState(board.getState(),null));
				bot.moveTo(new TwoTuple(square.getRow(),square.getCol()));
			}
		}
		while(!moveQueue.isEmpty()){
			BoardState currentState = moveQueue.poll();
			String cur = currentState.getCur();
			board.removeBots();
			for(int i = 0; i<4; i++){
				board.placeBot(cur.substring(2*i, 2*i+2), robots.get(i));
			}
			board.modifyAdjacencies();
			for(Robot bot : robots){
				Square square = board.getSquare(bot.getRow(),bot.getCol());
				for(TwoTuple adjacency : square.getModAdjacencies()){
					bot.moveTo(adjacency);
					if(!visitedStates.contains(board.getState())){
						if(checkWin(board.getState())){
							System.out.println("The game is won");
							break;
						} else {
							moveQueue.offer(new BoardState(board.getState(),currentState));
						}
					}
				}
			}
		}
		System.out.println(visitedStates.get(0));
	}
}
