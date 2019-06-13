package ByteCode;

import CPU.CPU;
import Exceptions.StackException;

 abstract public class ConditionalJumps extends ByteCodeOneParameter {

	 /**
	  * constructora para condicionales
	  * @param numero direccion de salto
	  */
	 ConditionalJumps(int numero){
		 
		 super(numero);
	 }
	 

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	 
	 ConditionalJumps(){
		 
		 super();
	 }
	 

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	 
	public boolean execute(CPU cpu) throws StackException {
		
		int op1 = cpu.pop();
		int op2 = cpu.pop();
		if (execute(op2, op1))
			cpu.setProgramCounter(this.parametro);
		
		return true;
	}
	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * devuelve true si la condicion se cumple o false si no comparando el numero con 0
	 * @param numero numero a comparar
	 * @param op1 
	 * @return true si se cumple la condicion, false si no
	 */
	abstract public boolean execute(int n1, int n2);


//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Introduce el parametro de entrada en la variable parametro.
	 * @param i
	 */
	public void setJump(int i)
	{
		this.parametro=i;
	}
}
