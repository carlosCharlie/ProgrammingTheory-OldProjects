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
	 * Constructor para la accion de WAS
	 * 
	 * @param player
	 * @param row_old
	 * @param col_old
	 * @param row
	 * @param col
	 * 
	 */
	public WolfAndSheepAction(int player,int row_old,int col_old,int row,int col){
		
		this.player=player;
		this.row_old=row_old;
		this.col_old=col_old;
		this.row=row;
		this.col=col;
		
	}//constructor
	
	/**
	 * Devuelve jugador actual.
	 * 
	 */
	public int getPlayerNumber() {
		
		return this.player;
		
	}//getPlayerNumber
	
	
	/**
	 * Devuelve fila actual
	 * 
	 * @return
	 * 
	 */
	public int getRow() {
		
		return row;
		
	}//getRow

	
	/**
	 * Devuelve la columan actual
	 * 
	 * @return
	 * 
	 */
	public int getCol() {
		
		return col;
		
	}//getCol


	/**
	 * Crea un neuvo estado a partir de la nueva posicion del jugador.
	 * 
	 * @param state el estado anterior al que se le va a aplicar la accion.
	 * 
	 * @return devuelve el estado actualizado.
	 */
	public WolfAndSheepState applyTo(WolfAndSheepState state) {
		
		//Comprobamos que el jugador está jugando en su turno
		 if (player != state.getTurn()) {
	            throw new IllegalArgumentException("Not the turn of this player");
	        }
				
			//creamos el nuevo tablero
			int[][] newBoard = state.getBoard();
			
			//colocamos all jugador en la posicion del tablero que le corresponde
			newBoard[this.row][this.col] = player;
			
			//borramos la posicion antigua
			newBoard[this.row_old][this.col_old] = EMPTY;
			
			
			//creamos el nuevo estado con las posicines actuales.
			WolfAndSheepState newState = new WolfAndSheepState (state, newBoard, state.isFinished(), state.getWinner());
			
			//Una vez coreado el nuevo estado, comprobamo si hay ganador.
			if (newState.isWinner( player)){
				
				//creamos un nuevo estado con jugador ganado.
				newState = new WolfAndSheepState(newState, newBoard, true, player);
			}
			
			return newState;
			
	}//applyTo
	

	public String toString (){
		
		
		return  "(" + this.row_old + ", " + col_old + ") to (" + this.row + ", " + col + ")\n";
			
		
	}

	public int getRowOld() {
		
		return this.row_old;
	}

	public int getColOld() {
		
		return this.col_old;
	}
	

}
