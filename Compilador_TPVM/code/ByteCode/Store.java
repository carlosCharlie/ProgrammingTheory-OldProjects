package ByteCode;

import CPU.CPU;
import Exceptions.ArrayException;
import Exceptions.StackException;

public class Store extends ByteCodeOneParameter{
	
	

	public Store(int posicion){
		
		super(posicion);
	}
	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	Store(){
		
		super();
	}


//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public ByteCode parse(String nombre, int i){
		
		if(nombre.equals("STORE"))
			return new Store(i);
		
		else return null;
	}


//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public boolean execute(CPU cpu) throws ArrayException, StackException {
		
		
		
			
			int tmp = cpu.pop();
			boolean ret = cpu.Write(this.parametro, tmp);
			return ret;
	}


//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public String toString(){
		
		return "Store " + this.parametro;
	}
	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
}
