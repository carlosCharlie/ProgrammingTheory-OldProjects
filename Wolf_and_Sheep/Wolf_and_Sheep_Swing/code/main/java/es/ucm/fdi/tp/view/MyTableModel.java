package es.ucm.fdi.tp.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

class MyTableModel extends AbstractTableModel {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private String[] colNames;
	List<String> names;

	MyTableModel(int numPlayers) {
		
		this.colNames = new String[] { "#Player", "Color" };
		names = new ArrayList<String>();
		
		for(int  i=0;i<numPlayers;i++)
		names.add("");
	}

	@Override
	public String getColumnName(int col) {
		
		return colNames[col].toString();
	}

	@Override
	public int getColumnCount() {
		
		return colNames.length;
	}

	@Override
	public int getRowCount() {
		
		return names != null ? names.size() : 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		if (columnIndex == 0) {
			
			return rowIndex;
			
		} 
		
		else
			
			return names.get(rowIndex);
		
	}

	public void addName(String name) {
		
		names.add(name);
		
		refresh();
	}

	public void refresh() {
		fireTableDataChanged();
	}

};