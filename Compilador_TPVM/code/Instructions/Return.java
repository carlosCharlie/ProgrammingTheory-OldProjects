package Instructions;

import ByteCode.Halt;
import Exceptions.ArrayException;

public class Return implements Instruction{


	public Instruction lexParse(String[] words, LexicalParser lexparser) {
		if(words.length==1 && words[0].equalsIgnoreCase("return"))return new Return();
		else return null;
	}

	
	public void compile(Main.Compiler compiler) throws ArrayException {
		compiler.addByteCode(new Halt());
		
	}

}
