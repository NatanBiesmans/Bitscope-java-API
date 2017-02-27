package Bitscope_Machines;

import Registers.Bitscope_registers;
import Serial.Comport_interface;

public class Bitscope_debugging {
	Comport_interface comPort;
	Bitscope_registers registers;

	public Bitscope_debugging(Comport_interface comPort, Bitscope_registers registers) {
		this.comPort = comPort;
		this.registers = registers;
	}
}
