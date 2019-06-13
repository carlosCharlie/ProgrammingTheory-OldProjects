package Instructions.Conditions;

import ByteCode.ByteCode;
import ByteCode.ConditionalJumps;
import Exceptions.ArrayException;
import Instructions.LexicalParser;
import Instructions.Assignment.Term;

public abstract class Condition {

	protected Term term1;
	protected Term term2;
	protected ConditionalJumps condition;
	/**
	 * devuelve la condicion a partir  de strings
	 * @param t1 primer termino
	 * @param op condicion
	 * @param t2 segundo termino
	 * @param parser lexicalparser
	 * @return condicion o null si no corresponde
	 */
	abstract public Condition parse(String t1, String op, String t2, LexicalParser parser);
	
	/**
	 * transforma la condicion en bytecodes
	 * @param compiler compilador al que añadir los bytecodes
	 * @throws ArrayException
	 */
	abstract public void compile(Main.Compiler compiler) throws ArrayException;
	
	public void setJump(int i) {
		this.condition.setJump(i);
		
	}
}
