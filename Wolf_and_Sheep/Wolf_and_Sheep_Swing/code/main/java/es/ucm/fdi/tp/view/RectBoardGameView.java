package es.ucm.fdi.tp.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;

public abstract class RectBoardGameView<S extends GameState<S,A>, A extends GameAction<S,A>> extends GameView<S,A> {

	private int _CELL_HEIGHT = 50;
	private int _CELL_WIDTH = 50;
	private int _SEPARATOR = -2;

	protected int windowPlayer;
	protected GUIController<?,?> gameCtrl;
	
	public enum Shape {
		CIRCLE, RECTANGLE
	}
	
	public RectBoardGameView(S state,int windowPlayer){
		
		this.state=state;
		this.windowPlayer=windowPlayer;
		initGUI();
		
	}
	
	private void initGUI() {
		setBorder(BorderFactory.createRaisedBevelBorder());

		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				RectBoardGameView.this.keyTyped(e.getExtendedKeyCode());
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				RectBoardGameView.this.requestFocus();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				int col = (e.getX() / _CELL_WIDTH);
				int row = (e.getY() / _CELL_HEIGHT);

				int mouseButton = 0;

				if (SwingUtilities.isLeftMouseButton(e))
					mouseButton = 1;
				else if (SwingUtilities.isMiddleMouseButton(e))
					mouseButton = 2;
				else if (SwingUtilities.isRightMouseButton(e))
					mouseButton = 3;

				if (mouseButton == 0)
					return; // Unknown button, don't know if it is possible!

				RectBoardGameView.this.mouseClicked(row, col, e.getClickCount(), mouseButton);
			}
		});
		
		_SEPARATOR = getSepPixels();
		if ( _SEPARATOR < 0 ) _SEPARATOR = 0;

		this.setPreferredSize(new Dimension(400, 400));
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		fillBoard(g);
	}

	private void fillBoard(Graphics g) {
		int numCols = getNumCols();
		int numRows = getNumRows();

		if (numCols <= 0 || numRows <= 0) {
			g.setColor(Color.red);
			g.drawString("Waiting for game to start!", 20, this.getHeight() / 2);
			return;
		}

		_CELL_WIDTH = this.getWidth() / numCols;
		_CELL_HEIGHT = this.getHeight() / numRows;

		for (int i = 0; i < numRows; i++)
			for (int j = 0; j < numCols; j++)
				drawCell(i, j, g);
	}

	private void drawCell(int row, int col, Graphics g) {
		int x = col * _CELL_WIDTH;
		int y = row * _CELL_HEIGHT;

		g.setColor( getBackground(row, col));
		g.fillRect(x + _SEPARATOR, y + _SEPARATOR, _CELL_WIDTH - 2*_SEPARATOR, _CELL_HEIGHT - 2*_SEPARATOR);

		Integer p = getPosition(row, col);

		if (p != -1) {
			Color c = getColor(p);
			Shape s = getShape(p);

			g.setColor(c);
			switch (s) {
			case CIRCLE:
				g.fillOval(x + _SEPARATOR+2, y + _SEPARATOR+2, _CELL_WIDTH - 2*_SEPARATOR-4, _CELL_HEIGHT - 2*_SEPARATOR-4);
				g.setColor(Color.black);
				g.drawOval(x + _SEPARATOR+2, y + _SEPARATOR+2, _CELL_WIDTH - 2*_SEPARATOR-4, _CELL_HEIGHT - 2*_SEPARATOR-4);
				break;
			case RECTANGLE:
				g.fillRect(x + _SEPARATOR+2, y + _SEPARATOR+2, _CELL_WIDTH - 2*_SEPARATOR-4, _CELL_HEIGHT - 2*_SEPARATOR-4);
				g.setColor(Color.black);
				g.drawRect(x + _SEPARATOR+2, y + _SEPARATOR+2, _CELL_WIDTH - 2*_SEPARATOR-4, _CELL_HEIGHT - 2*_SEPARATOR-4);
				break;
			default:
				break;

			}
		}

	}

	protected abstract void keyTyped(int keyCode);

	protected abstract void mouseClicked(int row, int col, int clickCount, int mouseButton);

	protected abstract Shape getShape(int player);

	protected abstract Color getColor(int player);

	protected abstract Integer getPosition(int row, int col);

	protected abstract Color getBackground(int row, int col);

	protected abstract int getNumRows();

	protected abstract int getNumCols();

	
	protected GameState<?,?> getState()
	{
		return this.state;
	}
	

	protected int getSepPixels() {
		return 2;
	}
	
	public void putColor(int player,Color color)
	{
		this.colores[player]=color;
	}
	
	public void setColors(Color[] colores){
		this.colores=colores;
	};
}
