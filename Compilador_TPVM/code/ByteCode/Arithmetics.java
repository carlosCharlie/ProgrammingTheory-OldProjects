package ByteCode;

import CPU.CPU;
import Exceptions.DivisionByZero;
import Exceptions.ExecutionErrorException;
import Exceptions.StackException;

abstract public class Arithmetics extends ByteCode{
	
	public ByteCode parse(String[] s){
		
		ByteCode ret;
		
		if(s.length!=1) ret= null;
		else ret= parse(s[0]);
		return ret;
	}

//--------------------------------------------------------------------------------------------------------------------------------------------------------------	
	/**
	 * transforma un string en un bytecode
	 * @param s nombre del bytecode
	 * @return bytecode con ese nombre o null si ha ocurrido un error
	 */
	abstract public ByteCode parse(String s);
	
//--------------------------------------------------------------------------------------------------------------------------------------------------------------	
	
	public boolean execute(CPU cpu) throws ExecutionErrorException{
		
		
		if (cpu.getStackCounter() >= 2){
			
			int op1=cpu.pop();
			int op2=cpu.pop();
			
			try{
				
				this.execute(op2,op1,cpu);
			}
			catch (DivisionByZero e){
				
				throw new DivisionByZero();
			}
			
			
		}//if
		else throw new StackException("Excepcion-bytecode " + this.toString() + ": Tamaño de pila insuficiente");
				
		return true;
	}
	
//--------------------------------------------------------------------------------------------------------------------------------------------------------------	
	
	/**
	 * realiza la operacion
	 * @param op1 operando 1
	 * @param op2 operando 2
	 * @param cpu cpu donde se realiza la operacion
	 * @return resultado de hacer la operacion con operando 1 y operando 2
	 * @throws DivisionByZero 
	 * @throws StackException 
	 */
	abstract public boolean execute(int op1,int op2,CPU cpu) throws ExecutionErrorException;
	
//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
}
