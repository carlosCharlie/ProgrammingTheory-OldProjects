package Command;

import Exceptions.ArrayException;
import Main.Engine;

public class Replace implements Command {

	private int numero;
	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	Replace(int num){
		
		super();
		this.numero=num;
	}
	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public Replace(){
		
		super();
	}

	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public void execute(Engine engine) throws ArrayException {
		
		 engine.replace(this.numero);
	}


//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public Command parse(String[] s){
		
		if(!s[0].equalsIgnoreCase("replacebc") || s.length != 2)
			return null;
		
		else{
			try{
			int tmp = Integer.parseInt(s[1]);
			return new Replace(tmp);
			}catch (NumberFormatException e)
			{
				return null;
			}
		}
	}


//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public String textHelp() {

		return "REPLACEBC N: solicita al usuario una nueva instrucción bytecode y reemplaza la línea N del programa bytecode por la nueva instrucción introducida por el usuario." + System.getProperty("line.separator");
	}


//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public String toString(){
		
		return "replacebc " + this.numero;
	}
	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
}
