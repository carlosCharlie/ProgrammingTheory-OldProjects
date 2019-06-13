package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import ByteCode.ByteCode;
import ByteCode.ByteCodeParser;
import ByteCode.ByteCodeProgram;
import CPU.CPU;
import Command.Command;
import Command.CommandParser;
import Exceptions.ArrayException;
import Exceptions.BadFormatByteCode;
import Exceptions.DivisionByZero;
import Exceptions.ExecutionErrorException;
import Exceptions.LexicalAnalysisException;
import Exceptions.StackException;
import Instructions.LexicalParser;
import Instructions.ParsedProgram;


public class Engine{
	
	private boolean end;
	private CPU cpu;
	private ByteCodeProgram program;
	private SourceProgram sProgram;
	private ParsedProgram parsedProgram;
	
	
	/**
	 * Inicializa las variables de la clase Engine.
	 */
	public Engine(){
	
		this.end = false;
		this.cpu= new CPU(this.program);
		this.program=new ByteCodeProgram();
		this.sProgram=new SourceProgram();
		this.parsedProgram=new ParsedProgram();
	}
	
//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Arranca el engine,controla todos los comandos
	 */
	public void start(){
		
		 Scanner sc = new Scanner(System.in); 
		 do{
			 Command comando;
			 System.out.print("> ");
			 String comstring = sc.nextLine();
			 comando = CommandParser.parse(comstring);
			 
			 if(comando == null) System.out.println("El comando no existe. Vuelva a introducir:" );
			 
			 else {

				 System.out.println("Comienza la ejecucion de " + comando);
				 
				 
				 try {
					 
					comando.execute(this);
					if (comstring.equalsIgnoreCase("run")){
						 
						 System.out.println("El estado de la maquina tras ejecutar programa es:");
						 System.out.println(this.cpu);
					 }
					if (!comstring.equalsIgnoreCase("quit")){
						System.out.println(this.sProgram);
						if(this.program.getcontador()!=0)System.out.println(this.program);
					}
					
				}
				catch (LexicalAnalysisException e){
					
					System.out.println(e.getMessage());
					reset();
					
				}
				catch (ArrayException e){
					
					System.out.println(e.getMessage());
					reset();
					
				} catch (BadFormatByteCode n)
				{
					System.out.println(n.getMessage());
					reset();
					
				} catch (FileNotFoundException n)
				 {
					System.out.println(n.getMessage());
					reset();
					
					 
				 }catch (ExecutionErrorException e){
					 System.out.println(e.getMessage());
					 reset();
				 }
				 
	
			 }//else	 
				 
		 }while(!this.end);		 
			 
		 System.out.println("El programa ha finalizado.");
	 }

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Imprime el comando introducido por el usuario.
	 * 
	 * @param comstring es el comando introducido por el usuario.
	 * @return Variable pasada como parametro.
	 */
	public String toString(String comstring){
		
		return comstring;
	}
	
//---------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Cuando el comando intriducido es QUIT, end debe estar a true para que finalice la ejecucion del programa.
	 * @return true
	 */
	public void Quit(){
		
		 this.end = true;
	}

//----------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Se resetea el ByteCodeProgram, SourceProgram y ParsedProgram.
	 */
	
	private void reset(){
		
		this.program=new ByteCodeProgram();
		this.sProgram=new SourceProgram();
		this.parsedProgram=new ParsedProgram();
	}

//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Comprueba que la nueva instrucción introducida en replace es correcta y la sustituye en el programa.
	 * @param numero posicion de la instruccion a reemplazar
	 * @return true si lo ha introducido correctamente.
	 * @throws ArrayException 
	 */
	public void replace(int numero) throws ArrayException{
		
		 ByteCode newInstruction;
		 String comstring;
		 if(this.program.getcontador()==0)throw new ArrayException("No se puede reemplazar porque todavia no se ha generado ningun bytecode");
		 if(numero<0 || numero>=this.program.getcontador())throw new ArrayException("No se puede reemplazar el bytecode en la posicion "+numero);
		 Scanner sc =new Scanner(System.in);
		 System.out.println("Introduce la nueva instruccion: ");
		try{
		 comstring = sc.nextLine();
		 newInstruction = ByteCodeParser.parse(comstring);
		 
		 while(newInstruction == null){
			 
			 System.out.println("Error al introducir la instruccion, introduce la nueva instruccion: ");
			 comstring = sc.nextLine();
			 newInstruction = ByteCodeParser.parse(comstring);
		 }
		 
		 this.program.setprogram(newInstruction, numero);
		}catch (BadFormatByteCode n)
		{
			System.out.println(n.getMessage());
		}
	}

//------------------------------------------------------------------------------------------------------------------------------------------------------------

	
	/**
	 * carga el programa de cpu y lo rellena con bytecodes nuevos
	 * @return siempre true
	 * @throws ArrayException 
	 */
	
