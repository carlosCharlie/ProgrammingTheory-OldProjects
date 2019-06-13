package es.ucm.fdi.tp.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.base.player.ConcurrentAiPlayer;
import es.ucm.fdi.tp.base.player.RandomPlayer;
import es.ucm.fdi.tp.mvc.GameEvent;
import es.ucm.fdi.tp.mvc.GameEvent.EventType;
import es.ucm.fdi.tp.mvc.GameObserver;
import es.ucm.fdi.tp.mvc.GameTable;


public class GameWindow <S extends GameState<S,A>, A extends GameAction<S,A>>  extends JFrame implements GameObserver<S,A>, GameControllerView<S,A> {


	private GUIController<S, A> control;
	private GameView<S,A> gameView;
	private int windowPlayer;
	private S estado;
	
	
	//Diferentes modo de juego
	private RandomPlayer randPlayer;
	private ConcurrentAiPlayer smartPlayer;
	GameAction<?,?> smartAction;
	private Thread smartThread=null;
	private int smartTime=500;
	//Modo de juego por defecto.
	private String mode = "Manual";
	
	//Area que muestra los mensajes del juego.
	private PlayerPanel<S,A> playerPanel;
	
	//ajustes
	private Settings<S,A> settings; 
	//para colorchooser
	int numPlayers;	
	
	

	public GameWindow(String title,int playerId,RandomPlayer randPlayer,ConcurrentAiPlayer s,GameView<S, A> gameView,
			GUIController<S, A> controlador,GameTable<S, A> game,int numPlayers,int x,int y) {
		
		super(title + " Jugador " + playerId);
		
		this.windowPlayer=playerId;
		
		this.control = controlador;
		
		this.gameView=gameView;
		
		this.randPlayer = randPlayer;
		this.smartPlayer = s;
		this.smartPlayer.setMaxThreads(1);
		this.smartPlayer.setTimeout(500);
		this.numPlayers=numPlayers;
	

		
		game.addObserver(this);
		this.setLocation(x, y);
		initGUI();
		
	}//GUIView

//-----------------------------------------------------------------------------------------------------------------------------
	
	private void initGUI() {
		
		//MAIN PANEL
		JPanel mainPanel = new JPanel(new BorderLayout());	
		mainPanel.setOpaque(true);
		
		//------------------------------------------------------------------------------------------------------------------------

			//Aloja los botones de accion del juego			
			this.settings= new Settings <S,A>(this);			
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
				
			playerPanel = new PlayerPanel<S,A>(this,this.gameView.getPreferredSize().height,this.numPlayers);
				
				
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
		
		repaint();
	}

	

	private void requestNextMove(boolean stop) {
		if(this.windowPlayer == this.estado.getTurn() && !stop && !this.estado.isFinished()) //la ultima comprobacion  no hace falta pero la pongo por si acaso
			
			switch(this.mode){
			
			case "Random":
				control.makeMove(this.randPlayer.requestAction(this.estado));	
				break;
				
			case "Smart":
				
							
				makeSmartMove();
				break;
		}
		
	}

	@Override
	public void movimientoAleatorio() {
		
		if(this.windowPlayer==this.estado.getTurn() && !this.estado.isFinished()){
			stopSmart();
			control.makeMove(randPlayer.requestAction(this.estado));
		}
	
	}

	@Override
	public void movimientoIA() {
		
		if(this.windowPlayer==this.estado.getTurn()  && !this.estado.isFinished() && this.smartThread==null){
			stopSmart();
			makeSmartMove();
		}
	}

	@Override
	public void startGame() {	
		stopSmart();
		control.startGame();
		
	}

	@Override
	public void setMode(String s) {
		
		this.stopSmart();
		this.mode = s;
		this.notifyEvent(new GameEvent<S,A>(EventType.Change, null, estado, null, "The type of player has changed."));
	}

	@Override
	public void setColor(int player, Color color) {
		this.gameView.putColor(player, color);
		
		if(this.estado!=null)
		this.notifyEvent(new GameEvent<S,A>(EventType.Change, null, estado, null, "The color of player "+player+" has changed."));
	
	}

	@Override
	public void stopGame() {
		stopSmart();
		this.control.stopGame();
		System.exit(1);
		
	}

	@Override
	public void setText(String txt) { 
		this.playerPanel.setText(txt);
		
	}

	@Override
	public void setMaxThreads(int n) {
		this.smartPlayer.setMaxThreads(n);
		this.notifyEvent(new GameEvent<S,A>(EventType.Change, null, estado, null, "The number of threads has changed."));
		
	}

	@Override
	public void setMaxTime(int n) {
		this.smartPlayer.setTimeout(n);
		this.smartTime=n;
		this.notifyEvent(new GameEvent<S,A>(EventType.Change, null, estado, null, "The time has changed."));
		
		
	}

	private void makeSmartMove() {
		
		this.settings.setStopSmartEnabled(true);
		if(this.smartThread==null)
		{this.smartThread = new Thread("smart"){
	
			public void run()
			{
				SwingUtilities.invokeLater(()->settings.changeBrainColor(Color.YELLOW));
			
				long time1=System.currentTimeMillis();
				
				smartAction=smartPlayer.requestAction(estado);
				
				long time2=System.currentTimeMillis();
				
				final long time=time2-time1;
				
				SwingUtilities.invokeLater(()->
				playerPanel.setText(playerPanel.getText()+smartPlayer.getEvaluationCount()+" nodes in "+time+"ms value= "+smartPlayer.getValue()));
				
				SwingUtilities.invokeLater(()->settings.changeBrainColor(Color.WHITE));
				
				SwingUtilities.invokeLater(()->settings.setStopSmartEnabled(false));
				
				if(!Thread.interrupted())
					try {
						SwingUtilities.invokeAndWait(new Runnable(){
							@Override
							public void run() {
								if(smartThread!=null)
									control.makeMove(smartAction);
								smartThread=null;
							}
						});
					} catch (InvocationTargetException | InterruptedException e) {
						stopSmart();
					}
			}
		};
			this.smartThread.start();
		
		}
	}

	public void stopSmart() {
		
		if(this.smartThread!=null){
			this.smartThread.interrupt();
			this.smartThread=null;
		}
		this.settings.setStopSmartEnabled(false);
		
		//this.notifyEvent(new GameEvent<S,A>(EventType.Change, null, estado, null, " "++" has changed."));
	}


}//GameWindow