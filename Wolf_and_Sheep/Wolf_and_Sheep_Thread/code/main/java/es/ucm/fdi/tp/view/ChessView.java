package es.ucm.fdi.tp.view;

import java.awt.Color;
import java.util.List;

import javax.swing.ImageIcon;

import es.ucm.fdi.tp.base.Utils;
import es.ucm.fdi.tp.chess.ChessAction;
import es.ucm.fdi.tp.chess.ChessBoard;
import es.ucm.fdi.tp.chess.ChessState;
import es.ucm.fdi.tp.chess.ChessBoard.Piece;
import es.ucm.fdi.tp.view.RectBoardGameView.Shape;
import es.ucm.fdi.tp.was.WolfAndSheepAction;

public class ChessView extends RectBoardGameView <ChessState, ChessAction> {
	
	private boolean isFirst;
	
	int rowOld, colOld;
	
	private static ImageIcon chessIcon[] = loadChessIcon();
	

	public ChessView(ChessState state, int windowPlayer) {
		
		super(state, windowPlayer);
		
		this.isFirst = true;
		
	}

	private static ImageIcon[] loadChessIcon() {

		ImageIcon[] icons = new ImageIcon[Piece.Empty.white() + 1];
		
		for (Piece p : Piece.values()){
			
			if(p != Piece.Empty && p != Piece.Outside){
				
				byte code = p.white();
				icons[code] = new ImageIcon(Utils.loadImage("chess/" + Piece.iconName(code)));
				
				//icons[code] = new ImageIcon("src/main/resources/chess/p_b.png");

				
				code = p.black();
				icons[code] = new ImageIcon(Utils.loadImage("chess/" + Piece.iconName(code)));
				//icons[code] = new ImageIcon(Utils.loadImage("src/main/resources/chess/" + Piece.iconName(code) + ".png"));
				
			}//if
		}//for
		
		return icons;
		
	}//loadChessIcon

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
			
			ChessAction nuevaAccion = new ChessAction(this.windowPlayer, this.rowOld, this.colOld, row, col);
			
			if(this.isValid(nuevaAccion))
				this.gameCtrl.makeMove(nuevaAccion);
			
			else{
				
				nuevaAccion = getSpecialAction(nuevaAccion);
				
				if (nuevaAccion != null) 
					this.gameCtrl.makeMove(nuevaAccion);
				
				else{
					
					//mensaje de error que el movimiento no es posible.
				}//else
					
			}//else
		}//else
		
		this.isFirst =! this.isFirst;
	
	}///mouseClicked

	private ChessAction getSpecialAction(ChessAction nuevaAccion) {

		List <ChessAction> actions = this.state.validActions(this.state.getTurn());
		
		for (ChessAction a : actions){
			
			if (a.getSrcRow() == nuevaAccion.getSrcRow() &&
				
				a.getSrcCol() == nuevaAccion.getSrcCol() &&
				
				a.getDstRow() == nuevaAccion.getDstRow() &&
				
				a.getDstCol() == nuevaAccion.getDstCol()
			)//if

				return a;
		}//for
		
		return null;
	}

	private boolean isValid(ChessAction action) {

	/*	if(this.windowPlayer!=this.state.getTurn())
			
			return false;
		
		else{
			
			List<ChessAction> acciones = state.validActions(this.state.getTurn());
			
			int i=0;
			
			while(i<acciones.size() &&
					
				//compruebo la posicion antigua
				!(action.getSrcRow() == acciones.get(i).getSrcRow() && action.getDstCol() == acciones.get(i).getDstCol())
				
				
			)
				
				i++;
		}
		*/

		return false;
	}

	
	@Override
	protected Shape getShape(int player) {

		return Shape.CIRCLE;
		
	}

	
	@Override
	protected Color getColor(int pieza) {

		if(pieza==16)
			return null;
		else
		if(pieza<=6)
			return this.colores[0];
		
		else 
		return this.colores[1];
		
	}

	
	@Override
	protected Integer getPosition(int row, int col) {

		return this.state.at(row, col);
		
		//return this.state != null && ChessBoard.empty((byte) this.state.at(row, col)) ? this.state.at(row, col): null;
	}

	
	@Override
	protected Color getBackground(int row, int col) {

		if (row % 2 == 0 && col % 2 == 0) return Color.LIGHT_GRAY;
		
		else if (row % 2 != 0 && col % 2 == 0) return Color.BLACK;
		
		else if(row%2==0 && col%2!=0) return Color.BLACK;
		
		else return Color.LIGHT_GRAY;
		
	}//getBackground
	

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
	public void update(ChessState state) {

		this.state=state;
		this.repaint();
		
	}

	
	@Override
	public void setController(GUIController<?, ?> controlador) {

		this.gameCtrl=controlador;
		
	}

	@Override
	protected ImageIcon getIcon(Integer p) {

		return chessIcon[p];
		
	}
		
		

}//ChessView


