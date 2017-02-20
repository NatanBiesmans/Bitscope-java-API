public class Main {

	public static void main(String[] args) {

		Bitscope scope = new Bitscope("COM3", 250000);

		// setup
		scope.set_page_to_lower();
		scope.set_trigger_type_to_edge_sensitive();
		scope.set_edge_direction_from_false_to_true();
		scope.set_trigger_source_to_analog();
		scope.set_trigger_bit7_DD7();
		scope.use_analog_trigger();
		scope.set_dump_size(0xff);
		scope.set_post_trigger_delay(0x0f00);
		scope.set_primary_channel_channel_select(true);
		scope.set_primary_attenuation_range(3.16, false);

		scope.upload_registers_to_bitscope();

		// trace
		scope.trace_until_trigger();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Acquire
		byte[] dump = scope.request_analog_memory_dump();
		int[] dump_int = bytearray2intarray(dump);
		for (int dump_element : dump_int) {
			System.out.println(dump_element);
		}

		scope.close_bitscope();
	}

	public static int[] bytearray2intarray(byte[] barray) {
		int[] iarray = new int[barray.length];
		int i = 0;
		for (byte b : barray)
			iarray[i++] = b & 0xff;
		return iarray;
	}
}
