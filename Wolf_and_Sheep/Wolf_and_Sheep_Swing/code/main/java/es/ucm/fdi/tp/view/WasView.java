package es.ucm.fdi.tp.view;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.List;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.was.WolfAndSheepAction;
import es.ucm.fdi.tp.was.WolfAndSheepState;

public class WasView extends RectBoardGameView<WolfAndSheepState,WolfAndSheepAction>{

	private int rowOld=-1;
	private int colOld=-1;

	private boolean isFirst;
	private int windowPlayer;
	
	public WasView(WolfAndSheepState state,int windowPlayer) {
	
		super(state,windowPlayer);
		this.isFirst=true;
		this.windowPlayer=windowPlayer;
	}

	@Override
	protected void keyTyped(int keyCode) {
		
		if (keyCode == 0)
			this.isFirst=true;
		
	}

	@Override
	protected void mouseClicked(int row, int col, int clickCount, int mouseButton) {

		if(this.isFirst){
			
			this.rowOld = row;
			this.colOld = col;
		}
		
		else{
			
			WolfAndSheepAction nuevaAccion =new WolfAndSheepAction(this.windowPlayer, this.rowOld, this.colOld, row, col);
			
			if(this.isValid(nuevaAccion))
				this.gameCtrl.makeMove(nuevaAccion);
			
			this.colOld=-1;
			this.rowOld=-1;
		}
		
		this.isFirst=!this.isFirst;
		
	}//mousedClicked

	@Override
	protected Shape getShape(int player) {
		
		return Shape.CIRCLE;
	}

	@Override
	protected Color getColor(int player) {
		
		return this.colores[player];
	}

	@Override
	protected Integer getPosition(int row, int col) {
		
		return this.state.at(row,col);

	}

	@Override
	protected Color getBackground(int row, int col) {

		boolean encontrado=false;
		List<WolfAndSheepAction> acciones = this.state.validActions(this.windowPlayer);
		int i=0;
		
		while(i<acciones.size() && !encontrado){
			
			if(row==acciones.get(i).getRow() && col==acciones.get(i).getCol())
				encontrado=true;
			
			i++;
		}
	
		if(encontrado)
			return Color.YELLOW;
	
		else{
			if (row % 2 == 0 && col % 2 == 0) return Color.LIGHT_GRAY;
			
			else if (row % 2 != 0 && col % 2 == 0) return Color.BLACK;
			
			else if(row%2==0 && col%2!=0) return Color.BLACK;
			
			else return Color.LIGHT_GRAY;
		}
	
	}

	@Override
	protected int getNumRows() {
		
		return this.state.getDimension();
	}

	@Override
	protected int getNumCols() {
		
		return this.state.getDimension();
	}

	@Override
	public void enable() {
		
		
	}

	@Override
	public void disable() {
		
		
		
	}

	@Override
	public void update(WolfAndSheepState state) {

		this.state=state;
		this.repaint();
		
	}

	@Override
	public void setController(GUIController<?, ?> controlador) {
		this.gameCtrl=controlador;
		
	}
	

	private boolean isValid(WolfAndSheepAction action) {
		
		if(this.windowPlayer!=this.state.getTurn())
			return false;
		else{
			List<WolfAndSheepAction> acciones=state.validActions(this.state.getTurn());
			
			int i=0;
			
			while(i<acciones.size() &&
			
					//compruebo la posicion antigua
					!(action.getRowOld()==acciones.get(i).getRowOld() && action.getColOld()==acciones.get(i).getColOld()			
			
					//compruebo la posicion nueva
					&& action.getRow()==acciones.get(i).getRow() && action.getCol()==acciones.get(i).getCol()))
				
				i++;
				
				return (i<acciones.size());
		}
	}

}
