package Instructions.Assignment;

import ByteCode.Add;
import ByteCode.Div;
import ByteCode.Mul;
import ByteCode.Store;
import ByteCode.Sub;
import Exceptions.ArrayException;
import Instructions.Instruction;
import Instructions.LexicalParser;

public class CompoundAssignment implements Instruction {

	private String var_name;
	private String operator;
	private Term term1;
	private Term term2;
	
	public CompoundAssignment(String nombre, Term op1, String operacion, Term op2) {
		this.var_name=nombre;
		this.operator=operacion;
		this.term1=op1;
		this.term2=op2;
	}

	public CompoundAssignment() {
		
	}

	public Instruction lexParse(String[] words, LexicalParser lexparser) {

	char name = words[0].charAt(0);
			
			if(words.length!=5 || name<'a' || name>'z'|| !(words[1].equals("=")))
				return null;
				
			else {
				if(!words[3].equals("+") && !words[3].equals("-") && !words[3].equals("*") && !words[3].equals("/")) return null;
				
				else{
					Term tmp1=TermParser.parse(words[2]);
					Term tmp2=TermParser.parse(words[4]);
				
				if(tmp1!=null&&tmp2!=null)return new CompoundAssignment(words[0],TermParser.parse(words[2]),words[3],TermParser.parse(words[4]));			
				else return null;
				}
			}
			
			
	}

	
	public void compile(Main.Compiler compiler) throws ArrayException {
		
		compiler.addByteCode(this.term1.compile(compiler));
        
        compiler.addByteCode(this.term2.compile(compiler));
         
        switch (this.operator){
         
        case "+":
            compiler.addByteCode(new Add());
            break;
             
        case "-":
            compiler.addByteCode(new Sub());
            break;
             
        case "/":
            compiler.addByteCode(new Div());
            break;
             
        case "*":
            compiler.addByteCode(new Mul());
            break;  
             
             
        }//switch
         
        compiler.addByteCode(new Store(compiler.getIndex(var_name,true)));
		
	}

}
