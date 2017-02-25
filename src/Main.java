import Bitscope.Bitscope;
import Bitscope.Bitscope_scope;

public class Main {

	public static void main(String[] args) {

		Bitscope bitscope = new Bitscope("COM3", 250000);

		Bitscope_scope scope = bitscope.get_scope();
		scope.set_voltage_range(2.0, -2.0);
		scope.set_trigger(20); //  doesn't work yet
		scope.set_timebase(8);
		scope.get_view_in_voltages();

		bitscope.Close_bitscope();
	}

}
