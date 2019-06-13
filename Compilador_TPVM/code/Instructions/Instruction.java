package Instructions;

import Exceptions.ArrayException;
import Exceptions.LexicalAnalysisException;

public interface Instruction {
	
	/**
	 * Genera el programa parseado a partir del programa fuente.
	 * @param words
	 * @param lexparser
	 * @return devuelve el programa parseado.
	 * @throws LexicalAnalysisException
	 * @throws ArrayException
	 */
	Instruction lexParse(String[] words, LexicalParser lexparser)throws LexicalAnalysisException, ArrayException;
	
	/**
	 * Se encarga de generar los Bytecodes de las instrucciones.
	 * @param compiler
	 * @throws ArrayException
	 */
	void compile(Main.Compiler compiler) throws ArrayException;

}