	public void readByteCodeProgram()throws BadFormatByteCode, ArrayException{

		cargarByteCodeProgram();
	
		ByteCode tmp;
			
		Scanner s=new Scanner(System.in);
		String leer;
		
		System.out.println("Introduce el bytecode. Una instrucción por linea:" + System.getProperty("line.separator"));
	
		do{
			leer= s.nextLine();
			if(this.program.isFull()){
				System.out.println("No se pueden almacenar mas programas." + System.getProperty("line.separator"));
			}
			else{
				
				tmp = ByteCodeParser.parse(leer);
				
				while(!leer.equalsIgnoreCase("end") && tmp == null){
				
					System.out.println("Bytecode incorrecto. Vuelva a introducir:" + System.getProperty("line.separator"));
					leer= s.nextLine();
					tmp = ByteCodeParser.parse(leer);
		
				}
			
				if (!leer.equalsIgnoreCase("end"))
					this.program.setprogram(tmp);
			}
			
		} while(!this.program.isFull() && !leer.equalsIgnoreCase("end"));

		guardarByteCodeProgram();
	
	
	}
	

	
//-------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * carga el programa de cpu a engine
	 */
	private void cargarByteCodeProgram(){
		
		if (this.cpu.getBcProgram()!=null)this.program = this.cpu.getBcProgram();
	}
	
//-------------------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * guarda el programa de engine en cpu
	 */
	private void guardarByteCodeProgram(){
		
		this.cpu.setBcProgram(this.program);
	}
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Bucle que ejecuta todos los bytecodes almacenados en el programa y los va mostrando por pantalla.
	 * @return Si la ejecucion del programa ha sido correcta, devuelve true.
	 * @throws ArrayException 
	 * @throws ExecutionErrorException 
	 * @throws StackException 
	 */
	public boolean run() throws ArrayException, ExecutionErrorException{
		
		boolean tmp;
		
		this.cpu.reset();
		if(this.program.getcontador()==0)throw new ArrayException("No hay programa compilado para ejecutar");
		this.cpu.setBcProgram(this.program);
		try{
			tmp = this.cpu.run();
		}
		catch (DivisionByZero s){
			
			throw new DivisionByZero();
		}
		
		return tmp;
	}

//------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * carga de fichero
	 * @param ruta ruta del fichero
	 * @throws FileNotFoundException
	 * @throws ArrayException
	 */
	public void loadFich(String ruta) throws FileNotFoundException, ArrayException {
		
		try{
			
			reset();
			
		File archivo = new File(ruta);
		
		Scanner sc;
		
			sc = new Scanner(archivo);
			while(sc.hasNextLine())
			{
			this.sProgram.pushSourceProgram(sc.nextLine());	
			}
			}catch (FileNotFoundException n)
			{
				throw new FileNotFoundException("No se ha encontrado el archivo");
			}
		
	}

//------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * transforma el programa fuente en un ByteCodeProgram
	 * @throws LexicalAnalysisException
	 * @throws ArrayException
	 */
	public void compile() throws LexicalAnalysisException, ArrayException {
		
 

			
			this.lexicalAnalysis();
			this.generateByteCode();

	}
	//------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * transforma el programa fuente en un ParsedProgram
	 * @throws LexicalAnalysisException
	 * @throws ArrayException
	 */
	private void lexicalAnalysis()throws LexicalAnalysisException, ArrayException{
		
		if(this.sProgram.getContador()==0)throw new LexicalAnalysisException("No hay codigo para compilar");
		
		else if(!this.sProgram.getLine(this.sProgram.getContador()-1).trim().equals("end"))throw new LexicalAnalysisException("La ultima linea del programa debe ser 'end'");
		
		else{
		LexicalParser lexicalParser=new LexicalParser(this.sProgram,0);		
		lexicalParser.lexicalParser(this.parsedProgram, "end");
		}
	}
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * transforma el parsedProgram en un ByteCodeProgram
	 * @throws ArrayException
	 */
	private void generateByteCode() throws ArrayException{
		
		Compiler compiler=new Compiler();
		compiler.compile(this.parsedProgram);
		
		this.program=compiler.getBytecodeProgram();
		
	}
	
	
	
	
//---------------------------------------------------------------------------------------------------------------------------------------------------------------	

}//ENGINE
