package Bitscope;

import Bitscope.Bitscope_library_control;

public class Bitscope {
	Bitscope_debugging debugger;
	Bitscope_scope scope;
	Bitscope_library_control bitscope_libary_control;

	public Bitscope(String port, int baudRate) {
		// System.out.println("Initialising library");

		bitscope_libary_control = new Bitscope_library_control(port, baudRate);
		debugger = bitscope_libary_control.getDebugger();
		scope = new Bitscope_scope(bitscope_libary_control);
	}

	public void Close_bitscope() {
		bitscope_libary_control.close_comport();
	}

	public byte[] get_view_in_voltages() {
		scope.get_view_in_voltages();
		return null;
	}

	public void set_voltage_range(double high_peak_voltage, double low_peak_voltage) {
		scope.set_voltage_range(high_peak_voltage, low_peak_voltage);
	}
	
	public void set_trigger(double trigger_voltage) {
		scope.set_trigger(trigger_voltage);
	}
	
	public void set_channel(Boolean channelA_not_channelB) {
		scope.set_channel(channelA_not_channelB);
	}
}
