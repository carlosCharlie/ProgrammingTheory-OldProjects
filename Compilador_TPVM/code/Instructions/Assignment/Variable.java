package Instructions.Assignment;

import ByteCode.ByteCode;
import ByteCode.Load;
import Exceptions.ArrayException;

public class Variable implements Term {

	private String letra;
	public Variable(String s){
		
		this.letra = s;
	}
	
	public Variable() {
		
	}

	
	public Term parse(String term) {
		
		if (term.length()!=1) return null;
		else{ 
			
			char name = term.charAt(0);
			if (name>='a' && name <='z') return new Variable(term); 
			else return null;
		}
	}

	public ByteCode compile(Main.Compiler compiler) throws ArrayException {
		return new Load(compiler.getIndex(this.letra,false));
	}

}
