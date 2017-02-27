package Bitscope_Machines;

import Bitscope.Bitscope_control;
import Bitscope.Bitscope_library_control;
import Registers.Bitscope_registers;

public class Bitscope_analog_function_generator {
	Bitscope_control control;
	Bitscope_registers registers;

	private int waveform = 0;
	private double symmetry = 50;
	private double frequency_in_khz = 4;
	private double peak_voltage = 3.3 / 2;
	private double offset_voltage = 3.3 / 2;
	private int awg_control = 0;

	public Bitscope_analog_function_generator(Bitscope_library_control library_control) {
		control = library_control.getControl();
		registers = library_control.getRegisters();
		disable_awg();
	}

	// awg control
	public void enable_awg() {
		this.awg_control = 0xc0;
		initiate_awg();
	}

	public void disable_awg() {
		this.awg_control = 0x00;
		initiate_awg();
	}
	
	public void reinitialise_awg(){
		initiate_awg();
	}

	// wave functions
	public void set_waveform_to_sine() {
		this.waveform = 0;
	}

	public void set_waveform_to_triangle() {
		this.waveform = 1;
	}

	public void set_waveform_to_block() {
		this.waveform = 3;
	}

	public void set_waveform_to_exponential() {
		this.waveform = 2;
	}

	public void set_voltage(double peak_voltage) {

		this.peak_voltage = peak_voltage;

	}

	public void set_offset(double offset_voltage) {
		this.offset_voltage = offset_voltage;
	}

	// wave parameters
	public void set_frequency(double signal_frequency_in_khz) {
		this.frequency_in_khz = signal_frequency_in_khz;
	}

	public void set_symmetry(double symmetry) {
		this.symmetry = symmetry;
	}

	// private functions
	private void initiate_awg() {

		registers.awg_control(this.awg_control);
		registers.clock_generator_control(0x00);
		registers.cv_operation_mode(this.waveform);
		registers.wave_phase_ratio(symmetry_percentage_to_register_value(this.symmetry));

		control.update_awg_registers();
		control.synthesize_awg();

		registers.cv_operation_mode(0x0000);
		registers.wave_size(0x03e8);
		registers.wave_index(0x0000);
		registers.wave_address(0x0000);
		registers.wave_phase_ratio(0x00083126);

		double bs_level_voltage = this.peak_voltage + this.offset_voltage;
		double bs_offset_voltage = this.offset_voltage - this.peak_voltage;
		registers.wave_offset(to_range_as_integer(bs_offset_voltage, 0, 3.3, 0, 0xffff));
		registers.wave_level(to_range_as_integer(bs_level_voltage, 0, 3.3, 0, 0xffff));

		control.update_awg_registers();
		control.translate_awg();

		registers.option(0x80f4);
		registers.modulo(0x03e8);
		registers.clock_ticks(frequency_in_khz_to_awg_clock_ticks(this.frequency_in_khz));
		registers.mark(0x010a);
		registers.space(0x0001);
		registers.dac_output(0x7f00);
		registers.cv_operation_mode(0x0200);

		control.update_awg_registers();
		control.generate_awg();
	}

	private int symmetry_percentage_to_register_value(double symmerty_percentage) {
		return to_range_as_integer(symmerty_percentage, 0, 100, 0, 65535);
	}

	private int frequency_in_khz_to_awg_clock_ticks(double frequency_in_khz) {
		double period = 1 / (frequency_in_khz * 1000);
		return (int) (period / 0.000000025);
	}

	private int to_range_as_integer(double to_convert_value, double lower_limit_of_to_convert_value,
			double upper_limit_of_to_convert_value, int lower_limit_of_output_value, int upper_limit_of_output_value) {

		double slope_coef = (double) (upper_limit_of_output_value - lower_limit_of_output_value)
				/ (upper_limit_of_to_convert_value - lower_limit_of_to_convert_value);
		return lower_limit_of_output_value + (int) (slope_coef * (to_convert_value - lower_limit_of_to_convert_value));
	}
}
