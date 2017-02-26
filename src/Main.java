import Bitscope.Bitscope;
import Bitscope.Bitscope_scope;

public class Main {

	public static void main(String[] args) {

		
		//single channel
		
		Bitscope bitscope = new Bitscope("COM3", 250000);

		Bitscope_scope scope = bitscope.get_scope();
		scope.set_voltage_range(4.0, -4.0); // max voltage: 4V, min voltage: -4V
		scope.set_channel(false); // go to channel B
		scope.set_analog_trigger_source(false);// check channel B for trigger
		scope.set_timebase(0.5); // a trace is 0.5 ms long
		
		scope.set_trigger(2); //Doesn't work yet ... waiting a bit for a fresh view

		double[] samples = scope.get_view_in_voltages();
		
		for(double sample: samples){
			System.out.println(sample);
		}

		bitscope.Close_bitscope();
		
		//chopped 
		
		bitscope = new Bitscope("COM3", 250000);

		scope = bitscope.get_scope();
		scope.set_voltage_range(4.0, -4.0);
		scope.set_analog_trigger_source(false);// check channel B for trigger
		scope.set_timebase(0.5); // a trace is 2 ms long
		
		scope.set_trigger(2); //Doesn't work yet ... waiting a bit for fresh view

		double[][] chopped_samples = scope.get_chopped_view_in_voltages();
		
		for(double sample: chopped_samples[1]){ //print all samples of channel 2 
			System.out.println(sample);
		}

		bitscope.Close_bitscope();
	}

}
