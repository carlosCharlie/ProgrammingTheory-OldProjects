package es.ucm.fdi.tp.view;

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
import javax.swing.JToolBar;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GamePlayer;
import es.ucm.fdi.tp.base.model.GameState;

public class Settings<S extends GameState<S,A>,A extends GameAction<S,A>> extends JToolBar{

	private GameControllerView <S,A> control;
	
	public Settings(GameControllerView <S,A> controller)
	{
		super();
		this.control=controller;
		initGUI();
	}

	private void initGUI() {
		
		
		//PANEL NORTE
		JPanel pnlNorth = new JPanel();
		
		//añade los componentes de izquierda a derecha.
		pnlNorth.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		
			//creamos los botones
			JButton randButton = new JButton();
			randButton.setPreferredSize(new Dimension(32,32));
			randButton.setBorder(BorderFactory.createEmptyBorder());
			randButton.setBackground(Color.WHITE);
			randButton.setIcon(new ImageIcon("src/main/java/es/ucm/fdi/tp/view/dado.png"));
			pnlNorth.add(randButton);
			
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
			pnlNorth.add(smartButton);
			
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
			pnlNorth.add(undoButton);
			
			
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
			pnlNorth.add(exitButton);
			
			exitButton.addActionListener(new ActionListener(){
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					control.stopGame();
					
				}
			});
		
			//creamos la etiqueta.
			JLabel etqNorth = new JLabel("Player Mode:");
			pnlNorth.add(etqNorth);
			
			//creamos el ComboBox
			String[] opcionCombo = {"Manual", "Random", "Smart"};
			final JComboBox comboNorth = new JComboBox (opcionCombo);
			
				comboNorth.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						control.setMode((String)comboNorth.getSelectedItem());
						
					}
					
					
					
				});
			
			
			
			
			pnlNorth.add(comboNorth);
		
			this.setVisible(true);
			this.add(pnlNorth);
	}
	
	
}
