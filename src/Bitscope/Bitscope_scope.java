package Bitscope;

import java.util.Arrays;

import Bitscope.Bitscope_library_control;
import Registers.Bitscope_registers;

public class Bitscope_scope {
	Bitscope_control control;
	Bitscope_registers registers;

	double higher_range_voltage;
	double lower_range_voltage;
	double trigger_voltage;

	int min_timebase = 15;
	int max_timebase = 535;
	int timebase_value = 40;

	int end_address = 0;

	public Bitscope_scope(Bitscope_library_control library_control) {
		control = library_control.getControl();
		registers = library_control.getRegisters();

		// general
		registers.load_dump_size(1028);
		registers.load_value_in_Dump_mode(0x00);
		registers.load_in_counter_capture_address(0x00);
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
		registers.load_iterations(1);

		// triggers
		registers.load_value_in_Trigger_mask(0x7f);
		registers.load_value_in_Trigger_logic(0x80);
		registers.load_trigger_intro(4);
		registers.load_trigger_outro(4);
		registers.load_trigger_value(0);
		registers.load_Trigger_level(32000);

		// range and span
		registers.load_Lower_voltage_range(0x5c2a);
		registers.load_Higher_voltage_range(0x9f81);

		control.update_registers_on_bitscope();
		control.update_registers_command();
	}

	public void set_channel(Boolean channelA_not_channelB) {
		if (channelA_not_channelB) {
			registers.load_value_in_Analog_channel_enable(0x01);
			registers.getSpock_option().set_bit(2);
		} else {
			registers.load_value_in_Analog_channel_enable(0x02);
			registers.getSpock_option().unset_bit(2);
		}

		control.update_registers_on_bitscope();
	}

	public void set_voltage_range(double high_peak_voltage, double low_peak_voltage) {
		higher_range_voltage = high_peak_voltage;

		double scale = high_peak_voltage - low_peak_voltage;
		double offset = low_peak_voltage + (scale / 2);

		int[] voltage_registers_values = to_span(offset, scale);
		registers.load_Higher_voltage_range(voltage_registers_values[0]);
		registers.load_Lower_voltage_range(voltage_registers_values[1]);

		control.update_registers_on_bitscope();
		control.update_registers_command();
	}

	public void set_trigger(double trigger_voltage) {
		this.trigger_voltage = trigger_voltage;
		registers.load_Trigger_level(to_range_as_integer(this.trigger_voltage, -7.157, 10.816, 0, 65535));

		control.update_registers_on_bitscope();
		control.update_registers_command();
	}

	public void set_timebase(double new_timebase_in_milliseconds) {

		new_timebase_in_milliseconds = ensure_range(new_timebase_in_milliseconds, 0.385, 8);
		new_timebase_in_milliseconds /= 1024 * 25 * 0.000001;
		this.timebase_value = (int) new_timebase_in_milliseconds;

		registers.load_time_base_clock_ticks(this.timebase_value);

		control.update_registers_on_bitscope();
		control.update_registers_command();
	}

	public double[] get_view_in_voltages() {
		initialise_request();

		return process_data(acquire_data());
	}

	// private acquisition functions

	private void initialise_request() {
		control.program_spock_registers_operation();
		end_address = get_end_address_after_trace(control.delay_until_trigger_operation());
	}

	private byte[] acquire_data() {
		registers.load_in_counter_capture_address(((end_address + (3 * 4096)) - 1028) % (3 * 4096));
		control.update_registers_on_bitscope();
		control.update_registers_command();
		return control.analog_memory_dump_operation();
	}

	private double[] process_data(byte[] acquired_data) {
		byte[] acquired_trace = process_trace(acquired_data);
		double[] processed_trace_in_voltages = new double[acquired_trace.length];

		int sample_index = 0;
		for (byte sample : acquired_trace) {
			processed_trace_in_voltages[sample_index] = to_range_as_double((double) (sample & 0xff) - 127, 0, 255,
					lower_range_voltage * 2, higher_range_voltage * 2);
			sample_index++;
		}

		return processed_trace_in_voltages;

	}

	private byte[] process_trace(byte[] trace) {
		byte[] processed_trace = null;
		processed_trace = Arrays.copyOfRange(trace, 1, trace.length - 1);
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
		double normalised_offset = 0.41;

		double high_ad_ref_voltage = (normalised_offset - offset / normalised_range) + (scale / normalised_range) / 2;
		double low_ad_ref_voltage = (normalised_offset - offset / normalised_range) - (scale / normalised_range) / 2;

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

}
