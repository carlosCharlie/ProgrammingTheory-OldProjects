package es.ucm.fdi.tp.was;

import static org.junit.Assert.*;

import org.junit.Test;

public class WolfAndSheepStateTest {

	//APARTADO 1
	@Test
	public void ovejasGanadoras(){
		
		WolfAndSheepState state = new WolfAndSheepState();
		int board [][] = state.getBoard();
		
		//colocamos una oveja donde el lobo no se pueda mover.
		//con el estado inicial lo mas sencillo es colocar un oveja en el unico movimineto posible que tiene el lobo.
		
		board[state.getBoard().length -2][1] = 1;
		board[0][1] = -1;
		
		//creamos un nuevo estado en el que se den las nuevas condiciones.
		WolfAndSheepState newState = new WolfAndSheepState(state, board);
		
		assertEquals("The sheeps have to be the winners.", true, newState.isWinner(newState, 1));
		
	}//ovejasGanadoras
	
	//APARTADO 2
	@Test
	public void loboGanador(){
		
		WolfAndSheepState state = new WolfAndSheepState();
		
		int board [][];
		
		for (int i = 0; i < state.getBoard().length; i += 2){
			
			board = state.getBoard();
			
			//colocamos un lobo en cada columna de la primera fila.
			board[0][i] = 0;
			
			//actualizamos el estado;
			
			WolfAndSheepState newState = new WolfAndSheepState(state, board);
			
			assertEquals("The wolf has to be the winner.", true, newState.isWinner(newState, 0));
		}
		
	}//loboGanador
	
	
	
	//APARTADO 3
	@Test
	public void loboInicial(){
		
		WolfAndSheepState state = new WolfAndSheepState();
		int board [][] = state.getBoard();
		
		//posicion inicial del lobo.
		assertEquals ("The wolf only has 1 movement.", 1, state.validActions(0).size());
		
		//colocamos al lobo en la siguiente posicion disponible.
		board[state.getBoard().length - 1][0] = -1;
		board[state.getBoard().length - 2][1] = 0;
		
		//nuevo estado con la posicion del lobo (la cual tiene 4 movimientos)
		WolfAndSheepState newState = new WolfAndSheepState(state, board);
		
		assertEquals ("The wolf has 4 movements.", 4, newState.validActions(0).size());
		
	}//loboInicial
	
	
	
	//APARTADO 4
	@Test
	public void ovejaIncial(){
		
		WolfAndSheepState state = new WolfAndSheepState();
		int board [][] = state.getBoard();
		
		board[0][3] = -1;
		board[0][5] = -1;
		board[0][7] = -1;
		
		WolfAndSheepState newState = new WolfAndSheepState(state, board);
		
		//dividimos entre 4 puesto que validActiosn devuelve las opciones validas de las 4 ovejas.
		assertEquals ("The sheep only has 1 movement.", 2, newState.validActions(1).size());
		
		//cambiamos la oveja a un lateral.
		
		board[0][0] = 1;
		board[0][1] = -1;
		
		newState = new WolfAndSheepState(state, board);
		
		assertEquals ("The sheep has 2 movements.", 1, newState.validActions(1).size());
		
	}//ovejaInicial

}//WolfAndSheepStateTest
