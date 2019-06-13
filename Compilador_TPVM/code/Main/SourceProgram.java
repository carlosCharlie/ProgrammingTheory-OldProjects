package Main;

import Exceptions.ArrayException;

public class SourceProgram {
	
	private String[] sProgram;
	private int contador;
	static public final int Max_SourceProgram=30;



	public SourceProgram()
	{
		this.sProgram=new String[Max_SourceProgram];
		this.contador=0;
	}
	
	/**
	 * añade una linea al sourceprogram
	 * @param linea linea a introducir
	 * @throws ArrayException
	 */
	public void pushSourceProgram(String linea) throws ArrayException{
		if(contador>=Max_SourceProgram)throw new ArrayException("No se pueden añadir más lineas");
		else{
			
			this.sProgram[contador]=linea;
			this.contador++;
		}
	}
	
	/**
	 * devuelve una determinada linea del sourceprogram
	 * @param i numero de linea
	 * @return linea
	 */
	public String getLine(int i)
	{
		return sProgram[i];
	}
	
	public String toString()
	{
		String programa="Programa fuente almacenado:"+ System.getProperty("line.separator");
		
		for(int i=0;i<this.contador;i++){
			programa = programa + i + ":" + this.sProgram[i] + System.getProperty("line.separator");
		}
		return programa;
	}

	/**
	 * devuelve el tamaño del sourceprogram
	 * @return tamaño de sourceprogram
	 */
	public int getContador() {
		return this.contador;
	}
	
	
	
	
}