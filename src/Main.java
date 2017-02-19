public class Main {

	public static void main(String[] args) {
		Bitscope scope = new Bitscope("COM3", 250000);
		
		scope.set_page_to_lower();
		scope.set_trigger_type_to_edge_sensitive();
		scope.set_edge_direction_from_false_to_true();
		scope.set_trigger_source_to_analog();
		scope.set_trigger_bit7_DD7();
		scope.use_analog_trigger();
		scope.set_dump_size(0xff);
		scope.set_post_trigger_delay(0x0f00);
		
		scope.upload_registers_to_bitscope();
		scope.close_bitscope();
	}
}
