package ByteCode;

public class IfEq extends ConditionalJumps{
	
	/**
	 * Constructura de la clase IfEq
	 * @param numero
	 */
	public IfEq(int numero){
		
		super(numero);
	}
	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Constructura de la clase IfEq
	 */
	public IfEq(){
		
		super();
	}
	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public boolean execute(int n1, int n2){
		
		return (!(n1 == n2));
	}


//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public ByteCode parse(String nombre, int i){
		
		if(nombre.equals("IFEQ"))
			return new IfEq(i);
		
		else return null;
	}


//--------------------------------------------------------------------------------------------------------------------------------------------------------------

	public String toString(){
		
		return "IfEq " + this.parametro;
	}
	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
}