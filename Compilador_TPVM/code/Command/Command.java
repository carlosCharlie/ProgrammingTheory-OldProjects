package Command;

import java.io.FileNotFoundException;

import Exceptions.ArrayException;
import Exceptions.BadFormatByteCode;
import Exceptions.DivisionByZero;
import Exceptions.ExecutionErrorException;
import Exceptions.LexicalAnalysisException;
import Exceptions.StackException;
import Main.Engine;

public interface Command {

	 /**
	  * ejecuta los comandos
	  * @param engine engine que se encarga de iteractuar con el usuario
	  * @return devuelve verdadero si se ha ejecutado correctamente el comando o falso si no
	 * @throws ArrayException 
	 * @throws LexicalAnalysisException 
	 * @throws BadFormatByteCode 
	 * @throws FileNotFoundException 
	 * @throws ExecutionErrorException 
	 * @throws StackException 
	  */
	abstract public void execute(Engine engine) throws LexicalAnalysisException, ArrayException, BadFormatByteCode, FileNotFoundException, DivisionByZero, ExecutionErrorException;
	
	/**
	 * Parsea un nombre con su comando correspondiente. Si no coincide ninguno, devuelve null.
	 * @param s
	 * @return devuelve un comando
	 */
	abstract public Command parse (String[] s);
	
	/**
	 * Contiene la informacion de lo que hace le comando.
	 * @return informacion del comando
	 */
	abstract public String textHelp (); 	
	 	
}
