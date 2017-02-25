import Bitscope.Bitscope;

public class Main {

	public static void main(String[] args) {

		Bitscope scope = new Bitscope("COM3", 250000);

		scope.set_voltage_range(2.0, -2.0);
		scope.set_trigger(1.0); //  doesn't work yet
		scope.set_channel(false);

		scope.get_view_in_voltages();

		scope.Close_bitscope();
	}

}
