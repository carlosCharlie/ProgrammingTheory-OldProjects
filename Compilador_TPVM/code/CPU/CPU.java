package CPU;

import ByteCode.ByteCode;
import ByteCode.ByteCodeProgram;
import Exceptions.ArrayException;
import Exceptions.DivisionByZero;
import Exceptions.ExecutionErrorException;
import Exceptions.StackException;


public class CPU {
	
	private int programCounter = 0;
	private ByteCodeProgram bcProgram;
	private Memory memory;
	private OperandStack stack;
	private Boolean halt;

//--------------------------------------------------------------------------------------------------------------------------------------------------------------	

	/**
	 * Inicializo las variables de la clase CPU.
	 * @param program programa inicial
	 */
	public CPU(ByteCodeProgram program){
		
		this.halt = false;
		this.memory = new Memory();
		this.stack = new OperandStack();
		this.bcProgram = program;
	}
	
//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * ejecuta el programa
	 * @return true si se ha ejecutado correctamente o false si no
	 * @throws ArrayException 
	 * @throws ExecutionErrorException 
	 * @throws StackException 
	 */
	public boolean run() throws ArrayException, StackException, ExecutionErrorException{
		
		this.programCounter = 0;
		boolean error = false;
		
		while(this.programCounter < bcProgram.getcontador() && !error && !this.halt){
			
			ByteCode bc = bcProgram.getprogram (this.programCounter);
			this.programCounter++;
			
			try{
				if (!bc.execute(this)) error = true;
			}
			catch (DivisionByZero s){
				
				throw new DivisionByZero();
			}
			
		}
		return !error;
	}
	
//--------------------------------------------------------------------------------------------------------------------------------------------------------------
		
	/**
	 * Imprime la secuencia de ByteCodes almacenados en el programa
	 * 
	 * @return Secuencia de los bytecodes almacenados.
	 */
	public String toString(){
		
		String s ="Estado de la CPU: " + System.getProperty("line.separator") + this.memory
				+System.getProperty("line.separator") + this.stack;
		 
		return s;
	}

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * resetea la memoria y la pila de la cpu
	 */
	public void reset(){
		
		this.stack.reset();
		this.memory.reset();
		this.halt=false;
	}
	
//--------------------------------------------------------------------------------------------------------------------------------------------------------------	
	
	/**
	 * Hace pop en la pila de la cpu
	 * @return ultimo elemento de la pila o null si no se ha podido sacar
	 * @throws StackException 
	 */
	public Integer pop() throws StackException{
		
		return this.stack.pop();
	}
	
//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Hace push en la pila de la cpu
	 * @param n numero a meter en la pila
	 * @return true si se ha podido introducir o false si no
	 * @throws StackException 
	 */
	public boolean push(int n) throws StackException{
		
		return this.stack.push(n);
	}

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Cambia el contador del programa
	 * @param i posicion de la siguiente instruccion
	 * @return true si se ha podido cambiar o false si no
	 */
	public boolean setProgramCounter(int i){
		
		boolean ret = true;
		
		if(i >= 0 && i < this.bcProgram.getcontador())
			this.programCounter = i;
		
		else ret= false;
		
		return ret;
	}
	
//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Aumenta uno mas al contador de programa
	 */
	public void increaseProgramCounter(){
		
		this.programCounter++;
	}

//--------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Devuelve el programa almacenado
	 * @return programa almacenado
	 */
	public ByteCodeProgram getBcProgram() {
		
		return this.bcProgram;
	}
	
//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Guarda un dato en la memoria
	 * @param posicion posicion de la memoria
	 * @param numero dato a guardar
	 * @return true si se ha guardado bien false si no
	 * @throws ArrayException 
	 */
	public boolean Write(int posicion,int numero) throws ArrayException{
		
		return this.memory.write(posicion,numero);
	}
	
//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Devuelve el numero de elementos de la pila
	 * @return numero de elementos en la pila
	 */
	public int getStackCounter(){
		
		return this.stack.getContador();
	}

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Lee de la memoria
	 * @param posicion posicion a leer
	 * @return dato que hay en esa posicion o null si hay un error
	 * @throws ArrayException 
	 */
	public Integer Read(int posicion) throws ArrayException{
		
		return this.memory.read(posicion);
	}

	
	/**
	 * Impriem por pantalla el numero posicionado en la cima de la pila.
	 * @return true si hay cima, false en caso contrario.
	 * @throws StackException
	 */
	public boolean out() throws StackException {
		
		boolean ret=false;
		
		if(this.getStackCounter()>=1){
			
			Integer tmp = this.stack.pop();

				System.out.println("console: "+tmp);
				ret =true;
		}
		
		return ret;
	}
	
	
	//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Pone halt a true
	 * @return true
	 */
	public boolean Halt(){
		
		this.halt=true;
		return true;
	}
	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Copia el ByteCodeProgram que le llega como parametro al de la CPU.
	 * @param bcProgram
	 */
	public void setBcProgram(ByteCodeProgram bcProgram){
		this.bcProgram=bcProgram;
	}

}
