package ByteCode;

import CPU.CPU;
import Exceptions.DivisionByZero;
import Exceptions.ExecutionErrorException;
import Exceptions.StackException;

public class Div extends Arithmetics{

	
	public ByteCode parse(String s){
		
		if (s.equals("DIV"))
			return new Div();
		
		else return null;
	}


//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	

	public boolean execute(int op1, int op2, CPU cpu) throws ExecutionErrorException{
		
		boolean ok;
		
		if(op2 == 0 ) throw new DivisionByZero();


		ok = true;
		cpu.push(op1 / op2);
		
		return ok;
	}


//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public String toString(){
		
		return "Div";
	}


//--------------------------------------------------------------------------------------------------------------------------------------------------------------

	
}
