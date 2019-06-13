package es.ucm.fdi.tp.launcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import es.ucm.fdi.tp.base.console.ConsolePlayer;
import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GamePlayer;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.base.player.RandomPlayer;
import es.ucm.fdi.tp.base.player.SmartPlayer;
import es.ucm.fdi.tp.ttt.TttState;
import es.ucm.fdi.tp.was.WolfAndSheepState;

public class Main {
	
	/**
	 * Main method that checking the entered parameters and begining the game.
	 * @param args input parameters
	 * 
	 */
	public static void main(String[] args) {
	
		if(argsSize(args) == 0)
			
			System.out.println("No arguments");
		
		else{
			
			GameState inicial = createInitialState(args[0]);
			
			if(inicial != null){
				
				if(argsSize(args) != 3)
					
					System.out.println("Error: this game mode does not have that number of players.");
				
				else{
			
					List<GamePlayer>ListaJugadores = new <GamePlayer>ArrayList();
					GamePlayer tmp = null;
					
					for(int i=0; i<2; i++){
						
						tmp = createPlayer(args[0], args[i+1], "player " + (i+1));
						 
						if(tmp!=null)
							
							ListaJugadores.add(tmp);
						
						else i=2;
						
					}//for
					
					if(tmp!=null)
						
						playGame(inicial,ListaJugadores);
					
				}//else
			}//if
		}//else
		
	}//main
	
	
	/**
	 * Return the size of arguments.
	 * 
	 * @param a
	 * @return size
	 */
	public static int argsSize (String a[]){
		
		return a.length;
		
	}//argsSize
	
	
	/**
	 * Method that controls the game and interacts with the actions and states.
	 * 
	 * @param initialState 
	 * @param players list of players
	 * @return Winning player number
	 * 
	 */
	public static <S extends GameState<S, A>, A extends GameAction<S, A>> int playGame(GameState<S, A> initialState,
		List<GamePlayer> players) {
		
			int playerCount = 0;
			
			for (GamePlayer p : players) {
				
				p.join(playerCount++); // welcome each player, and assign
										// playerNumber
			}//for
			
			@SuppressWarnings("unchecked")
			S currentState = (S) initialState;
			System.out.println(currentState);
			
			while (!currentState.isFinished()) {
				
	
				// request move
				A action = players.get(currentState.getTurn()).requestAction(currentState);
				
				// apply move
				currentState = action.applyTo(currentState);
				
				System.out.println("After action:\n" + currentState);
	
				if (currentState.isFinished()) {
					
					// game over
					String endText = "The game ended: ";
					
					int winner = currentState.getWinner();
					
					if (winner == -1) {
						
						endText += "draw!";
						
					}
					else {
						
						endText += "player " + (winner + 1) + " (" + players.get(winner).getName() + ") won!";
					}
					
					System.out.println(endText);
				}
			}
			return currentState.getWinner();
	}

	/**
	 * Create the initial state depending of enterd patameters
	 * @param gameName name´s game
	 * @return initial state
	 */
	public static GameState<?,?> createInitialState(String gameName){
		
		switch(gameName){
		
		case "was":
		
			return new WolfAndSheepState();
			
			
		case "ttt":
			
			return new TttState(3);
			
		default:
			
			System.out.println("Error: the game "+ gameName +" it´s not defined.");
			
			return null;
			
		}//switch
	}
	
	/**
	 * Return the players.
	 * @param gameName mame´s game.
	 * @param playerType kind of player.
	 * @param playerName name´s player.
	 * @return player.
	 */
	public static GamePlayer createPlayer(String gameName,String playerType, String playerName){
		
		switch(playerType){
		
		case "console":
			
			Scanner sc = new Scanner(System.in);
			
			return new ConsolePlayer(playerName,sc);
			
			 
		case "rand":
			
			return new RandomPlayer(playerName);
			
		
		case "smart":
			
			return new SmartPlayer(playerName,3);
			
		
		default:
			
			System.out.println("Error: the player " + playerType + " it´s not difined.");
			
			return null;
			
		}//switch
		
	}//createPlayer
	
	
}
