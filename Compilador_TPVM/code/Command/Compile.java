
package Command;

import Exceptions.ArrayException;
import Exceptions.LexicalAnalysisException;
import Main.Engine;

public  class Compile implements Command {

	
	public void execute(Engine engine) throws LexicalAnalysisException, ArrayException {
		
			engine.compile();
	}

	
	public Command parse(String[] s) {
		if(s.length==1 && s[0].equalsIgnoreCase("compile"))return new Compile();
		else return null;
	}

	
	public String textHelp() {
		
		return "COMPILE: realiza el análisis léxico del programa fuente, generando un nuevo programa parseado y, posteriormente a partir del programa parseado genera un programa bytecode." + System.getProperty("line.separator");
	}

	
	public String toString(){
		return "Compile";
	}
	
}