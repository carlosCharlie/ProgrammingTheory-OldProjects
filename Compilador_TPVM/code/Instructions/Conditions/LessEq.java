package Instructions.Conditions;

import ByteCode.IfLeq;
import Exceptions.ArrayException;
import Instructions.LexicalParser;
import Instructions.Assignment.Term;
import Instructions.Assignment.TermParser;

public class LessEq extends Condition{

	public LessEq(Term tmp1, Term tmp2) {
		this.term1=tmp1;
		this.term2=tmp2;
		this.condition=new IfLeq();
	}

	public LessEq() {
		
	}

	
	public Condition parse(String t1, String op, String t2, LexicalParser parser) {
		Term tmp1=TermParser.parse(t1);
		Term tmp2=TermParser.parse(t2);
		
		if(tmp1==null ||tmp2==null|| !op.equals("<="))return null;
		else return new LessEq(tmp1,tmp2);
	}

	
	public void compile(Main.Compiler compiler) throws ArrayException {
		compiler.addByteCode(this.term1.compile(compiler));
		compiler.addByteCode(this.term2.compile(compiler));
		compiler.addByteCode(this.condition);		
	}

}
