package Instructions.Conditions;

import Exceptions.ArrayException;
import Exceptions.LexicalAnalysisException;
import Instructions.Instruction;
import Instructions.LexicalParser;
import Instructions.ParsedProgram;

public class IfThen implements Instruction{

	private ParsedProgram Body;
	private Condition condition;

	public IfThen(Condition tmpCond, ParsedProgram tmpBody) {
		this.condition=tmpCond;
		this.Body=tmpBody;
	}

	public IfThen() {

	}

	
	public Instruction lexParse(String[] words, LexicalParser lexparser)throws LexicalAnalysisException, ArrayException {
		
		if(words.length == 4 && words[0].equals("if")){
			
			try{
				//parsea la codicion
				Condition tmpCond=ConditionParser.parse(words[1],words[2],words[3],lexparser);
				
				
				//parsea el cuerpo
				ParsedProgram tmpBody = new ParsedProgram();
			LexicalParser lp = new LexicalParser(lexparser.getsprogram(),lexparser.getprogramCounter()+1);
			lp.lexicalParser(tmpBody, "endif");
			lexparser.setprogramCounter(lp.getprogramCounter());
			if(tmpCond!=null && tmpBody!=null)return new IfThen(tmpCond,tmpBody);
			else return null;
			
			}catch (LexicalAnalysisException s){
				throw new LexicalAnalysisException();
			}
			
			
		}else return null;
	}

	
	public void compile(Main.Compiler compiler) throws ArrayException {
		
		//int pc = compiler.getBytecodeProgram().getcontador();
		
		this.condition.compile(compiler);
		
		compiler.compile(this.Body);
		
		this.condition.setJump(compiler.getSizeBytecodeProgram());
		
		
	}

	
	
	
}
