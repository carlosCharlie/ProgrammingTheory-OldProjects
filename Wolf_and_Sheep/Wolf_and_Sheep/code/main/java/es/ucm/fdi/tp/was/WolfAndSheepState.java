package es.ucm.fdi.tp.was;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.ttt.TttAction;
import es.ucm.fdi.tp.ttt.TttState;

public class WolfAndSheepState extends GameState<WolfAndSheepState,WolfAndSheepAction>{


	private final int turn;
    private final boolean finished;
    private final int[][] board;
    private final int winner;
    public final static int dim = 8;//siz's board
    final static int EMPTY = -1;
    
    /**
     * Initial state builder
    */
	public WolfAndSheepState() {
		
		//2 players
		super(2);
		
		//The wolf begins
		this.turn = 0;
		this.winner = -1;
		this.finished = false;
		
		//Board creation
		this.board=new int[dim][dim];
		
		//Intial board = -1
		for(int i=0; i<dim; i++)
			for(int j=0; j<dim; j++)
			
				this.board[i][j]=EMPTY;
		
		
		//Sheeps in black positions
		for(int i=1; i<dim; i=i+2)
			
			this.board[0][i]=1;
		
		//Wolf in lower left corner
		this.board[dim-1][0] = 0;
	}
	

	/**
	 * State builder
	 * 
	 * @param antiguo //Old state
	 * @param NuevoTablero //Board for the new state
	 * @param finished //Indicates whether the game is finished
	 * @param winner //Indicates if the player is winner
	 * 
	 */
	public WolfAndSheepState(WolfAndSheepState antiguo, int[][] NuevoTablero, boolean finished, int winner){
		
		super(2);
		this.board = NuevoTablero;
		this.finished = finished;
		this.winner = winner;
		//change the turn (0 - 1)
		this.turn = (antiguo.turn+1)%2;
	}
	
	
	/*
	 * State builder
	 * @param antiguo //Old state
	 * @param NuevoTablero //Board for the new state
	 */
	public WolfAndSheepState(WolfAndSheepState antiguo, int[][] NuevoTablero){
			
			super(2);
			this.board=NuevoTablero;
			
			this.winner=-1;
			this.finished=false;
			this.turn=(antiguo.turn+1)%2;
	}
	
	
	/**
	 * Return the current turn.
	 */
	public int getTurn() {
		
		return turn;
		
	}//getTurn
	
	/**
	 * Ruturn if the game is over
	 */
	public boolean isFinished() {
		
		return this.finished;
	}//isFinished

	
	/**
	 * Return the current winner
	 */
	public int getWinner() {
		
		return this.winner;
	}//getWinner
	
	/**
	 * Copy and return the current board
	 * 
	 * @return copy of board.
	 */
	public int [][] getBoard(){
		
		 int[][] copy = new int[board.length][];
		 
	     for (int i=0; i<board.length; i++)
	    	 
	    	 copy[i] = board[i].clone();
	     
	     return copy;
	     
	}//getBoard
	

