package Instructions.Assignment;

public class TermParser{

	private final static Term[] terms={new Variable(),new Number()};
	
	/**
	 * Parsea el parametro en variable o en numero.
	 * @param words
	 * @return un termino.
	 */
	public static Term parse (String words){
		
		int i=0;
		Term tmp;
		
		do{
			tmp=terms[i].parse(words);
			i++;
			
		}while(tmp == null && i < terms.length);
		
		return tmp;
	}
}
