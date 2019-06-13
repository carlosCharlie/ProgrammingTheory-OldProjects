package CPU;

import Exceptions.StackException;

public class OperandStack {

	private int[] stack;
	public final static int MAX_STACK = 3;
	private int contador;
	
	/**
	 * Incializamos las variables de la clase OperandStack
	 */
	public OperandStack(){
	
		this.stack = new int [MAX_STACK];
		this.contador = 0;
	
	}
	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Almacena el valor pasado por parametro en la pila.
	 * @param n valor para introducir.
	 * @return true si hay espacio suficiente en la pila .
	 * @throws StackException 
	 */
	public boolean push (int n) throws StackException{
		
		if (contador < (MAX_STACK-1)){
			
			this.stack[contador] = n;
			this.contador++;
			return true;
		}
		else 
			{
			throw new StackException("La pila esta llena");
			
			}
	}
	
	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Saca el valor que hay en la pila en la posicion que llega por parametro.
	 * @param n posicion de la pila.
	 * @return Valor sacado de la pila.
	 * @throws StackException 
	 */
	public int pop () throws StackException{
		
		int i;
		if(this.contador==0)throw new StackException("La pila esta vacia");
		else{
			i = stack[this.contador - 1];
			this.contador--;
		
			return i;
	}
	}
	
	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Devuelve la posisicon en la cual se encuentra la pila.
	 * @return Posicion de la pila.
	 */
	public int getContador(){
		
		return this.contador;
	}
	
	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Muestra de forma ordenada los elementos que hay almacenados en la pila.
	 * @return Secuencia de elementos almacenados en la pila.
	 */
	public String toString(){
		
		int i;
		String s = "Pila: ";
		
		for(i = 0; i < this.contador; i++){
			
			s = s + this.stack[i] + " ";
		}
		
		if(i == 0) s = s + "<vacia>";

		return s;
		
	}
	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Resetea la pila poniednola a 0.
	 */
	public void reset() {
		this.contador = 0;
		
	}
	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
}