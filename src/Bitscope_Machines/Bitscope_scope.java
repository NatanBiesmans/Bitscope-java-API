package Bitscope_Machines;

import java.util.Arrays;

import Bitscope.Bitscope_control;
import Bitscope.Bitscope_library_control;
import Registers.Bitscope_registers;

public class Bitscope_scope {
	Bitscope_control control;
	Bitscope_registers registers;

	double higher_range_voltage = 5;
	double lower_range_voltage = -5;
	double trigger_voltage = 0;
	boolean channel_a_not_b = true;
	boolean trigger_channel_a_not_b = true;
	int trace_size = 1024;

	int dump_mode = 0x00;
	int trace_mode = 0x00;
	int buffer_mode = 0x00;

	int min_timebase = 15;
	int max_timebase = 535;
	int timebase_value = 40;

	int end_address = 0;

	public Bitscope_scope(Bitscope_library_control library_control) {
		control = library_control.getControl();
		registers = library_control.getRegisters();
	}

	public double[][] get_chopped_view_in_voltages() {
		load_chopped_parameters();
		initiate_scope();
		control.delay_until_trigger_operation();
		return read_data_and_give_voltages();
	}

	public void set_channel(Boolean channelA_not_channelB) {
		this.channel_a_not_b = channelA_not_channelB;
	}

	public void set_analog_trigger_source(boolean channelA_not_channelB) {
		this.trigger_channel_a_not_b = channelA_not_channelB;
	}

	public void set_voltage_range(double high_peak_voltage, double low_peak_voltage) {
		this.higher_range_voltage = high_peak_voltage;
		this.lower_range_voltage = low_peak_voltage;

	}

	public void set_trigger(double trigger_voltage) {
		this.trigger_voltage = trigger_voltage;
		registers.load_Trigger_level(to_range_as_integer(this.trigger_voltage, -7.157, 10.816, 0, 65535));

	}

	public void set_timebase(double new_timebase_in_milliseconds) {

		new_timebase_in_milliseconds = ensure_range(new_timebase_in_milliseconds, 0.385, 12);
		new_timebase_in_milliseconds /= 1024 * 25 * 0.000001;
		this.timebase_value = (int) new_timebase_in_milliseconds;

	}

	public double[] get_view_in_voltages() {
		load_single_channel_parameters();
		initiate_scope();
		single_channel_initialise_request();
		return process_data(single_channel_acquire_data());
	}

	public int get_sampeling_frequency() {
		return 1024 / this.timebase_value;
	}

	// private acquisition functions

	private byte[] dump_channel_A() {

		return process_trace(control.request_chopped_channel_A_data());
	}

	private byte[] dump_channel_B() {

		return process_trace(control.request_chopped_channel_B_data());
	}

	private double[][] read_data_and_give_voltages() {

		double[][] processed_data = { process_data(dump_channel_A()), process_data(dump_channel_B()) };
		return processed_data;
	}

	private void load_chopped_parameters() {
		set_registers_to_chopped();
		registers.load_value_in_Analog_channel_enable(0x03);
	}

	private void set_registers_to_chopped() {
		this.trace_mode = 0x02;
		this.dump_mode = 0x00;
		this.buffer_mode = 0x01;
	}

	private void load_single_channel_parameters() {
		set_registers_to_single_channel();

		if (this.channel_a_not_b) {
			registers.load_value_in_Analog_channel_enable(0x01);
		} else {
			registers.load_value_in_Analog_channel_enable(0x02);
		}
	}

	private void set_registers_to_single_channel() {
		this.trace_mode = 0x00;
		this.dump_mode = 0x00;
		this.buffer_mode = 0x00;

	}

	private void single_channel_initialise_request() {
		control.program_spock_registers_operation();
		end_address = get_end_address_after_trace(control.delay_until_trigger_operation());
	}

	private byte[] single_channel_acquire_data() {
		registers.load_in_counter_capture_address(((end_address + (3 * 4096)) - 1028) % (3 * 4096));
		control.update_scope_registers_on_bitscope();
		control.update_registers_command();
		return control.analog_memory_dump_operation();
	}

	private double[] process_data(byte[] acquired_data) {
		byte[] acquired_trace = process_trace(acquired_data);
		double[] processed_trace_in_voltages = new double[acquired_trace.length];

		int sample_index = 0;
		for (byte sample : acquired_trace) {
			processed_trace_in_voltages[sample_index] = to_range_as_double((double) (sample & 0xff) - 127, 0, 255,
					lower_range_voltage, higher_range_voltage);
			processed_trace_in_voltages[sample_index] += (this.higher_range_voltage - this.lower_range_voltage) / 2;
			sample_index++;
		}

		return processed_trace_in_voltages;
	}

	private byte[] process_trace(byte[] trace) {
		byte[] processed_trace = null;
		processed_trace = Arrays.copyOfRange(trace, 32, trace.length - 32);
		return processed_trace;
	}

	// support function

	private int get_end_address_after_trace(String returned_string) {
		String end_address_strings = returned_string.substring(returned_string.length() - 6);

		return Integer.parseInt(end_address_strings, 16);
	}

