package es.ucm.fdi.tp.was;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.tp.base.model.GameState;

public class WolfAndSheepState extends GameState<WolfAndSheepState,WolfAndSheepAction>{


	private final int turn;
    private final boolean finished;
    private final int[][] board;
    private final int winner;
    public final static int dim = 8;//Tamaño del lado del tablero
    final static int EMPTY = -1;
    
    /**
     * Constructor para estado inicial
    */
	public WolfAndSheepState() {
		
		//indicar que son dos jugadores.
		super(2);
		
		//empieza el lobo
		this.turn=0;
		this.winner=-1;
		this.finished=false;
		
		//creacion del tablero
		this.board=new int[dim][dim];
		
		//se inicializa el tablero a 0
		for(int i=0; i<dim; i++)
			for(int j=0; j<dim; j++)
			
				this.board[i][j]=EMPTY;
		
		
		//Ovejas en las casillas negras(impares) del tablero
		for(int i=1; i<dim; i=i+2)
			
			this.board[0][i]=1;
		
		//Lobo en la primera casilla de la ultima fila
		this.board[dim-1][0] = 0;
	}
	

	/**
	 * Construcutor para el estado
	 * 
	 * @param antiguo //Antiguo estado
	 * @param NuevoTablero //Tablero donde distribuiremos el nuevo estado.
	 * @param finished //nos indica si se ha termiando la partida
	 * @param winner //nos indica que jugador ha ganado (lobo ó ovejas)
	 * 
	 */
	public WolfAndSheepState(WolfAndSheepState antiguo, int[][] NuevoTablero, boolean finished, int winner){
		
		super(2);
		this.board=NuevoTablero;
		this.finished=finished;
		this.winner=winner;
		//cambia el turno (0 - 1)
		this.turn=(antiguo.turn+1)%2;
	}
	
	
	/*
	 * Construtor de estado
	 */
	public WolfAndSheepState(WolfAndSheepState antiguo, int[][] NuevoTablero){
			
			super(2);
			this.board=NuevoTablero;
			
			this.winner=-1;
			this.finished=false;
			this.turn=(antiguo.turn+1)%2;
	}
	
	
	/**
	 * Devuelve el turno actual.
	 */
	public int getTurn() {
		
		return turn;
		
	}//getTurn
	
	/**
	 * Devuelve si ha finalizado el juego
	 */
	public boolean isFinished() {
		
		return this.finished;
	}//isFinished

	
	/**
	 * Devuelve quien es el ganador actual.
	 */
	public int getWinner() {
		
		return this.winner;
	}//getWinner
	
	/**
	 * Hace una copia del tablero actual y lo devuelve.
	 * 
	 * @return copia del tablero.
	 */
	public int [][] getBoard(){
		
		 int[][] copy = new int[board.length][];
		 
	     for (int i=0; i<board.length; i++)
	    	 
	    	 copy[i] = board[i].clone();
	     
	     return copy;
	     
	}//getBoard
	

