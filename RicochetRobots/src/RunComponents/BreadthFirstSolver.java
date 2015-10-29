package RunComponents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import GameComponents.Board;
import GameComponents.BoardState;
import GameComponents.Robot;
import GameComponents.Square;
import GameComponents.TwoTuple;
 
/**
 * Ricochet Robot solver using a Breadth First Search algorithm
 * @author Mitch Powell
 */
public class BreadthFirstSolver {
	private Board board;
	private String target;
	private ArrayList<Robot> robots;
	private ArrayList<String> visitedStates;
	
	/**
	 * Constructor for a BreadthFirstSolver object, whose function is to solve
	 * a ricochet robots board.
	 * @param b the board to be solved
	 * @param r an ArrayList containing the robots on the board
	 */
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
	
	/**
	 * Takes a state string and 
	 * @param state the state string to check against the target square
	 * @return true if the object robot is on the target square, false otherwise
	 */
	public boolean checkWin(String state){
		if(state.substring(0, 2).equals(target)){
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * Method to trace back the states that lead to the winning solution
	 * @param state the BoardState object representing the board at the time of the victory
	 */
	public void traceStates(BoardState state){
		board.removeBots();
		if(state.hasPrev()){
			for(int i=0; i<4; i++){
				board.placeBot(state.getCur().substring(2*i,(2*i)+2), robots.get(i));
			}
			System.out.println(board.toString());
			traceStates(state.getPrev());
		} else {
			for(int i=0; i<4; i++){
				board.placeBot(state.getCur().substring(2*i,(2*i)+2), robots.get(i));
			}
			System.out.println("Starting State: ");
			System.out.println(board.toString());
		}
	}
	
	/**
	 * Method to add a state to the list of visited states.
	 * Done this way in order to maintain an ordered list of visited states,
	 * this allows for the binary searching of the list, which makes checking against it
	 * enormously faster as the visited number of states gets large.
	 * @param x the new string to be added to the visited list
	 */
	public void insertVisited(String x) {
	    int pos = Collections.binarySearch(visitedStates, x);
	    if (pos < 0) {
	        visitedStates.add(-pos-1, x);
	    }
	}
	
	/**
	 * Implements a breadth first search algorithm to generate and evaluate a state tree, where the robot
	 * of any robot represents a change in state.
	 */
	public void solve(){
		rearrangeBots();
		visitedStates.add(board.getState());
		if(checkWin(board.getState())){
			traceStates(new BoardState(board.getState(),null));
		} else {
			BoardState originalState = new BoardState(board.getState(),null);
			LinkedList<BoardState> moveQueue = new LinkedList<BoardState>();
			outermost:
			for(Robot bot : robots){
				Square square = board.getSquare(bot.getRow(), bot.getCol());
				TwoTuple origSquare = new TwoTuple(square.getRow(),square.getCol());
				for(int i=0; i<square.getModAdjacencies().size(); i++){
					bot.moveTo(square.getModAdjacencies().get(i));
					if(checkWin(board.getState())){
						traceStates(new BoardState(board.getState(),originalState));
						break outermost;
					}
					insertVisited(board.getState());
					moveQueue.offer(new BoardState(board.getState(),originalState));
					bot.moveTo(origSquare);
				}
			}
			//While more states can be explored
			//This outerloop label is a little lazy on my part (The OOP equivalent of a goto statement)
			//really I should have created a separate method, and still will if time allows
			outerloop:
			while(!moveQueue.isEmpty()){
				//Evaluate the next board state on the queue
				BoardState currentState = moveQueue.poll();
				String cur = currentState.getCur();
				//Take the robots off of the board
				board.removeBots();
				//put the robots on the board in the location indicated by the state string
				for(int i = 0; i<4; i++){
					board.placeBot(cur.substring(2*i, 2*i+2), robots.get(i));
				}
				//update the square adjacencies
				board.modifyAdjacencies();
				
				//for each of the robots
				for(Robot bot : robots){
					Square square = board.getSquare(bot.getRow(),bot.getCol());
					TwoTuple location = bot.getLocation();
					//for each square a robot is adjacent to
					for(TwoTuple adjacency : square.getModAdjacencies()){
						//move the robot to the square
						bot.moveTo(adjacency);
						//if the resulting board state is not in the list of visited states
						if(Collections.binarySearch(visitedStates,board.getState())<1){//!visitedStates.contains(board.getState())){
							//add it to the list of the visited states
							insertVisited(board.getState());
							//System.out.println(visitedStates.size());
							//check if it is a winning state
							if(checkWin(board.getState())){
								//System.out.println("The game is won");
								System.out.println("Total visited states: "+visitedStates.size());
								System.out.println("Target Square: "+target+"\n");
	
								System.out.println("Intermediate States: ");
								System.out.println();
								traceStates(new BoardState(board.getState(),currentState));
								System.out.println();
								break outerloop;
							//if it is not, add the board state to the queue
							} else {
								moveQueue.add(new BoardState(board.getState(),currentState));
							}
						}
						//move the robot back to the original square
						bot.moveTo(location);
					}
				}
			}
		}
	}
	
	/**
	 * The number of states visited during BFS algorithm
	 * @return the number of visited states
	 */
	public int getNumVisited(){
		return visitedStates.size();
	}
}
