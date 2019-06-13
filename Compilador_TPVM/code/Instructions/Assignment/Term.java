package Instructions.Assignment;

import ByteCode.ByteCode;
import Exceptions.ArrayException;

public interface Term {

	/*
	 * Parsea el termino del parametro.
	 */
	Term parse(String term); 
	
	/**
	 * Convierte en Bytecode
	 * @param compiler
	 * @return
	 * @throws ArrayException
	 */
	ByteCode compile(Main.Compiler compiler) throws ArrayException;
}
