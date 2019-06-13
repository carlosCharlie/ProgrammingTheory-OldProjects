package Instructions.Assignment;

import ByteCode.ByteCode;
import ByteCode.Push;

public class Number implements Term {

	private int num;
	
	public Number(int term){
		
		this.num = term;
	}
	
	
	public Number() {
	}



	public Term parse(String term) {
		
		try{
		Integer.parseInt(term);
		return new Number(Integer.parseInt(term));
		}catch(NumberFormatException e) {
			return null;
		}

	}

	
	public ByteCode compile(Main.Compiler compiler) {
		return new Push(num);
	}

}
