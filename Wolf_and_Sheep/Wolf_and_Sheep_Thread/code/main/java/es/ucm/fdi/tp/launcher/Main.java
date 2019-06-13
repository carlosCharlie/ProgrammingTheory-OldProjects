package es.ucm.fdi.tp.launcher;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.base.console.ConsolePlayer;
import es.ucm.fdi.tp.base.model.GamePlayer;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.base.player.ConcurrentAiPlayer;
import es.ucm.fdi.tp.base.player.RandomPlayer;
import es.ucm.fdi.tp.base.player.SmartPlayer;
import es.ucm.fdi.tp.chess.ChessAction;
import es.ucm.fdi.tp.chess.ChessState;
import es.ucm.fdi.tp.mvc.GameTable;
import es.ucm.fdi.tp.ttt.TttAction;
import es.ucm.fdi.tp.ttt.TttState;
import es.ucm.fdi.tp.view.ChessView;
import es.ucm.fdi.tp.view.ConsoleController;
import es.ucm.fdi.tp.view.ConsoleView;
import es.ucm.fdi.tp.view.GUIController;
import es.ucm.fdi.tp.view.GameView;
import es.ucm.fdi.tp.view.GameWindow;
import es.ucm.fdi.tp.view.TttView;
import es.ucm.fdi.tp.view.WasView;
import es.ucm.fdi.tp.was.WolfAndSheepAction;
import es.ucm.fdi.tp.was.WolfAndSheepState;

public class Main {
	
	/**
	 * Crea el estado inicial dependiendo del juego introducido
	 * @param gameName abreviatura del juego
	 * @return estado inicial
	 */
	private static GameState<?,?>createInitialState(String gameName){
		

		switch(gameName){
		
		case "was":
			
			 return  new WolfAndSheepState();
			
			
		case "ttt":
			
			return  new TttState(3);
			
		case "chess":
			
			return new ChessState();
			
		default:
			
			return null;
			
		}//switch
	}
	
	
	/**
	 * Devuelve los jugadores que participaran en el juego
	 * @param gameName abreviatura del juego
	 * @param playerType tipo de jugador(random,smart,console..)
	 * @param playerName nombre del jugador
	 * @return jugador
	 */
	public static GamePlayer createPlayer(String playerType, String playerName){
	
		
		switch(playerType){
		
		case "manual":
			
			Scanner sc=new Scanner(System.in);
			return new ConsolePlayer(playerName,sc);
		
		case "rand":
			
			return new RandomPlayer(playerName);
		
		
		case "smart":
			
			return new SmartPlayer(playerName,3);
		
		default:
			
			System.err.println("Error: the player " + playerType + " it's not defined.");
			
			return null;
			
		}//switch
		
	}//createPlayer
	
	
	
