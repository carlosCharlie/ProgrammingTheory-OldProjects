package es.ucm.fdi.tp.view;

import java.util.List;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GamePlayer;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.mvc.GameTable;

public class ConsoleController<S extends GameState<S,A>, A extends GameAction<S,A>> implements Runnable{

	private GameTable<S,A> game;
	private List<GamePlayer>players;
	
	public ConsoleController(List<GamePlayer> players, GameTable<S,A> game)
	{
		this.game = game;
		this.players = players;
		
	}
	
	public void run() {
		
		int playerCount = 0;
		
		for (GamePlayer p : players) {
			p.join(playerCount++);
		}
		

		this.game.start();
		
		do{
		
		A action = this.players.get(this.game.getState().getTurn()).requestAction(this.game.getState());
		
		this.game.execute(action);
		
		}while(!this.game.isFinished());
		
		
		this.game.stop();
	}

}
