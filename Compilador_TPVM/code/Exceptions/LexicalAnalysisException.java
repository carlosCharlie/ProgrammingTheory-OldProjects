package Exceptions;

public class LexicalAnalysisException extends Exception {

	public LexicalAnalysisException (){
		
		super("Hay un error de sintaxis en el codigo del archivo");
	}

	public LexicalAnalysisException(String s) {
		super(s);
	}
	
}
