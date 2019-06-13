package Command;

public class CommandParser {
		 	
	
	 	private final static Command[] commands = {
	 			new Help(), new Quit(),
	 			new Replace(), new Run(), new LoadFich() ,new Compile()};
	 	
	 	

 //--------------------------------------------------------------------------------------------------------------------------------------------------------------
 	
 	/**
 	 * entra un string y crea un objeto Command
 	 * @param line string del comando
 	 * @return commando con ese nombre o null si esta mal
 	 */
 	 public static Command parse(String line){

 		 Command comando;
 		 
 		 line=line.toUpperCase();
 		 String[] commandCadena = line.split(" ");
	
	 		 int i = 0;
	 		 do{
		 			comando = commands[i];
		 			comando = comando.parse(commandCadena);
		 			i++;
		 			
	 		 } while(comando == null && i < commands.length);
	 		 
	 		 return comando;
	 	 }
	 	 
 	 
 //--------------------------------------------------------------------------------------------------------------------------------------------------------------
 	 
 	 /**
 	  * Devuelve el comando de la posicion llegada desde el paramentro, si no es correcta, devuelve null.
 	  * @param i
 	  * @return un comando
 	  */
 	 public static Command getCommands (int i){
 		 
 		 if(i < commands.length)
 			 return commands[i];
 		 else
 			 return null;
 	 }
 	 

 //--------------------------------------------------------------------------------------------------------------------------------------------------------------
 	 
 	 /**
 	  * Imprime todos la informacion llegada de los textHelp de los comandos.
 	  */
 	 public static void showHelp (){
 		 
 		 for (int i = 0; i < CommandParser.commands.length; i++){
 			 
 			 System.out.println(CommandParser.commands[i].textHelp());
 		 }
 		 
 	 }
 	 
 	 
 	 
 	 
 //--------------------------------------------------------------------------------------------------------------------------------------------------------------

}
	

