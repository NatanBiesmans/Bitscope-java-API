package Bitscope;

import Registers.Bitscope_registers;
import Serial.Comport_interface;

public class Bitscope_control {
	Comport_interface comPort;
	Bitscope_registers registers;

	public Bitscope_control(Comport_interface comPort, Bitscope_registers registers) {
		this.comPort = comPort;
		this.registers = registers;
	}

	// machine control
	public void reset_bitscope() {
		comPort.send_string("!");
	}

	public String get_revision() {
		return comPort.send_string_and_wait_for_response_as_string("?");
	}

	// capture engine operations

	public void update_registers_command() {
		comPort.send_string_and_wait_for_response_as_string("U");
	}

	public void capture_spock_counter_operation() {
		comPort.send_string_and_wait_for_response_as_string("<");
	}

	public void program_spock_registers_operation() {
		comPort.send_string_and_wait_for_response_as_string(">");
	}

	public void trace_until_trigger_operation() {
		comPort.send_string_and_wait_for_response_as_string("T");
	}

	public String delay_until_trigger_operation() {
		return comPort.send_string_and_wait_for_response_as_string("D");
	}

	public void trace_logic_until_trigger_operation() {
		comPort.send_string_and_wait_for_response_as_string("T");
	}

	public void update_RAM_pointers_operation() {
		comPort.send_string_and_wait_for_response_as_string("u");
	}

	public String sample_dump_operation() {
		return comPort.send_string_and_wait_for_response_as_string("S");
	}

	public byte[] mixed_memory_dump_operation() {
		return comPort.send_string_and_wait_for_response_as_bytes("M");
	}

	public byte[] analog_memory_dump_operation() {
		return comPort.send_string_and_wait_for_response_as_bytes("A");
	}

	public void update_registers_on_bitscope() {
		comPort.send_string_and_wait_for_response_as_string(registers.create_setup_string() + ">");
	}

	// eeprom operations
	public String read_eeprom(byte address) {
		registers.getEeprom_address().load_value(address);

		return comPort.send_string_and_wait_for_response_as_string(registers.create_eeprom_read_string() + "r");
	}

	public void write_eeprom(byte address, byte value) {
		registers.getEeprom_address().load_value(address);
		registers.getEeprom_data().load_value(value);

		comPort.send_string_and_wait_for_response_as_string(registers.create_eeprom_write_string() + "w");
	}

}
