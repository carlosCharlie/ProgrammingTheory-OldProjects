package ByteCode;

import CPU.CPU;
import Exceptions.StackException;

public class Mul extends Arithmetics{
	
	
	public ByteCode parse(String s){
		
		if (s.equals("MUL"))
			return new Mul();
		
		else return null;
	}
	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public boolean execute(int op1, int op2, CPU cpu) throws StackException{
		
		cpu.push(op1 * op2);
		
		return true;
	}
	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------

	public String toString(){
		
		return "Mul";
	}
	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
}
