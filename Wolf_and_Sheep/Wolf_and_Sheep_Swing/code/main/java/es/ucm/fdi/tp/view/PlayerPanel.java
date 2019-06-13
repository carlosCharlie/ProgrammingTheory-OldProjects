package es.ucm.fdi.tp.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;

public class PlayerPanel<S extends GameState<S,A>,A extends GameAction<S,A>> extends JPanel {

	private GameControllerView<S,A> control;
	
	private JTextArea statusMessages;
	//Tabla de colores.
	private Map<Integer, Color> colors;
	private ColorChooser colorChooser;
	private MyTableModel tModel;
	
	public PlayerPanel(GameControllerView<S,A> control,int size,int numCols,Color[]colores){
	
		super(new BorderLayout());
		
		this.control=control;
		
		this.setPreferredSize(new Dimension(210, size));
		
		initGUI(numCols,colores);
		
	}
	private void initGUI(int numCols,Color[]colores) {
		//PANEL CENTRO DERECHA
		
			this.setBorder(BorderFactory.createEmptyBorder(5,0,2,2));
					
			
		//***************************************************************************************************

			
			//PANEL STATUS MESSAGES --> muestra la informacion de las jugadas en el transcurso del juego
			
			 statusMessages = new JTextArea ();
			
				//permite editar el area de texto.
				statusMessages.setEditable(false);
				
				statusMessages.setBackground(Color.WHITE);
			
				//Implementamos el area de texto para que tenga barra desplazadora.
				JScrollPane area_barra = new JScrollPane (statusMessages);
				
					//ponemos un borde con su titulo
					area_barra.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Status Messages"));
									
			
		//Anadimos al panel del centro
		this.add(area_barra, BorderLayout.CENTER);
		
			
		//***************************************************************************************************
			
			//PANEL PLAYER INFORMATION --> muestra la tableta de colores que identifican a las piezas.
		
			JPanel playerInformation = new JPanel (new BorderLayout());
			
				
				playerInformation.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Player Information"));
				playerInformation.setBackground(getBackground());
				playerInformation.setPreferredSize(new Dimension(5, 200));
				
					//CREACION DE LA TABLA DE COLORES Y SU POSTERIOR JDIALOG QUE SERVIRÃ� PARA MODIFICAR EL COLOR DE LA MISMA
					
					colors = new HashMap<>();
					colorChooser = new ColorChooser(new JFrame(), "Choose Line Color", Color.BLACK);
					
					// names table
					tModel = new MyTableModel(numCols);
					
					//damos valores a la columna de los jugadores.
					
					for(int i=0; i<numCols;i++){
						tModel.setValueAt(i, i, 0);
						colors.put(i, colores[i]);
					}
					final JTable table = new JTable(tModel) {
						
						private static final long serialVersionUID = 1L;

						// THIS IS HOW WE CHANGE THE COLOR OF EACH ROW
						@Override
						public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
							Component comp = super.prepareRenderer(renderer, row, col);

							// the color of row 'row' is taken from the colors table, if
							// 'null' setBackground will use the parent component color.
							if (col == 1)
								comp.setBackground(colors.get(row));
							else
								comp.setBackground(Color.WHITE);
							comp.setForeground(Color.BLACK);
							return comp;
						}
					};

					table.addMouseListener(new java.awt.event.MouseAdapter() {
						@Override
						public void mouseClicked(java.awt.event.MouseEvent evt) {
							int row = table.rowAtPoint(evt.getPoint());
							int col = table.columnAtPoint(evt.getPoint());
							if (row >= 0 && col >= 0) {
								changeColor(row);
							}
						}

					});
					
			JScrollPane table_barra = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			
			
			playerInformation.add(table_barra, BorderLayout.CENTER);
				
				
		this.add(playerInformation, BorderLayout.SOUTH);
	
			
	//--------------------------------------------------------------------------------------------------------------------------------------	

			
//anadimos al panel del centro.
		
	}
	public void setText(String s) {

		this.statusMessages.setText(s);
		
	}
	public String getText() {
		
		return this.statusMessages.getText();
	}
	
	
	private void changeColor(int row) {
		
		colorChooser.setSelectedColorDialog(colors.get(row));
		
		colorChooser.openDialog();
		
		if (colorChooser.getColor() != null) {
			
			colors.put(row, colorChooser.getColor());
			this.control.setColor(row,colorChooser.getColor());
			repaint();
			
		}//if
		
		
	}
}
