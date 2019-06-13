package Command;

import java.io.FileNotFoundException;

import Exceptions.ArrayException;
import Main.Engine;

public  class LoadFich implements Command {

	private String ruta;
	
	public LoadFich(String ruta)
	{
		this.ruta=ruta;
	}
	
	public LoadFich() {
		
	}

	public void execute(Engine engine) throws FileNotFoundException, ArrayException {
		
		engine.loadFich(this.ruta);
		
	}

	
	
	public Command parse(String[] s) {
		
		//comprobar ruta 
		
		if(s.length==2 && s[0].equals("LOAD"))return new LoadFich(s[1]);
		else return null;
	}

	
	public String textHelp() {
		
		return "LOAD FICH: carga el fichero de nombre FICH como programa fuente. No realiza ningún tipo de comprobación sintáctica." + System.getProperty("line.separator");
	}
	
	public String toString(){
		return "Load " + this.ruta;
	}
	

	
	

}
