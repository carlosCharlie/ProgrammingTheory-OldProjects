package Instructions.Assignment;

import ByteCode.Store;
import Exceptions.ArrayException;
import Instructions.Instruction;
import Instructions.LexicalParser;

public class SimpleAssignment implements Instruction{

	private  String var_name;
	private Term rhs;

	
	public SimpleAssignment(String var_name, Term rhs) {
	
		this.var_name=var_name;
		this.rhs=rhs;
	
	}

	public SimpleAssignment() {
		// TODO Auto-generated constructor stub
	}

	public Instruction lexParse(String[] words, LexicalParser lexparser) {
		
		char name = words[0].charAt(0);
		if(words.length!=3 || name<'a' || name>'z'|| !words[1].equals("="))
			return null;
		else {
			Term tmp= TermParser.parse(words[2]);
			if(tmp==null)return null;
			else return new SimpleAssignment(words[0],tmp);
		}
	}

	
	public void compile(Main.Compiler compiler) throws ArrayException {
		compiler.addByteCode(this.rhs.compile(compiler));
		compiler.addByteCode(new Store(compiler.getIndex(var_name,true)));
		
	}

}
