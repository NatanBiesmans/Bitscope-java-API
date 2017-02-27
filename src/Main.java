import Bitscope.Bitscope;
import Bitscope_Machines.Bitscope_analog_function_generator;
import Bitscope_Machines.Bitscope_logic_analyzer;
import Bitscope_Machines.Bitscope_scope;

public class Main {

	public static void main(String[] args) {

		// initialization
		Bitscope bitscope = new Bitscope("COM3", 250000);

		Bitscope_logic_analyzer logic_analyzer = bitscope.get_logic_analyzer();

		logic_analyzer.enable_channel(6);
		logic_analyzer.set_frequency(1000);
		logic_analyzer.initiate_logic_analyzer();

		byte[] samples = logic_analyzer.get_logic_analyzer_trace();

		for (byte sample : samples) {
			System.out.println(sample);
		}

		bitscope.Close_bitscope();

		// reinitialization => scope is stil based on older framework .. awg and logic analyzer are already on the newer one
		bitscope = new Bitscope("COM3", 250000);

		// function generator
		Bitscope_analog_function_generator awg = bitscope.get_analog_function_generator();
		awg.enable_awg();
		awg.set_waveform_to_sine();
		awg.set_frequency(100);
		awg.set_symmetry(20);
		awg.set_voltage(0.8);
		awg.set_offset(0.8);

		// single channel

		Bitscope_scope scope = bitscope.get_scope();
		scope.set_voltage_range(4.0, -4.0); // max voltage: 4V, min voltage: -4V
		scope.set_channel(false); // go to channel B
		scope.set_analog_trigger_source(false);// check channel B for trigger
		scope.set_timebase(2); // a trace is 2 ms long

		scope.set_trigger(2); // Doesn't work yet ... waiting a bit for a fresh
								// view

		double[] scope_samples = scope.get_view_in_voltages();

		for (double sample : scope_samples) {
			System.out.println(sample);
		}

		bitscope.Close_bitscope();

		// chopped

		bitscope = new Bitscope("COM3", 250000);
		scope = bitscope.get_scope();
		scope.set_voltage_range(4.0, -4.0);
		scope.set_analog_trigger_source(false);// check channel B for trigger
		scope.set_timebase(0.5); // a trace is 0.5 ms long

		scope.set_trigger(2); // Doesn't work yet ... waiting a bit for fresh
								// view

		double[][] chopped_samples = scope.get_chopped_view_in_voltages();

		// for(double sample: chopped_samples[1]){ //print all samples of
		// channel 2
		// System.out.println(sample);
		// }

		bitscope.Close_bitscope();
	}

}
