package ByteCode;

abstract public class ByteCodeOneParameter extends ByteCode{
	
	protected int parametro;
	/**
	 * constructor para los bytecodes de un parametro
	 * @param param parametro
	 */
	ByteCodeOneParameter(int param){
		
		super();
		this.parametro=param;
	}
	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public ByteCodeOneParameter(){
	}

	public ByteCode parse(String[] s){
		
		if(s.length!=2)
			return null;
		
		else{
			
			try{
			int tmp=Integer.parseInt(s[1]);
			return parse(s[0],tmp);
			}catch (NumberFormatException e)
			{
				return null;
			}
		}
	}
	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * transforma un string y un parametro en un bytecode
	 * @param nombre nombre del bytecode
	 * @param i parametro
	 * @return bytecode con ese nombre y parametro o null si ocurre un error
	 */
	abstract public ByteCode parse(String nombre,int i);
	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
}
