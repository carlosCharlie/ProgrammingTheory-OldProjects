package Command;

import Main.Engine;

public class Help implements Command {
	
	
	public void execute(Engine engine){
		
		 CommandParser.showHelp();
	}


//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public Command parse(String[] s){
		
		if (s.length == 1 && s[0].equalsIgnoreCase("help")) 
			return new Help();
		
		else return null;
	}


//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public String textHelp() {
		
		return "HELP: Que muestra información sobre los distintos comandos disponibles." + System.getProperty("line.separator");
	}
	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------

	public String toString(){
		
		return "Help";
	}
	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------

}
