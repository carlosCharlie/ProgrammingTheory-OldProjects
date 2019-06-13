package Instructions;

import Exceptions.ArrayException;
import Exceptions.LexicalAnalysisException;
import Main.SourceProgram;

public class LexicalParser {

	 private SourceProgram sProgram;
	 private int programCounter;
	 
	 
	 public LexicalParser(SourceProgram sProgram, int i) {
		 this.sProgram=sProgram;
		 this.programCounter=i;
	 }


	public void lexicalParser(ParsedProgram pProgram, String stopKey)throws LexicalAnalysisException, ArrayException{
		 
		 Instruction tmp=new Return();//para inicializar
		 
		 while(!this.sProgram.getLine(this.programCounter).trim().equalsIgnoreCase(stopKey) && tmp!=null)
		 {
			tmp=ParserInstruction.parse(this.sProgram.getLine(this.programCounter),this);
			
			if(tmp!=null)
				{
				pProgram.push(tmp);
				this.programCounter++; 
				}else throw new LexicalAnalysisException();
		
		 }
	 }


	public SourceProgram getsprogram() {
		return this.sProgram;
	}


	public int getprogramCounter() {
		return this.programCounter;
	}


	public void setprogramCounter(int counter) {
		this.programCounter=counter;
		
	};
}
