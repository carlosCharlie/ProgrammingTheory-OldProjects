package es.ucm.fdi.tp.view;

import java.util.List;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GamePlayer;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.mvc.GameTable;

public class GUIController<S extends GameState<S,A>, A extends GameAction<S,A>> implements Runnable{

	private GameTable<S,A> game;
	private List<GamePlayer>players;
	
	
	public GUIController(List<GamePlayer> players, GameTable<S,A> game2) {
		
		this.game = game2;
		this.players = players;
		
	}

	public void makeMove(GameAction<?,?> gameAction){
		
		if(!this.game.isFinished())
			this.game.execute((A)gameAction);
		
		if(this.game.isFinished())
			this.stopGame();
	}

	public void startGame(){
		
		game.start();
	}
	public void stopGame() 
	{
		game.stop();
		
	}
	@Override
	public void run() {
		
		this.game.start();
	}
	
	
}
