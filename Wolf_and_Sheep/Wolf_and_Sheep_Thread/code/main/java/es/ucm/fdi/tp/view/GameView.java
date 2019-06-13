package es.ucm.fdi.tp.view;

import java.awt.Color;

import javax.swing.JComponent;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;

public abstract class GameView<S extends GameState<S, A>,A extends GameAction<S, A>> extends JComponent {

	protected S state;
	protected Color[]colores;
	
	/*
	 * permite al usuario jugar
	 *
	 */
	public abstract void enable();
	
	/*
	 * impide jugar
	 * 
	 */
	public abstract void disable();
	
	/*
	 *actualiza la vista
	 */
	public abstract void update(S state);
	
	public abstract void setController(GUIController<?, ?>	controlador);
	
	public abstract void putColor(int player,Color color);
}
