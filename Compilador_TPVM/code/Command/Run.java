package Command;

import Exceptions.ArrayException;
import Exceptions.DivisionByZero;
import Exceptions.ExecutionErrorException;
import Exceptions.StackException;
import Main.Engine;

public class Run implements Command {

	
	public void execute(Engine engine) throws ArrayException,ExecutionErrorException{
		
		
			engine.run();	
		 
	}


	//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public Command parse(String[] s){
		
		if (s.length == 1 && s[0].equalsIgnoreCase("RUN"))
			return new Run();
		
		else return null;
	}
	

	//--------------------------------------------------------------------------------------------------------------------------------------------------------------

	public String textHelp() {
		
		return "RUN: Ejecuta el programa actual. En caso de que se produzca "
				+ "un error de ejecución, avisa al usuario mediante un mensaje. " +
				System.getProperty("line.separator");
	}

	//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	public String toString(){
		
		return "Run";
	}
	

	//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
}
