package Bitscope;

import Bitscope.Bitscope_library_control;

public class Bitscope {
	Bitscope_debugging debugger;
	Bitscope_scope scope;
	Bitscope_analog_function_generator awg;
	Bitscope_library_control bitscope_libary_control;

	public Bitscope(String port, int baudRate) {
		bitscope_libary_control = new Bitscope_library_control(port, baudRate);
		debugger = bitscope_libary_control.getDebugger();
		scope = new Bitscope_scope(bitscope_libary_control);
		awg = new Bitscope_analog_function_generator(bitscope_libary_control);
	}

	public void Close_bitscope() {
		bitscope_libary_control.close_comport();
	}

	public Bitscope_scope get_scope(){
		return this.scope;
	}
	
	public Bitscope_analog_function_generator get_analog_function_generator(){
		return this.awg;
	}
}
