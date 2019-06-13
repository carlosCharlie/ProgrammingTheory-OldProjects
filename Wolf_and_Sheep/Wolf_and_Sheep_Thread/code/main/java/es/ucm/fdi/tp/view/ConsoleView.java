package es.ucm.fdi.tp.view;


import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.mvc.GameEvent;
import es.ucm.fdi.tp.mvc.GameEvent.EventType;
import es.ucm.fdi.tp.mvc.GameObservable;
import es.ucm.fdi.tp.mvc.GameObserver;

public class ConsoleView<S extends GameState<S,A>, A extends GameAction<S,A>> implements GameObserver<S,A> {

	public ConsoleView(GameObservable<S,A> gameTable) {
		
		//se añade a si mismo a la lista de gameTable
		gameTable.addObserver(this);
	}
	@Override
	public void notifyEvent(GameEvent<S, A> e) {
		
		System.out.println(e+"\n");
		
		System.out.println(e.getState());
	}

}
