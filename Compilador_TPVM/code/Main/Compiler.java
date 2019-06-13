package Main;

import ByteCode.ByteCode;
import ByteCode.ByteCodeProgram;
import Exceptions.ArrayException;
import Instructions.Instruction;
import Instructions.ParsedProgram;

public class Compiler {
	private static final int MAX_varTable = 30;
	private ByteCodeProgram bytecode;
	private String[ ] varTable;
	private int numVars;
	
	public Compiler()
	{
		this.numVars=0;
		this.bytecode=new ByteCodeProgram();
		this.varTable=new String[MAX_varTable];
	}
	/**
	 * Analiza un ParsedProgram y lo acumula en forma de ByteCodeProgram
	 * @param pProgram
	 * @throws ArrayException
	 */
	public void compile(ParsedProgram pProgram) throws ArrayException {
		
		int i=0;
		
		try{
			while (i < pProgram.getCounter()) {
				
				Instruction instr = pProgram.getInstruction(i); 
				instr.compile(this); 
				i++; 
				
			}
		}
		catch (ArrayException e){
	    	throw new ArrayException("Excepcion en la generacion del bytecode: Se excede la capacidad máxima del array");
		}
	
	}
	
	
	/**
	 * Devuelve el indice de una variable
	 * @param letra variable
	 * @param canWrite si no existe la variable y esta a true, crea una nueva
	 * @return indice de la variable
	 * @throws ArrayException
	 */
	public int getIndex(String letra,boolean canWrite) throws ArrayException
	{
		int i=0;
		boolean encontrado=false;
		
		while(!encontrado && i<this.numVars)
			{
			 encontrado=varTable[i].equals(letra);
			i++;
			};
			
		if(!encontrado){
			
			
			if(canWrite){
			
				if(this.numVars>=this.varTable.length)throw new ArrayException("no se pueden declarar mas de " +this.varTable.length +" variables");
				
				else{
					this.varTable[this.numVars]=letra;
					this.numVars++;
					return this.numVars-1;
				}

			}else
				throw new ArrayException("la variable '"+letra+"' no esta inicializada");
		}else return i-1;
	}

	/**
	 * añade un bytecode al final del array de bytecodes
	 * @param bytecode bytecode a introducir
	 * @throws ArrayException
	 */
	public void addByteCode(ByteCode bytecode) throws ArrayException {
		this.bytecode.setprogram(bytecode);
		
	}
	
	/**
	 * devuelve el ByteCodeProgram
	 * @return ByteCodeProgram
	 */
	public ByteCodeProgram getBytecodeProgram()
	{
		return this.bytecode;
	}

	/**
	 * devuelve el tamaño del ByteCodeProgram
	 * @return tamaño del ByteCodeProgram
	 */
	public int getSizeBytecodeProgram()
	{
		return this.bytecode.getcontador();
	}
	
}
