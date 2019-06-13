package es.ucm.fdi.tp.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.base.player.RandomPlayer;
import es.ucm.fdi.tp.base.player.SmartPlayer;
import es.ucm.fdi.tp.mvc.GameEvent;
import es.ucm.fdi.tp.mvc.GameEvent.EventType;
import es.ucm.fdi.tp.mvc.GameObserver;
import es.ucm.fdi.tp.mvc.GameTable;


public class GameWindow <S extends GameState<S,A>, A extends GameAction<S,A>>  extends JFrame implements GameObserver<S,A>, GameControllerView<S,A> {


	private GUIController<S, A> control;
	private GameView<S,A> gameView;
	private int windowPlayer;
	private S estado;
	protected Color[]colores;
	
	//Diferentes modo de juego
	private RandomPlayer randPlayer;
	private SmartPlayer smartPlayer;
	//Modo de juego por defecto.
	private String mode = "Manual";
	
	//Area que muestra los mensajes del juego.
	private PlayerPanel<S,A> playerPanel;
	
	//para colorchooser
	int numPlayers;

	public GameWindow(String title,int playerId,RandomPlayer randPlayer,SmartPlayer smartPlayer,GameView<S, A> gameView,
			GUIController<S, A> controlador,GameTable<S, A> game,int numPlayers,int x,int y) {
		
		super(title + " Jugador " + playerId);
		
		this.windowPlayer=playerId;
		
		this.control = controlador;
		
		this.gameView=gameView;
		
		this.randPlayer = randPlayer;
		this.smartPlayer = smartPlayer;
		
		this.numPlayers=numPlayers;
		
		this.gameView.setColors(this.colores);
		
		this.colores=new Color[this.numPlayers];
		createInitialColors();
		this.gameView.setColors(colores);
		game.addObserver(this);
		this.setLocation(x, y);
		initGUI( randPlayer, smartPlayer);
		
	}//GUIView

//-----------------------------------------------------------------------------------------------------------------------------
	
	private void initGUI(RandomPlayer randPlayer, SmartPlayer smartPlayer) {
		
		//MAIN PANEL
		JPanel mainPanel = new JPanel(new BorderLayout());	
		mainPanel.setOpaque(true);
		
		//------------------------------------------------------------------------------------------------------------------------

			//Aloja los botones de accion del juego			
			Settings <S,A> settings= new Settings <S,A>(this);			
		mainPanel.add(settings, BorderLayout.NORTH);
			
	//--------------------------------------------------------------------------------------------------------------------------------------	
		
		
		//PANEL CENTRO. En el cual ira el tablero y la informacion del juego.
			JPanel pnlCentre = new JPanel(new BorderLayout());
						
				//PANEL --> TABLERO (CENTRO IZQUIERDA)
				
				JPanel pnlBoard = new JPanel(new BorderLayout());
				
					pnlBoard.setSize(getMaximumSize());
			
				//Anadimos el TABLERO
					
				pnlBoard.add(this.gameView);	
				
			pnlCentre.add(pnlBoard, BorderLayout.CENTER);
			
			
		//--------------------------------------------------------------------------------------------------------------------------------------	

				//PANEL CENTRO DERECHA
				
			playerPanel = new PlayerPanel<S,A>(this,this.gameView.getPreferredSize().height,this.numPlayers,colores);
				
				
			pnlCentre.add(playerPanel, BorderLayout.EAST);
			
			
		//--------------------------------------------------------------------------------------------------------------------------------------	

			
		//Anadimo al FRAME.
		mainPanel.add(pnlCentre, BorderLayout.CENTER);
		
		
	//--------------------------------------------------------------------------------------------------------------------------------------	

		
	this.setContentPane(mainPanel);

	this.setSize(750,600);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
	}
	
	@Override
	public void notifyEvent(final GameEvent<S, A> e) {
		
		SwingUtilities.invokeLater(new Runnable() {
			

			@Override
			public void run() {
			handleEvent(e);
			}
			
		});
		
	}//notifyEvent
	
	private void handleEvent(GameEvent<S, A> e){
		
		this.estado = e.getState();
		boolean stop = false;
		
		switch(e.getType()){
		
			case Start:
				
				this.gameView.update(e.getState());
				this.playerPanel.setText("");
	
				break;
				
			case Change:
				
				this.gameView.update(e.getState());
				
				break;
				
				
			case Stop:
				
				stop = true;
				this.gameView.update(e.getState());
				
				break;
			
			case Error:		
				break;
				
		}//switch
		

			this.playerPanel.setText(this.playerPanel.getText()+ e.toString() + "\n");
	
		
		repaint();
		
		requestNextMove(stop);
	}

	

	private void requestNextMove(boolean stop) {
		
		if(this.windowPlayer == this.estado.getTurn() && !stop && !this.estado.isFinished()) //la ultima comprobacion  no hace falta pero la pongo por si acaso
			
			switch(this.mode){
			
			case "Random":
				
				control.makeMove(this.randPlayer.requestAction(this.estado));	
				break;
				
			case "Smart":
				
				control.makeMove(this.smartPlayer.requestAction(this.estado));
				break;
		}
		
	}

	@Override
	public void movimientoAleatorio() {
		
		if(this.windowPlayer==this.estado.getTurn() && !this.estado.isFinished())
			control.makeMove(randPlayer.requestAction(this.estado));
	
	}

	@Override
	public void movimientoIA() {
		
		if(this.windowPlayer==this.estado.getTurn()  && !this.estado.isFinished())
			control.makeMove(smartPlayer.requestAction(this.estado));
		
	}

	@Override
	public void startGame() {	
		
		control.startGame();
		
	}

	@Override
	public void setMode(String s) {
		
		
		this.mode = s;
		this.notifyEvent(new GameEvent<S,A>(EventType.Change, null, estado, null, "The type of player has changed."));
	}

	@Override
	public void setColor(int row, Color color) {
		this.gameView.putColor(row, color);
		this.gameView.update(this.estado);
	}

	@Override
	public void stopGame() {
		
		this.control.stopGame();
		System.exit(1);
		
	}

	@Override
	public void setText(String txt) {
		
		this.playerPanel.setText(txt);
		
	}

	private void createInitialColors(){
		
		for(int i=0;i<colores.length;i++)
			
			colores[i]=new Color((50*i)+100, (10*i)+100, 95);
	}

}//GameWindow



