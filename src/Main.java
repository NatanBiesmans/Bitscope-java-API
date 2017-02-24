import Bitscope.Bitscope;

public class Main {

	public static void main(String[] args) {

		Bitscope scope = new Bitscope("COM3", 250000);

		scope.set_voltage_range(6.0, -6.0); //doesn't work yet => more reverse engineering
		scope.set_trigger(0.0); //also doesn't work yet
		scope.set_channel(false); //works => confirmed ... true = channel A, false = channel B
		
		scope.get_view_in_voltages();

		scope.Close_bitscope();
	}

}
