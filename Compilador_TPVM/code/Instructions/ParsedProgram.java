package Instructions;

import Exceptions.ArrayException;

public class ParsedProgram {

	private Instruction[] instruc;
	private int contador;
	public static final int Max_parsedProgram=25;
	
	public ParsedProgram()
	{
		this.contador=0;
		instruc=new Instruction[Max_parsedProgram];
	}
	
	/**
	 * Se encarga de ir generando el array de instrucciones.
	 * @param inst
	 * @throws ArrayException
	 */
	public void push(Instruction inst) throws ArrayException {
		if(contador>=this.instruc.length)throw new ArrayException("No se pueden añadir más programas en el programa parseado");
		else{
		instruc[this.contador]=inst;
		this.contador++;
		}
	}
	
	/**
	 * Devuelve el constador del programa
	 * @return contador.
	 */
	public int getCounter()
	{
	return this.contador;	
	}

	/**
	 * Devuelve la instruccion del programa en la posicion que indica el parametro.
	 * @param i
	 * @return instruccion.
	 */
	public Instruction getInstruction(int i) {
		return this.instruc[i];
	}
	
	
	public void reset() {
			
			this.contador = 0;
		}
	
	
}
