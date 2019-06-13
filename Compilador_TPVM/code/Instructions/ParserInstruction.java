package Instructions;

import Exceptions.ArrayException;
import Exceptions.LexicalAnalysisException;
import Instructions.Assignment.CompoundAssignment;
import Instructions.Assignment.SimpleAssignment;
import Instructions.Conditions.IfThen;
import Instructions.Conditions.While;

public class ParserInstruction {

	private final static Instruction[] Instructions={new  SimpleAssignment(),new CompoundAssignment(), new While(),new IfThen(), new Write(),new Return()};
	
	
	/**
	 * Parse el programa fuente.
	 * @param Ins
	 * @param lp
	 * @return una instrucción.
	 * @throws ArrayException
	 */
	static public Instruction parse(String Ins,LexicalParser lp) throws ArrayException
	{
		Ins=Ins.trim();
		String[]cadena=Ins.split(" ");
		Instruction tmp;
		int i=0;
		do{
			
			try{tmp=Instructions[i].lexParse(cadena, lp);}
			catch (LexicalAnalysisException e)
			{
				return null;
			}
		
			
			i++;
			
		}while(tmp==null && i<Instructions.length);
		
		return tmp;
	}
}
