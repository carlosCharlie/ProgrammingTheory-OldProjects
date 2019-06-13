package es.ucm.fdi.tp.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GamePlayer;
import es.ucm.fdi.tp.base.model.GameState;

public class Settings<S extends GameState<S,A>,A extends GameAction<S,A>> extends JToolBar{

	private GameControllerView <S,A> control;
	private JLabel brainLabel;
	private JButton stopButton;
	
	public Settings(GameControllerView <S,A> controller)
	{
		super();
		this.control=controller;
		initGUI();
	}

	private void initGUI() {
		
		
		//PANEL NORTE
		JPanel pnlNorth = new JPanel(new BorderLayout());
		
		
		
		JPanel pnlWest = new JPanel();
		
		//añade los componentes de izquierda a derecha.
		pnlWest.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		
			//creamos los botones
			JButton randButton = new JButton();
			randButton.setPreferredSize(new Dimension(32,32));
			randButton.setBorder(BorderFactory.createEmptyBorder());
			randButton.setBackground(Color.WHITE);
			randButton.setIcon(new ImageIcon("src/main/java/es/ucm/fdi/tp/view/dado.png"));
			pnlWest.add(randButton);
			
			randButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
				
					control.movimientoAleatorio();
					
					
				}
				
			});
				
			
			
			
			JButton smartButton = new JButton();
			smartButton.setPreferredSize(new Dimension(32,32));
			smartButton.setBorder(BorderFactory.createEmptyBorder());
			smartButton.setBackground(Color.WHITE);
			smartButton.setIcon(new ImageIcon("src/main/java/es/ucm/fdi/tp/view/nerd-face.png"));
			pnlWest.add(smartButton);
			
			smartButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
				
					control.movimientoIA();
					
					
				}
				
			});
			
			
			
			JButton undoButton = new JButton();
			undoButton.setPreferredSize(new Dimension(32,32));
			undoButton.setBorder(BorderFactory.createEmptyBorder());
			undoButton.setBackground(Color.WHITE);
			undoButton.setIcon(new ImageIcon("src/main/java/es/ucm/fdi/tp/view/undo.png"));
			pnlWest.add(undoButton);
			
			
			undoButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
				
					control.startGame();
					
					
				}
				
			});
			
			
			JButton exitButton = new JButton();
			exitButton.setPreferredSize(new Dimension(32,32));
			exitButton.setBorder(BorderFactory.createEmptyBorder());
			exitButton.setBackground(Color.WHITE);
			exitButton.setIcon(new ImageIcon("src/main/java/es/ucm/fdi/tp/view/exit.png"));
			pnlWest.add(exitButton);
			
			exitButton.addActionListener(new ActionListener(){
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					control.stopGame();
					
				}
			});
		
			//creamos la etiqueta.
			JLabel etqNorth = new JLabel("Player Mode:");
			pnlWest.add(etqNorth);
			
			//creamos el ComboBox
			String[] opcionCombo = {"Manual", "Random", "Smart"};
			final JComboBox comboNorth = new JComboBox (opcionCombo);
			
				comboNorth.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						control.setMode((String)comboNorth.getSelectedItem());
						
					}
					
					
					
				});
			
				pnlWest.add(comboNorth);
			
				
			//panel Este
			JPanel pnlEast = new JPanel(new FlowLayout(FlowLayout.LEFT));
			pnlEast.setBorder(BorderFactory.createTitledBorder("Smart Moves"));
				
			
				//threads
				this.brainLabel = new JLabel(new ImageIcon("src/main/resources/brain.png"));
				this.brainLabel.setOpaque(true);
				this.brainLabel.setBackground(Color.WHITE);
				pnlEast.add(brainLabel);
			
	
				JSpinner selectorThreads= new JSpinner(new SpinnerNumberModel(1,1,Runtime.getRuntime().availableProcessors(),1));
				pnlEast.add(selectorThreads);
				selectorThreads.addChangeListener(new ChangeListener(){

					@Override
					public void stateChanged(ChangeEvent e) {
						control.setMaxThreads((int) selectorThreads.getValue());
						
					}
					
				});
			
				JLabel thLabel=new JLabel("Threads");
				pnlEast.add(thLabel);
				
				
				//cronometro
				JLabel timeLabel = new JLabel(new ImageIcon("src/main/resources/timer.png"));
				pnlEast.add(timeLabel);
			
	
				JSpinner selector= new JSpinner(new SpinnerNumberModel(500,500,5000,500));
				pnlEast.add(selector);
				selector.addChangeListener(new ChangeListener(){

					@Override
					public void stateChanged(ChangeEvent e) {
						control.setMaxTime((int) selector.getValue());
						
					}
					
				});
				
				JLabel tLabel=new JLabel("ms");
				pnlEast.add(tLabel);
				
				this.stopButton = new JButton(new ImageIcon("src/main/resources/stop.png"));
				pnlEast.add(stopButton);
				stopButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						control.stopSmart();
						
					}
					
				});
				this.stopButton.setEnabled(false);
					
		
			
		pnlNorth.add(pnlWest,BorderLayout.WEST);
		pnlNorth.add(pnlEast,BorderLayout.EAST);
			
			this.setVisible(true);
			this.add(pnlNorth);
	}

	public void changeBrainColor(Color color) {
		
		this.brainLabel.setBackground(color);
	}

	public void setStopSmartEnabled(boolean b) {
		this.stopButton.setEnabled(b);
		
	}
	
	
}
