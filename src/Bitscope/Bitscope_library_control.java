package Bitscope;
import Serial.Comport_interface;
import Registers.Bitscope_registers;


public class Bitscope_library_control {
	Comport_interface comPort;
	Bitscope_debugging debugger;
	Bitscope_control control;
	Bitscope_registers registers;
	
	
	public Bitscope_library_control(String port, int baudrate){
		//Environment setup
		comPort = new Comport_interface(port, baudrate);
		comPort.open_comPort();
		
		registers = new Bitscope_registers();
		
		//system setup
		debugger = new Bitscope_debugging(comPort, registers);
		control = new Bitscope_control(comPort, registers);
		control.reset_bitscope();
	}
	
	public void close_comport(){
		comPort.close_comport();
	}

	public Comport_interface getComport() {
		return comPort;
	}

	public Bitscope_debugging getDebugger() {
		return debugger;
	}

	public Bitscope_control getControl() {
		return control;
	}

	public Bitscope_registers getRegisters() {
		return registers;
	}

}
