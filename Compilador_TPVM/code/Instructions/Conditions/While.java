package Instructions.Conditions;

import ByteCode.GoTo;
import Exceptions.ArrayException;
import Exceptions.LexicalAnalysisException;
import Instructions.Instruction;
import Instructions.LexicalParser;
import Instructions.ParsedProgram;

public class While implements Instruction{
	
	private Condition condition;
	private ParsedProgram whileBody;

	public While(Condition tmpCond, ParsedProgram tmpBody) {
		this.condition=tmpCond;
		this.whileBody=tmpBody;
	}

	public While() {
		
	}

	public Instruction lexParse(String[] words, LexicalParser lexparser)throws LexicalAnalysisException, ArrayException  {
		
		if(words.length==4 && words[0].equals("while")){
		
		

			
			
			try{
				//parsea la codicion
				Condition tmpCond=ConditionParser.parse(words[1],words[2],words[3],lexparser);
				
				//parsea el cuerpo
				ParsedProgram tmpBody=new ParsedProgram();
				LexicalParser lp = new LexicalParser(lexparser.getsprogram(),lexparser.getprogramCounter()+1);
				lp.lexicalParser(tmpBody, "ENDWHILE");
				lexparser.setprogramCounter(lp.getprogramCounter());
				if(tmpCond!=null && tmpBody!=null)return new While(tmpCond,tmpBody);
				else return null;
				}
			
			catch (LexicalAnalysisException s){
				throw new LexicalAnalysisException();
			}
		}else return null;
	}

	
	public void compile(Main.Compiler compiler) throws ArrayException {
		
		int pc=compiler.getBytecodeProgram().getcontador();
		
		this.condition.compile(compiler);					
		
		compiler.compile(this.whileBody);
				
		compiler.addByteCode(new GoTo(pc));					
		
		this.condition.setJump(compiler.getSizeBytecodeProgram());
	}

}
