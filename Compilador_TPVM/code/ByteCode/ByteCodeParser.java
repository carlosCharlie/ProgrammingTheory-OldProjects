package ByteCode;

import Exceptions.BadFormatByteCode;

public class ByteCodeParser {

	private final static ByteCode[] ByteCodes={new Halt(), new Add(), new Sub(), new Div(), new Mul(), new Load(), new Store(),new Push(),new IfLe(),new IfLeq(),new IfEq(),new IfNeq(),new GoTo(),new Out()};

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * transforma el string en un bytecode
	 * @param s nombre del bytecode
	 * @return bytecode  o null si esta mal
	 */
	public static ByteCode parse(String s)throws BadFormatByteCode{
		
		ByteCode byt;
		s = s.toUpperCase();
		String[] bytecodeCadena = s.split(" ");
		int i=0;
		
		do{
			byt= ByteCodes[i].parse(bytecodeCadena);
			i++;
			
		}while(byt == null && i < ByteCodes.length);
	
		if(byt==null)throw new BadFormatByteCode("Error de sintaxis en el bytecode");
		return byt;
	}
	
//--------------------------------------------------------------------------------------------------------------------------------------------------------------	
	
}
