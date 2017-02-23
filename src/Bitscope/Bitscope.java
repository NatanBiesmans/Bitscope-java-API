package Bitscope;
import Bitscope.Bitscope_library_control;

public class Bitscope {
	Bitscope_debugging debugger;
	Bitscope_scope scope;
	
	
	public Bitscope(String port, int baudRate) {
		System.out.println("Initialising library");
		
		Bitscope_library_control bitscope_libary_control = new Bitscope_library_control(port, baudRate);
		debugger = bitscope_libary_control.getDebugger();
		scope = new Bitscope_scope(bitscope_libary_control);
	}
	
	
}
