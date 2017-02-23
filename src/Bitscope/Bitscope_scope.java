package Bitscope;

import Bitscope.Bitscope_library_control;
import Registers.Bitscope_registers;

public class Bitscope_scope {
	Bitscope_control control;
	Bitscope_registers registers;

	public Bitscope_scope(Bitscope_library_control library_control) {
		control = library_control.getControl();
		registers = library_control.getRegisters();

		// general
		registers.load_dump_size(1028);
		registers.load_value_in_Dump_mode(0x00);
		registers.load_in_spock_option_address(0x00);
		registers.load_value_in_Trace_mode(0x00);
		registers.load_post_trigger_capture(1028);
		registers.load_value_in_Dump_channel(0x00);
		registers.load_value_in_Buffer_mode(0x00);
		registers.load_value_in_Analog_channel_enable(0x01);
		registers.load_pre_trigger_capture(1);
		registers.load_trigger_checking_delay_period(0);
		registers.load_timeout(0x0a00);
		registers.load_time_base_clock_ticks(40);
		registers.load_clock_scale(1);

		// triggers
		registers.load_value_in_Trigger_mask(0x7f);
		registers.load_value_in_Trigger_logic(0x80);
		registers.load_trigger_intro(4);
		registers.load_trigger_outro(4);
		registers.load_trigger_value(0);
		registers.load_Trigger_level(32000);

		// range and span
		registers.load_Lower_voltage_range(0x0000);
		registers.load_Higher_voltage_range(0xffff);
	}

	public void set_channel(Boolean channelA_not_channelB) {
		if (channelA_not_channelB) {
			registers.load_value_in_Analog_channel_enable(0x01);
			registers.getSpock_option().set_bit(2);
		} else {
			registers.load_value_in_Analog_channel_enable(0x02);
			registers.getSpock_option().unset_bit(2);
		}
	}

	public void adjust_voltage_range() {

	}

	public void adjust_timebase() {

	}

	public float[] get_view_in_voltages() {
		return null;
	}
}
