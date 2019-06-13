package ByteCode;

import CPU.CPU;
import Exceptions.StackException;

public class Out extends ByteCode {

	@Override
	public boolean execute(CPU cpu) throws StackException {
		return cpu.out();
	}

	@Override
	public ByteCode parse(String[] s) {
		if(s.length==1&&s[0].equalsIgnoreCase("out"))return new Out();
		else return null;
	}
	
	public String toString()
	{
		return "Out";
	}

}