	/**
	 * Metodo principal que comprueba los parametros introducidos y comienza el juego
	 * @param args parametros de entrada
	 * 
	 */
	public static void main(String[] args) {
	
		//composicion de la enrada:
		//	arg[0] --> tipo de juego (ttt / was)
		//	arg[1] --> modo de vista en la que queremos jugar (gui / console)
		//  arg[2] & arg [3] --> tipo de jugaodres (manual / rand / smart)
		
		if(argsSize(args) == 0){
			
			System.err.println("Error: No arguments\n");
			usage();
			System.exit(1);
		}//if
		
		else if (argsSize(args) < 2){
			
			System.err.println("Error: Insufficient arguments\n");
			usage();
			System.exit(1);
			
		}//else if
		
		else{
			
			if(args[1].equals("console") && args.length<4){
				
				System.err.println("Error: Insufficient arguments\n");
				usage();
				System.exit(1);	
			}
			
			//el argumento 1 --> kind of game
			GameState<?,?> inicial = createInitialState(args[0]);
			
			if (inicial == null) {
				
				System.err.println("Error: Invalid game\n");
				usage();
				System.exit(1);
				
			}//if
			
			else{
				//create a list os players.
					List<GamePlayer>ListaJugadores = new ArrayList<GamePlayer>();

					GamePlayer tmp = null;
					
					String tipo;
					
					for(int i = 2; i < inicial.getPlayerCount()+2; i++){
						
						if(args[1].equals("gui"))
							tipo="manual";
						else
							tipo=args[i];
						
						tmp = createPlayer(tipo, "jugador" + (i-1));
						 
						if(tmp!=null)
							
							ListaJugadores.add(tmp);
						
						else i = 2;
						
					}//for
						
					if(tmp!=null){
						
						GameTable<?,?> game=null;
						
						switch(args[0]){
					
							case "ttt":
								
								game = new GameTable<TttState,TttAction>((TttState) inicial);
								
								break;
								
								
							case "was":
								
								game = new GameTable<WolfAndSheepState,WolfAndSheepAction>((WolfAndSheepState) inicial);
								
								break;
								
							case "chess":
								
								game = new GameTable<ChessState, ChessAction>((ChessState) inicial);
								
								break;
								
						}//switch
								
						switch(args[1]){
							
							case "console":
							
								startConsoleMode(game,ListaJugadores);
								
								break;
									
								
							case "gui":
									
								
								startGUIMode(args[0], game, ListaJugadores,inicial);
								
								break;
								
								
							default: 
								
								System.err.println("Invalid view mode:" + args[1]);
								usage();
								
								break;
									
						}//switch
					}//if
			}//else
		}//else
	}//main
	
	
	private static void startGUIMode(String args, final GameTable<?, ?> game, final List<GamePlayer> ListaJugadores,GameState<?,?> state) {
		
		final GUIController<?,?> controlador = new GUIController<>(ListaJugadores, game);
		
		
		final List<GameWindow<?,?>>ventanas = new ArrayList<GameWindow<?,?>>();
		
		GameView<?,?> gameview = null;
		String tituloVentana = null;
		
		//una ventana para cada jugador
		for( int i=0;i<ListaJugadores.size();i++){
			
			switch(args){
			
			case "was":
				
				tituloVentana="Wolf And Sheep";
				
				gameview=new WasView((WolfAndSheepState) state,i);
				
				break;
				
				
			case "ttt":
				
				tituloVentana="TicTacToe";
				
				gameview=new TttView((TttState) state,i);
				
				break;
				
			case "chess":
				
				tituloVentana = "Chess";
				
				gameview = new ChessView((ChessState) state, i);
				
				
			}//switch
			
			final String tituloFinal=tituloVentana;
			gameview.setController(controlador);
			
			final GameView<?,?> gameviewFinal = gameview;
			
			final int indice = i;
			
			final RandomPlayer r = new RandomPlayer("aleatorio");		
			final ConcurrentAiPlayer s = new ConcurrentAiPlayer("inteligente");
			
			r.join(i);
			s.join(i);
			
				try {
					
					SwingUtilities.invokeAndWait(new Runnable(){
						
							public void run() {
								
								ventanas.add(new GameWindow(tituloFinal, indice, r, s, gameviewFinal, controlador, game, ListaJugadores.size(), indice*750,70));
								
							};
					}
					
					);
					
				} 
				
				catch (InvocationTargetException | InterruptedException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}
		
		controlador.run();
		
	}//startGUIMode


	private static void startConsoleMode(GameTable<?, ?> game, List<GamePlayer> ListaJugadores) {
		
		ConsoleView<?,?> vistaCons = new ConsoleView<>(game);
		
		ConsoleController<?,?> controlador = new ConsoleController<>(ListaJugadores, game);
	
		controlador.run();
		
	}//startConsoleMode


	/**
	 * Help menu for the users.
	 */
	private static void usage() {
		
		System.out.println("Main game mode player_1 player_2 ...\n");

		System.out.println("   game: ttt (for Tic Tac Toe), was (for wolf and sheep)");

		System.out.println("   mode: gui or console");

		System.out.println("   player_i: manual (for manual player), random (for random player), smart (for smart player) ");
		
	}
	
	/**
	 * Devuelve el tamaño de los argumentos.
	 * 
	 * @param a
	 * @return tamaño
	 */
	public static int argsSize (String a[]){
		
		return a.length;
		
	}//argsSize
	
	
}


