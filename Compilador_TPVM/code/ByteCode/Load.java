package ByteCode;

import CPU.CPU;
import Exceptions.ArrayException;
import Exceptions.StackException;

public class Load extends ByteCodeOneParameter{
	
	/**
	 * Constructora de la clase Load
	 * @param param
	 */
	public Load(int param){
		
		super(param);
	}


//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Constructura de la clase IfEq
	 */
	public Load(){
		
		super();
	}


//--------------------------------------------------------------------------------------------------------------------------------------------------------------

	public boolean execute(CPU cpu) throws ArrayException, StackException{
		
		boolean ret=true;
		
		Integer tmp = cpu.Read(this.parametro);
		 
		 if (tmp != null)
			 ret = cpu.push(tmp);
		 
		 else ret = false;
		 
		 return ret;
	}


//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public ByteCode parse(String nombre,int numero) {
		
		if(nombre.equals("LOAD"))
			return new Load(numero);
		
		else return null;
	}
	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------

	public String toString(){
		
		return "Load " + this.parametro;
	}
	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
}
