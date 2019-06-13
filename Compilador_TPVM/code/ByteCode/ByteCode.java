package ByteCode;

import CPU.CPU;
import Exceptions.ArrayException;
import Exceptions.DivisionByZero;
import Exceptions.ExecutionErrorException;
import Exceptions.StackException;

abstract public class ByteCode {

	/**
	 * ejecuta el bytecode
	 * @param cpu cpu donde se ejecutará
	 * @return true si se ha ejecutado bien o false si no
	 * @throws DivisionByZero 
	 * @throws ArrayException 
	 * @throws StackException 
	 * @throws ExecutionErrorException 
	 */
	abstract public boolean execute(CPU cpu) throws DivisionByZero, ArrayException, ExecutionErrorException;
	
	/**
	 * transforma una cadena de strings en un bytecode
	 * @param s cadena de strings
	 * @return bytecode con esa cadena o null si se ha producido un error
	 */
	abstract public ByteCode parse(String[ ] s);

}