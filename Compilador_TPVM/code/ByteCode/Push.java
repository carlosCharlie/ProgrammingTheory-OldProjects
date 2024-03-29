package ByteCode;

import CPU.CPU;
import Exceptions.StackException;

public class Push extends ByteCodeOneParameter{

	public Push(int numero){
		
		super(numero);
	}
	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public Push(){
		
		super();
	}
	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public boolean execute(CPU cpu) throws StackException{
		
		return cpu.push(this.parametro);
	}


//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	

	public ByteCode parse(String nombre,int numero){
	
		if(nombre.equals("PUSH"))
			return new Push(numero);
		
		else return null;
	}
	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public String toString(){
		
		return "Push " + this.parametro;
	}


//--------------------------------------------------------------------------------------------------------------------------------------------------------------

}