	/**
	 * Return a list with valid actions of player.
	 */
	public List<WolfAndSheepAction> validActions(int playerNumber) {
		
		//Create a new array for input valids actions
		ArrayList<WolfAndSheepAction> accionesValidas = new ArrayList<WolfAndSheepAction>();

		switch(playerNumber){
		
		//WOLF
		case 0:
			
			//Look for the wolf
			int co = 0, ro = 0;
			
			while(at(ro,co) != 0){
				
				co++;
				
				if(co == dim){
					
					ro++;
					co = 0;
				}//if
			}//while
			
			
			//Show his movements -->
			
			//down right
			if(co+1 < dim && ro+1 < dim && at(ro+1, co+1) == EMPTY)
				
				accionesValidas.add(new WolfAndSheepAction(playerNumber,ro,co,ro+1,co+1));
			
			//down left
			if(ro+1 < dim && co-1 >= 0 && at(ro+1, co-1) == EMPTY)
				
				accionesValidas.add(new WolfAndSheepAction(playerNumber,ro,co,ro+1,co-1));
			
			//above right
			if(ro-1 >= 0 && co+1 < dim && at(ro-1, co+1) == EMPTY)
				
				accionesValidas.add(new WolfAndSheepAction(playerNumber,ro,co,ro-1,co+1));
			
			//above left
			if(ro-1 >= 0 && co-1 >= 0 && at(ro-1, co-1) == EMPTY)
				
				accionesValidas.add(new WolfAndSheepAction(playerNumber,ro,co,ro-1,co-1));
			
			break;	
			
			
		//SHEEP
		case 1:
			
			int col = 0,row = 0;
			int finaly = 0;
			
			//to 4 sheeps
			for(int k = 0; k < dim/2; k++){
			
				
				//look for 1 sheep until I find it or have not reached the end of the board.
				//dim * dim --> board's final
				
				while((at(row,col) != 1) && finaly != (dim * dim) - 1){
					
					col++;
					finaly++;
					
					if(col == dim){
						
						row++;
						col = 0;
						
					}//if
					
				}//while
				
				////Show his movements
				//if have not reached the end of the board -->
				
				if (finaly != (dim * dim) - 1){
				
					//down right
					if(row+1 < dim && col+1 < dim && at(row+1, col+1) == EMPTY)
						
						accionesValidas.add(new WolfAndSheepAction(playerNumber, row, col, row+1, col+1));
					
					//down left
					if(row+1 < dim && col-1 >= 0 && at(row+1, col-1) == EMPTY)
						
						accionesValidas.add(new WolfAndSheepAction(playerNumber, row, col, row+1, col-1));
		
					col++;
					finaly++;
					
					if(col==dim){
						row++;
						col=0;
					}//if
				
					
				}//if
				
				
				else k = dim/2;
				
			}//for (to 4 sheeps)
			
			break;
			
		}//switch
		
		return accionesValidas;
		
	}//validActions
	
	/**
	 * Return the the value of parameters.
	 * 
	 * @param row
	 * @param col
	 * @return player of this position
	 */
	 public int at(int row, int col){
		 
	 return board[row][col];
	    
	 }//at

	 
	 /**
	  * Checking if the player is winner.
	  * 
	  * @param state current state
	  * @param player current player
	  * @return if the player is winner.
	  */
	public static boolean isWinner(WolfAndSheepState state, int player){
		
		boolean iswinner = false;
		
		switch(player){
		
			case 0:
				
				iswinner = false;
				int i=0;
				
				//The wolf is winner when is in the first row or the sheeps don't have movements.
				
				if(state.validActions(1).size() == 0)
					
					iswinner = true;
				
				else{
				
					//the wolf is in row 0
					while(i<dim && state.board[0][i]!=0)
						
						i++;
					
					
					if(i != dim) iswinner = true;
				}//else
				
				break;
				
			case 1:
				
				
				//The sheeps are winner when the wolf don't have movements
				
				iswinner = false;
				
				List<WolfAndSheepAction> PosiblesAcciones = state.validActions(0);
				
				if(PosiblesAcciones.size() == 0) iswinner=true;
				
				break;
		}
		
		return iswinner;
	}//isWinner.
	
	public String toString(){
		
		String s = "";
		
		//Print the board
		for (int i = 0; i < dim; i++){
			for (int j = 0; j < dim; j++){
				
				switch (at(i, j)){
				
				//empty square
				case EMPTY:
					
					s += "· ";
					
					break;
					
				//wolf square
				case 0:
					
					s += "W ";
					
					break;
					
				//sheep square
				case 1:
					
					s += "S ";
				
				}//SWITCH
				
			}//for j
			
			//print a new row
			s += "\n";
			
		}//for i

		return s;
		
	}
	
}//public class WolfAndSheepState