	private int[] to_span(double offset, double scale) {

		double minimum_impedance = 16.0;
		double impedance_scaling = 12.0;
		double divider_sesitor_value = 300.0;
		double register_size = 65536;

		double normalised_range = 18.3;
		//double normalised_offset = 0.412;
		double normalised_offset = 0.41;
		
		double high_ad_ref_voltage = (normalised_offset + offset / normalised_range) + (scale / normalised_range) / 2;
		double low_ad_ref_voltage = (normalised_offset + offset / normalised_range) - (scale / normalised_range) / 2;

//		System.out.println(high_ad_ref_voltage);
//		System.out.println(low_ad_ref_voltage);
		
		
		double da_impedance_high = 2 * high_ad_ref_voltage;
		da_impedance_high = minimum_impedance + impedance_scaling * da_impedance_high * da_impedance_high;

		double da_impedance_low = 1 - 2 * low_ad_ref_voltage;
		da_impedance_low = minimum_impedance + impedance_scaling * da_impedance_low * da_impedance_low;

		double da_voltage_high = ((divider_sesitor_value + 2 * da_impedance_high) * high_ad_ref_voltage
				- da_impedance_high * (low_ad_ref_voltage + 1)) / divider_sesitor_value;
		double da_voltage_low = ((divider_sesitor_value + 2 * da_impedance_low) * low_ad_ref_voltage
				- da_impedance_low * high_ad_ref_voltage) / divider_sesitor_value;

		int register_value_high = (int) ((ensure_range(da_voltage_high, da_voltage_low, 1) * register_size)) - 1;
		int register_value_low = (int) (ensure_range(da_voltage_low, 0, da_voltage_high) * register_size);

		int[] output = { register_value_high, register_value_low };

		return output;
	}

	private double ensure_range(double value, double minimum, double maximum) {
		if (value < maximum && value > minimum) {
			return value;
		} else if (value <= minimum) {
			return minimum;
		} else if (value >= maximum) {
			return maximum;
		} else {
			return minimum; // shouldn't happen, just JAVA being careful
		}
	}

	private double to_range_as_double(double to_convert_value, double lower_limit_of_to_convert_value,
			double upper_limit_of_to_convert_value, double lower_limit_of_output_value,
			double upper_limit_of_output_value) {

		double slope_coef = (upper_limit_of_output_value - lower_limit_of_output_value)
				/ (upper_limit_of_to_convert_value - lower_limit_of_to_convert_value);
		return lower_limit_of_output_value + (slope_coef * (to_convert_value - lower_limit_of_to_convert_value));
	}

	private int to_range_as_integer(double to_convert_value, double lower_limit_of_to_convert_value,
			double upper_limit_of_to_convert_value, int lower_limit_of_output_value, int upper_limit_of_output_value) {

		double slope_coef = (double) (upper_limit_of_output_value - lower_limit_of_output_value)
				/ (upper_limit_of_to_convert_value - lower_limit_of_to_convert_value);
		return lower_limit_of_output_value + (int) (slope_coef * (to_convert_value - lower_limit_of_to_convert_value));
	}

	private void initiate_scope() {
		// general
		registers.load_dump_size(this.trace_size + 4);
		registers.load_in_counter_capture_address(0x00);
		registers.load_value_in_Trace_mode(0x00);
		registers.load_post_trigger_capture(this.trace_size + 4);
		registers.load_value_in_Buffer_mode(0x00);
		registers.load_pre_trigger_capture(1);
		registers.load_trigger_checking_delay_period(0);
		registers.load_timeout(0x0a00);
		registers.load_time_base_clock_ticks(this.timebase_value);
		registers.load_clock_scale(1);
		registers.load_iterations(1);

		// triggers
		registers.load_value_in_Trigger_mask(0x7f);
		registers.load_value_in_Trigger_logic(0x80);
		registers.load_trigger_intro(4);
		registers.load_trigger_outro(4);
		registers.load_trigger_value(0);
		registers.load_Trigger_level(32000);

		if (this.trigger_channel_a_not_b) {
			registers.getSpock_option().unset_bit(2);
		} else {
			registers.getSpock_option().set_bit(2);
		}

		// range and span
		double scale = this.higher_range_voltage - this.lower_range_voltage;
		double offset = this.lower_range_voltage + (scale / 2);

//		System.out.println(offset);
//		System.out.println(scale);
		
		int[] voltage_registers_values = to_span(offset, scale);
		
//		System.out.println(String.format("%02x",voltage_registers_values[0]));
//		System.out.println(String.format("%02x",voltage_registers_values[1]));
		
		registers.load_Higher_voltage_range(voltage_registers_values[0]);
		registers.load_Lower_voltage_range(voltage_registers_values[1]);

		// trace dependent

		registers.load_value_in_Trace_mode(this.trace_mode);
		registers.load_value_in_Dump_mode(this.dump_mode);
		registers.load_value_in_Buffer_mode(this.buffer_mode);

		// upload

		control.update_scope_registers_on_bitscope();
		control.update_registers_command();

	}

}
