package es.ucm.fdi.tp.view;

import java.awt.Color;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;

public interface GameControllerView <S extends GameState<S,A>, A extends GameAction <S,A>> {

	
	public abstract void movimientoAleatorio();
	
	public abstract void movimientoIA();
	
	public abstract void startGame();
	
	public abstract void setMode(String s);
	
	public abstract void setColor(int row, Color color);

	public abstract void stopGame();
	
	public abstract void setText(String txt);
	
	public abstract void setMaxThreads(int n);

	public abstract void setMaxTime(int value);

	public abstract void stopSmart();
}
