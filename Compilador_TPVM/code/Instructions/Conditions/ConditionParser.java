package Instructions.Conditions;

import Exceptions.LexicalAnalysisException;
import Instructions.Instruction;
import Instructions.LexicalParser;
import Instructions.Assignment.CompoundAssignment;
import Instructions.Assignment.SimpleAssignment;

public class ConditionParser {

	private final static Condition[] Conditions={new  Equal(), new Less(),new LessEq(),new NotEqual()};

	/**
	 * parsea las condiciones
	 * @param op1 primer parametro
	 * @param op2 condicion
	 * @param op3 segundo parametro
	 * @param lp
	 * @return la condicion o null si esta mal
	 * @throws LexicalAnalysisException
	 */
	public static Condition parse(String op1,String op2,String op3,LexicalParser lp) throws LexicalAnalysisException{
		
		int i=0;
		Condition tmp;
		do{
			tmp=Conditions[i].parse(op1, op2, op3, lp);
			i++;
		}while(tmp==null&&i<Conditions.length);
		
		if (tmp==null)throw new LexicalAnalysisException();
		return tmp;
	}
}
