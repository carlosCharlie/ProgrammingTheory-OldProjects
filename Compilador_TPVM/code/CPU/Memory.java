package CPU;

import Exceptions.ArrayException;

public class Memory {
	
	private Integer[] memory;
	private int Max_M;
	private final int MAXIMO = 20;
	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * inicializa la memoria a null
	 */
	public Memory(){
		
		Max_M = MAXIMO;
		this.memory = new Integer[MAXIMO];
		
		for(int i = 0; i < Max_M; i++){
			
			this.memory[i] = null;
		}
	}
	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * escribe un numero en la memoria si la posicion es correcta (la memoria es ilimitada)
	 * @param pos posicion donde escribe
	 * @param value numero a escribir(entero)
	 * @return ret devuelve verdadero si se ha podido escribir o falso si no
	 * @throws ArrayException 
	 */
	public boolean write(int pos, int value) throws ArrayException{
		
		this.resize(pos);
		boolean ret;
		
		if(pos < 0)
			throw new ArrayException("No se puede escribir en la posicion "+pos);
		
		else {

		this.memory[pos] = value;
		ret = true;
		}
		
		return ret;
	 }
	
	
//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	/**
	 * devuelve el dato de una determinada posicion
	 * @param pos posicion de la memoria
	 * @return tmp dato de esa posicion (null si esta vacio)
	 * @throws ArrayException 
	 */
	 public Integer read(int pos) throws ArrayException{
		 
		 Integer tmp;
		 
		 if(pos < 0)
				throw new ArrayException("No se puede leer de la posicion "+pos);
		 	
		 else{
		 		this.resize(pos);
		 		tmp = this.memory[pos];
		 	}
		 
		 return tmp;
		}
	 
	 

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	 
	 
	 /**
	  * si se quiere escribir en una posicion mayor del tamaño actual de la memoria, se aumenta el tamaño
	  * @param posicion donde se quiere guardar el dato
	  */
	 private void resize(int posicion){
		 
		 if(posicion >= Max_M){
			 
			 Integer[] tmp = new Integer[posicion * 2];
		
		 	for(int i = 0; i < Max_M; i++){
		 		
		 		tmp[i] = this.memory[i];
		 	}
		 
		 	for(int i = Max_M; i < posicion * 2; i++){
		 		
		 		tmp[i] = null; 
		 	}
		 	
		 	this.memory = tmp;
		 	Max_M = posicion * 2;
		 }
	 }
	
	 

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	 
	 /**
	  * Transforma los datos de la memoria en un string en forma de lista
	  */
	public String toString(){
		
		String m = "";

		for(int i = 0; i < this.Max_M; i++){
			
			if(this.memory[i]!=null)
				m = m + "[" + i + "]" + ":" + this.memory[i] + " ";

		}
		
		if(m.length() == 0) m="<vacia> ";
		
		m = "Memoria: " + m;
		return m;
	}
	
	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Reseta la memoria creando un nuevo array.
	 */
	public void reset() {
		
		this.memory = new Integer[MAXIMO];
		
	}
	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
}
