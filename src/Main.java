import Bitscope.Bitscope;
import Bitscope.Bitscope_scope;

public class Main {

	public static void main(String[] args) {

		Bitscope bitscope = new Bitscope("COM3", 250000);

		Bitscope_scope scope = bitscope.get_scope();
		scope.set_voltage_range(4.0, -4.0);
		scope.set_channel(false); // go to channel B
		scope.set_analog_trigger_source(false);// check channel B for trigger
		scope.set_timebase(2); // a trace is 2 ms long
		
		scope.set_trigger(0);

		double[] samples = scope.get_view_in_voltages();
		
		for(double sample: samples){
			System.out.println(sample);
		}

		bitscope.Close_bitscope();
	}

}
