package es.ucm.fdi.tp.was;

import java.util.List;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;

public class WolfAndSheepAction implements GameAction<WolfAndSheepState,WolfAndSheepAction>{

	private int player;
	private int row;
	private int col;
	private int row_old;
	private int col_old;
	private final int EMPTY=-1;
	
	/**
	 * Builder action
	 * 
	 * @param player
	 * @param row_old
	 * @param col_old
	 * @param row
	 * @param col
	 * 
	 */
	public WolfAndSheepAction(int player,int row_old,int col_old,int row,int col){
		
		this.player = player;
		this.row_old = row_old;
		this.col_old = col_old;
		this.row = row;
		this.col = col;
		
	}//constructor
	
	/**
	 * Return the current player.
	 *
	 * @return player 
	 *
	 */
	public int getPlayerNumber() {
		
		return this.player;
		
	}//getPlayerNumber
	
	
	/**
	 * Return current row
	 * 
	 * @return row
	 * 
	 */
	public int getRow() {
		
		return row;
		
	}//getRow

	
	/**
	 * Return the current column
	 * 
	 * @return col
	 * 
	 */
	public int getCol() {
		
		return col;
		
	}//getCol


	/**
	 * Create a new state from the new position of player.
	 * 
	 * @param state old state
	 * 
	 * @return devuelve updated state
	 */
	public WolfAndSheepState applyTo(WolfAndSheepState state) {
		
		//Checking the turn of player
		if(this.player!=state.getTurn())
			
			throw new IllegalArgumentException("Not the turn of this player");
		
		else{
			
			//create a new board
			int[][] newBoard = state.getBoard();
			
			//input the player in his correct position
			newBoard[this.row][this.col] = player;
			
			//remove the old position
			newBoard[this.row_old][this.col_old] = EMPTY;
			
			
			//create a new state with the current positions
			WolfAndSheepState newState = new WolfAndSheepState (state, newBoard, state.isFinished(), state.getWinner());
			
			//Checking if the player is winner.
			if (WolfAndSheepState.isWinner(newState, player)){
				
				//if have a winner --> create a new state for this
				newState = new WolfAndSheepState(newState, newBoard, true, player);
			}
			
			return newState;
		}//else
			
	}//applyTo
	
	
	public String toString (){
		
		
		return  "(" + this.row_old + ", " + col_old + ") to (" + this.row + ", " + col + ")\n";
			
		
	}
	

}
