package Bitscope_Machines;

import java.util.Arrays;

import Bitscope.Bitscope_control;
import Bitscope.Bitscope_library_control;
import Registers.Bitscope_registers;

public class Bitscope_logic_analyzer {
	Bitscope_control control;
	Bitscope_registers registers;

	private int trace_size = 12000;
	private int rate = 1;
	private int trigger_mode = 1;
	private double frequency_in_khz = 5000;
	private boolean[] logic = { false, false, false, false, false, false, false, false };
	private boolean[] mask = { false, false, false, false, false, false, false, false };

	public Bitscope_logic_analyzer(Bitscope_library_control library_control) {
		control = library_control.getControl();
		registers = library_control.getRegisters();
	}

	public void enable_logic_analyzer() {
		initialise_logic_analyzer_registers();

	}

	public void enable_channel(int channel) {
		mask[channel] = false;
	}

	public void disable_channel(int channel) {
		mask[channel] = true;
	}

	public void set_trigger(int channel, boolean trigger) {
		logic[channel] = trigger;
	}

	public void set_frequency(double frequency_in_khz) {
		this.frequency_in_khz = frequency_in_khz;
	}

	public void set_trace_size(int trace_size) {
		this.trace_size = trace_size;
	}

	public void set_trigger_direction(boolean tf_not_ft) {
		if (tf_not_ft) {
			trigger_mode = 0;
		} else {
			trigger_mode = 1;
		}
	}

	public void initiate_logic_analyzer() {
		initialise_logic_analyzer_registers();
		control.update_logic_analyzer_registers_on_bitscope();
	}

	public byte[] get_logic_analyzer_trace() {

		control.program_spock_registers_operation();
		int end_addres = get_end_address_after_trace(control.delay_until_trigger_operation());
		registers.load_in_counter_capture_address(end_addres + 12288 - this.trace_size);
		control.program_spock_registers_operation();

		byte[] trace = control.analog_memory_dump_operation();
		return process_trace(trace);
	}

	private int frequency_to_ticks(double frequency) {
		return (int) (1000000 / (frequency * 25));
	}

	private void initialise_logic_analyzer_registers() {
		registers.load_value_in_Trace_mode(0x0e);
		registers.load_value_in_Dump_mode(0x00);
		registers.load_dump_size(this.trace_size);
		registers.load_value_in_Buffer_mode(0x00);
		registers.load_clock_scale(0x0001);
		registers.load_post_trigger_capture(this.trace_size);
		registers.load_value_in_Dump_channel(0x80);
		registers.load_value_in_Analog_channel_enable(0x00);
		registers.load_digital_channel(0x00);
		registers.load_dump_send(0x00);
		registers.load_dump_skip(0x00);

		registers.load_timeout(0x0000);
		registers.load_value_in_Spock_option(this.trigger_mode);
		registers.load_trigger_intro(0x0001);
		registers.load_trigger_outro(0x0001);

		for (int channel = 0; channel < logic.length; channel++) {
			if (logic[channel]) {
				registers.set_trigger_logic_bit(channel);
			} else {
				registers.unset_trigger_logic_bit(channel);
			}
			if (mask[channel]) {
				registers.set_trigger_mask_bit(channel);
			} else {
				registers.unset_trigger_mask_bit(channel);
			}
		}
		registers.load_time_base_clock_ticks(frequency_to_ticks(this.frequency_in_khz));
	}

	private int get_end_address_after_trace(String returned_string) {
		String end_address_strings = returned_string.substring(returned_string.length() - 6);
		return Integer.parseInt(end_address_strings, 16);
	}
	
	private byte[] process_trace(byte[] trace) {
		byte[] processed_trace = null;
		processed_trace = Arrays.copyOfRange(trace, 1, trace.length - 1);
		return processed_trace;
	}

}
