package es.ucm.fdi.tp.view;

import java.awt.Color;
import java.util.List;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.ttt.TttAction;
import es.ucm.fdi.tp.ttt.TttState;

public class TttView extends RectBoardGameView<TttState,TttAction>{

	public TttView(TttState state,int windowPlayer) {
		super(state, windowPlayer);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void keyTyped(int keyCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void mouseClicked(int row, int col, int clickCount, int mouseButton) {
		
		TttAction nuevaAccion=new TttAction(this.windowPlayer, row, col);
		
		if(this.isValid(nuevaAccion))
			this.gameCtrl.makeMove(nuevaAccion);
	}

	@Override
	protected Shape getShape(int player) {
		// TODO Auto-generated method stub
		return Shape.CIRCLE;
	}

	@Override
	protected Color getColor(int player) {
		// TODO Auto-generated method stub
		return this.colores[player];
	}

	@Override
	protected Integer getPosition(int row, int col) {
		// TODO Auto-generated method stub
		return state.at(row, col);
	}

	@Override
	protected Color getBackground(int row, int col) {
		
		if (row % 2 == 0 && col % 2 == 0) return Color.LIGHT_GRAY;
		
		else if (row % 2 != 0 && col % 2 == 0) return Color.BLACK;
		
		else if(row%2==0 && col%2!=0) return Color.BLACK;
		
		else return Color.LIGHT_GRAY;
	}

	@Override
	protected int getNumRows() {
		// TODO Auto-generated method stub
		return state.getDimension();
	}

	@Override
	protected int getNumCols() {
		// TODO Auto-generated method stub
		return state.getDimension();
	}

	@Override
	public void enable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disable() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void update(TttState state) {
		this.state=state;
		repaint();
		
	}

	@Override
	public void setController(GUIController<?, ?> controlador) {
		this.gameCtrl=controlador;
		
	}

	private boolean isValid(TttAction action) {
		
		if(this.windowPlayer!=this.state.getTurn())
			return false;
		else{
		List<TttAction> acciones=state.validActions(this.state.getTurn());
		
		int i=0;
		
		while(i<acciones.size() && !(action.getRow()==acciones.get(i).getRow() && action.getCol()==acciones.get(i).getCol()))
			
			i++;
			
			return (i<acciones.size());
		}
	
	}

}
