
package Command;

import Exceptions.ArrayException;
import Exceptions.BadFormatByteCode;
import Main.Engine;

public class AddByteCodeProgram implements Command {

	
	public void execute(Engine engine)throws BadFormatByteCode, ArrayException{
		
		 engine.readByteCodeProgram();
	}


//--------------------------------------------------------------------------------------------------------------------------------------------------------------


	public Command parse(String[] s){
		
		if(s.length != 1 || !s[0].equalsIgnoreCase("bytecode"))
			return null;
		
		else return (new AddByteCodeProgram());
	}


//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	public String textHelp(){
		
		return "BYTECODE: Introduce la instrucción bytecode BC al programa actual. Si BC no" +
				"está correctamente escrito, entonces manda un mensaje de error y no lleva a cabo la" + 
				"insercción." + System.getProperty("line.separator");
	}
	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public String toString(){
		
		return "ByteCode";
	}


//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
}
