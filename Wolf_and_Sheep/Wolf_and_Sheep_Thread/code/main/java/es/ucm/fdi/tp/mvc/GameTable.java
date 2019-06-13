package es.ucm.fdi.tp.mvc;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameError;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.mvc.GameEvent;
import es.ucm.fdi.tp.mvc.GameEvent.EventType;

/**
 * An event-driven game engine.
 * Keeps a list of players and a state, and notifies observers
 * of any changes to the game.
 */
public class GameTable<S extends GameState<S, A>, A extends GameAction<S, A>> implements GameObservable<S, A> {
	

    private S initialState;
    private S actualState;
    private List<GameObserver<S,A>> observers=new ArrayList<GameObserver<S,A>>();
    private boolean finished;
    
    

    /**
     * GamwTable builder
     * @param inicial
     */
    public GameTable(S inicial) {
    	
        this.initialState = inicial;
        this.finished = false;
        this.actualState = null;
    }//Builder
    
    
    /**
     * Launch a new play
     */
    public void start() {
    
    	GameEvent<S,A> e;
    	
    	this.actualState = this.initialState;
    	
		e = new GameEvent<S,A>(GameEvent.EventType.Start, null, this.actualState, null, "The game has begun.");

		this.finished=false;
		
		this.notifyObservers(e);
		
    }//start
    
    
    //**********************************************************************************************************************************************************
    
    
    /**
     * Stop the game
     */
    public void stop() {
        
    	GameEvent<S,A> e;
    	
    
    		
    		this.finished = true;
    		e = new GameEvent<S,A>(GameEvent.EventType.Stop,null,this.actualState,null,"The game has stop.");
    	
 	
    	if(this.actualState.isFinished())
    		
    		if(this.actualState.getWinner()==-1)
        		e = new GameEvent<S,A>(GameEvent.EventType.Stop, null, this.actualState,null,"\n Game over.\n\n Nobody wins.");
    	
    		else
    			e = new GameEvent<S,A>(GameEvent.EventType.Stop, null, this.actualState,null,"\n Game over.\n\n Player " + this.actualState.getWinner() + " wins.");
    	
    	this.notifyObservers(e);
    	
    }//stop
    
    
    //**********************************************************************************************************************************************************

    
    public void execute(A action) {
    	
    	S tmp;
    	
    	tmp = action.applyTo(this.actualState);
    	
    	GameEvent<S,A> e;
    	
	
	    //si el juego esta parado || no iniciado || error de ejecucion.
    	
	    if(this.actualState == null || tmp == null)
	    	
	     	e = new GameEvent<S,A>(GameEvent.EventType.Error, action, this.actualState, new GameError("Move can not be made", 
	     			new Exception("Move can not be made")),"Move can not be made"); 
	    
	    
	    else {
	    		
	    	this.finished = tmp.isFinished();
	    	
	    	this.actualState = tmp;
	    	
	    	EventType evento=GameEvent.EventType.Change;
	    	
	    	if(this.finished)
	    		evento=GameEvent.EventType.Stop;
	    	
		    	e = new GameEvent<S,A>(evento,action,this.actualState,null,"A move has been made.");
		    
	    
    	}//else
	    
	    
	    this.notifyObservers(e);
    }//execute
    
    //**********************************************************************************************************************************************************
 
    public S getState() {
    	
        return this.actualState;
    }//getState

    
    //**********************************************************************************************************************************************************

    
    public void addObserver(GameObserver<S, A> o) {
    	
        this.observers.add(o);
    }//addObserver
    
    
    //**********************************************************************************************************************************************************

    
    public void removeObserver(GameObserver<S, A> o) {
    	
        this.observers.remove(o);
    }//removeObserver
    
    
    //**********************************************************************************************************************************************************

    
    private void notifyObservers(GameEvent<S,A> e){
    	
    	for(int i=0;i<observers.size();i++)
    		
    		this.observers.get(i).notifyEvent(e);
    }//notifyObservers


	public boolean isFinished() {
		return this.finished;
	}
    
    //**********************************************************************************************************************************************************

}//GameTable


