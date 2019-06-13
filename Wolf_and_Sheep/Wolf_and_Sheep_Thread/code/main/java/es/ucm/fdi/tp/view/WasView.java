package es.ucm.fdi.tp.view;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.ImageIcon;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.was.WolfAndSheepAction;
import es.ucm.fdi.tp.was.WolfAndSheepState;

public class WasView extends RectBoardGameView<WolfAndSheepState,WolfAndSheepAction>{

	private int rowOld;
	private int colOld;

	private boolean isFirst;
	public WasView(WolfAndSheepState state,int windowPlayer) {
	
		super(state,windowPlayer);
		this.isFirst=true;
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
		// TODO Auto-generated method stub
	return this.state.at(row,col);

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
		// TODO Auto-generated method stub
		
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

	@Override
	protected ImageIcon getIcon(Integer p) {

		return null;
	}

}
