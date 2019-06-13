package Instructions;

import ByteCode.Load;
import ByteCode.Out;
import Exceptions.ArrayException;


public class Write implements Instruction{

	private String var_name;
	
	public Write(String name) {
		this.var_name=name;
	}

	public Write() {
		
	}


	public Instruction lexParse(String[] words, LexicalParser lexparser) {
		if(words.length==2 && words[0].equalsIgnoreCase("write"))return new Write(words[1]);
		else return null;
	}


	public void compile(Main.Compiler compiler) throws ArrayException {
		compiler.addByteCode(new Load(compiler.getIndex(var_name,false)));
		compiler.addByteCode(new Out());
		
	}

}