	/**
	 * Devuelve la lista de acciones validas dependiendo del jugador
	 */
	public List<WolfAndSheepAction> validActions(int playerNumber) {
		
		//creamos un nuevo array donde introducir los moviemtos validaos.
		ArrayList<WolfAndSheepAction> accionesValidas = new ArrayList<WolfAndSheepAction>();

		switch(playerNumber){
		
		//Lobo
		case 0:
			
			//Busco al lobo
			int co = 0, ro = 0;
			
			while(at(ro,co) != 0){
				
				co++;
				
				if(co == dim){
					
					ro++;
					co = 0;
				}//if
			}//while
			
			
			//miro sus movimientos -->
			
			//abajo a la derecha
			if(co+1 < dim && ro+1 < dim && at(ro+1, co+1) == EMPTY)
				
				accionesValidas.add(new WolfAndSheepAction(playerNumber,ro,co,ro+1,co+1));
			
			//arriba a la izquierda
			if(ro+1 < dim && co-1 >= 0 && at(ro+1, co-1) == EMPTY)
				
				accionesValidas.add(new WolfAndSheepAction(playerNumber,ro,co,ro+1,co-1));
			
			//abajo a la derecha
			if(ro-1 >= 0 && co+1 < dim && at(ro-1, co+1) == EMPTY)
				
				accionesValidas.add(new WolfAndSheepAction(playerNumber,ro,co,ro-1,co+1));
			
			//arriba a la izquierda
			if(ro-1 >= 0 && co-1 >= 0 && at(ro-1, co-1) == EMPTY)
				
				accionesValidas.add(new WolfAndSheepAction(playerNumber,ro,co,ro-1,co-1));
			
			break;	
			
			
		//Oveja
		case 1:
			
			int col=0,row=0;
			int finaly = 0;
			//Para las 4 ovejas
			for(int k = 0; k < dim/2; k++){
			
				
				//busco 1 oveja hasta que la encuentro o no he llegado al final del tablero.
				//dim * dim indica el fianl del tablero.
				
				while((at(row,col) != 1) && finaly != (dim * dim) - 1){
					
					col++;
					finaly++;
					
					if(col == dim){
						
						row++;
						col = 0;
						
					}//if
					
				}//while
				
				//miro sus movimientos (las ovejas solo tienen dos posibles movimientos, hacia abajo)
				//sino he llegado al final del tablero -->
				
				if (finaly != (dim * dim) - 1){
				
					//abajo a la derecha
					if(row+1 < dim && col+1 < dim && at(row+1, col+1) == EMPTY)
						
						accionesValidas.add(new WolfAndSheepAction(playerNumber, row, col, row+1, col+1));
					
					//abajo a la izquierda
					if(row+1 < dim && col-1 >= 0 && at(row+1, col-1) == EMPTY)
						
						accionesValidas.add(new WolfAndSheepAction(playerNumber, row, col, row+1, col-1));
		
					col++;
					finaly++;
					
					if(col==dim){
						row++;
						col=0;
					}//if
				
					
				}//if para meterse en acionesValidas.
				
				//si el tablero llega a su fin, no puede seguir buscando ovejas
				//porque buscaria fuera del tablero --> finalizamo sle for.
				else k = dim/2;
				
			}//for (para las 4 ovejas)
			
			break;
			
		}//switch
		
		return accionesValidas;
		
	}//validActions
	
	/**
	 * Devuelve quien se encuentra en la posicion pasada por parametro.
	 * 
	 * @param row
	 * @param col
	 * @return jugador de la posicion.
	 */
	 public int at(int row, int col){
		 
	 return board[row][col];
	    
	 }//at

	 
	 /**
	  * Comprueba dependiendo de los movimientos de cada jugador, si ha ganado.
	  * 
	  * @param state estado actual
	  * @param player jugador que se quiere probar
	  * @return si ha ganado algun jugador.
	  */
	public boolean isWinner(int player){
		
		boolean iswinner = false;
		
		switch(player){
		
			case 0:
				
				iswinner = false;
				int i=0;
				
				//el lobo gana cuando esta en la primera fila o cuando las ovejas no tienen movimentos.
				
				if(this.validActions(1).size() == 0)
					
					iswinner = true;
				
				else{
				
					//el lobo esta en la fila 0, en cualquiera de las columnas.
					while(i<dim && this.board[0][i]!=0)
						
						i++;
					
					
					if(i != dim) iswinner = true;
				}//else
				
				break;
				
			case 1:
				
				
				//las ovejas ganan cuando el lobo no se puede mover
				
				iswinner = false;
				
				List<WolfAndSheepAction> PosiblesAcciones = this.validActions(0);
				
				if(PosiblesAcciones.size() == 0) iswinner=true;
				
				break;
		}
		
		return iswinner;
	}//isWinner.
	
	public String toString(){
		
		String s = "";
		
		//Imprime el tablero.
		for (int i = 0; i < dim; i++){
			for (int j = 0; j < dim; j++){
				
				//esta condicion muestra solo las casillas de movimiento de las fichas.
				
				if((i %2 == 0 && j %2 != 0) || (i %2 != 0 && j %2 == 0))
		
				
					switch (at(i, j)){
					
					//casilla vacia
					case EMPTY:
						
						s += "· ";
						
						break;
						
					//casilla lobo
					case 0:
						
						s += "W ";
						
						break;
						
					//casilla oveja	
					case 1:
						
						s += "S ";
					
					}//SWITCH
				else
					s +="  ";
				
			}//for j
			
			//imprimimos nueva linea una vez que se ha completado una fila entera.
			s += "\n";
			
		}//for i
		

		return s;
		
	}


	
	public int getDimension() {
		// TODO Auto-generated method stub
		return dim;
	}
	
}//public class WolfAndSheepState
