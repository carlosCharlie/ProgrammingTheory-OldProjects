package es.ucm.fdi.tp.launcher;

import static org.junit.Assert.*;

import org.junit.Test;

public class MainTest {

	
	//APRATADO 1
	@Test
	
	public void argumentosInvalidos (){
		
		String[] argsMas = {"a", "b", "c", "d"};
		
		String[] argsMenos = {"e", "f"};
		
		Main mainP = new Main();
		
		//argumentos de mas.
		assertTrue("The number of arguments is not correct.", mainP.argsSize(argsMas) != 3);
		
		//argumentos de menos.
		assertTrue("The number of arguments is not correct.", mainP.argsSize(argsMenos) != 3);
		
	}//argumentosInvalidos
	
	
	//APARTADO 2
	
	@Test
	
	public void juegoInvalido(){
		
		//creacion de argumtos con jeugo invalido
		String[] args = {"wrong", "console", "smart"};
		
		Main mainP = new Main();
		
		assertNull("The kind of game is not correct.", mainP.createInitialState(args[0]));
		
		
	}//juegoInvalido
	

}//mainTest
