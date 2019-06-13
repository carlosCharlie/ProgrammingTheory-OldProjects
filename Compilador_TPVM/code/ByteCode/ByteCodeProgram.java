package ByteCode;

import Exceptions.ArrayException;

public class ByteCodeProgram {
	
	 private ByteCode[] program;
	 public final static int MAX_PROG=80;
	 private int contador; //posicion por la que estaa relleno el array
	 
	 
	 /*
	  * Inicializa el contador y el array
	  */
	 public ByteCodeProgram(){
		 
		 this.contador=0;
		 this.program= new ByteCode[MAX_PROG];
	 }
	 

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	 
	 /**
	  * mete un bytecode al final programa
	  * @param programa programa a meter
	  * @return true si esta bien false si esta mal
	 * @throws ArrayException 
	  */
	 public boolean setprogram(ByteCode programa) throws ArrayException{
		 
		 if(contador == MAX_PROG)throw new ArrayException("No se pueden añadir mas bytecodes");
		 else{
			 
			 this.program[contador]=programa;
			 this.contador++;
			 return true;
		 }
		
	}
	 

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	 
	 
	 /**
	  * mete un programa en una determinada posicion si se puede
	  * @param programa programa a meter
	  * @param pos posicion en la que meterlo
	  * @return true si se ha metido, false si no
	 * @throws ArrayException 
	  */
	 public void setprogram(ByteCode programa, int pos) throws ArrayException{
		 
		 ;
		 if(pos<0|pos>this.contador-1)
			 throw new ArrayException("No se puede reemplazar un array en la posicion "+pos);
		 
		 else{
			 this.program[pos]=programa;
		 }
	}
	 
	 
//--------------------------------------------------------------------------------------------------------------------------------------------------------------
		
	 
	/**
	 * devuelve el bytecode almacenado en una determinada posicion del programa
	 * @param i posicion en la que puscar
	 * @return bytecode de esa posicion
	 */
	public ByteCode getprogram(int i){
		
		return this.program[i];
	}
	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	/**
	 *transforma el programa en un string
	 *@return programa en string 
	 */
	public String toString(){
		
		String programa="Programa ByteCode almacenado:"+ System.getProperty("line.separator");
		
		for(int i=0;i<this.contador;i++){
			programa = programa + i + ":" + this.program[i] + System.getProperty("line.separator");
		}
		return programa;
	}
	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * devuelve la ultima posicion ocupada
	 * @return ultima posicion almacenada
	 */
	public int getcontador(){
		
		return this.contador;
	}


//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public boolean isFull() {
		if (this.contador == MAX_PROG)
			return true;
		
		else return false;
	}
	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------

	public void reset() {
		
		this.contador = 0;
	}
	
	
	
	
}
