public class Bitscope_control {
	Comport_interface comPort;
	Bitscope_registers registers;

	public Bitscope_control(Bitscope_registers registers, Comport_interface comPort) {
		this.comPort = comPort;
		this.registers = registers;
	}

	public String trace_until_trigger() {
		return comPort.send_string_and_wait_for_response_as_string("T");
	}

	public String measure_time_period() {
		return comPort.send_string_and_wait_for_response_as_string("P");
	}

	public String delay_until_triger_then_trace() {
		return comPort.send_string_and_wait_for_response_as_string("D");
	}

	public String request_sample_dump() {
		return comPort.send_string_and_wait_for_response_as_string("S");
	}

	public String request_mixed_memory_dump() {
		return comPort.send_string_and_wait_for_response_as_string("M");
	}

	public String request_analog_memory_dump() {
		return comPort.send_string_and_wait_for_response_as_string("A");
	}

	public void reset_VM() {
		comPort.send_string("\0");
	}

	public String get_revision() {
		return comPort.send_string_and_wait_for_response_as_string("?");
	}
	
	public void set_trigger_type_to_level_sensitive() {
		registers.getSpock_option().unset_bit(5);
	}

	public void set_trigger_type_to_edge_sensitive() {
		registers.getSpock_option().set_bit(5);
	}

	public void set_edge_direction_from_false_to_true() {
		registers.getSpock_option().unset_bit(4);
	}

	public void set_edge_direction_from_true_to_false() {
		registers.getSpock_option().set_bit(4);
	}

	public void set_page_to_lower() {
		registers.getSpock_option().unset_bit(3);
	}

	public void set_page_to_higher() {
		registers.getSpock_option().set_bit(3);
	}

	public void set_trigger_source_to_analog() {
		registers.getSpock_option().set_bit(0);
	}

	public void set_trigger_source_to_digital() {
		registers.getSpock_option().unset_bit(0);
	}

	public void set_trigger_bit7_DD7() {
		registers.getSpock_option().unset_bit(1);
		registers.getSpock_option().unset_bit(2);
	}

	public void set_trigger_bit7_comparator() {
		registers.getSpock_option().set_bit(2);
		registers.getSpock_option().unset_bit(1);
	}

	public void set_trigger_bit7_event1() {
		registers.getSpock_option().set_bit(1);
		registers.getSpock_option().unset_bit(2);
	}

	public void set_trigger_bit7_event2() {
		registers.getSpock_option().set_bit(1);
		registers.getSpock_option().set_bit(2);
	}

	public void set_post_trigger_delay(int delay){
		registers.set_post_trigger_delay(delay);
	}
	
	// trigger logic
	public void use_analog_trigger() {
		registers.getTrigger_mask().load_value(0xff);
		registers.getTrigger_logic().load_value(0x00);
	}

	public void set_logic_trigger(int channel, boolean level) {
		registers.getTrigger_mask().unset_bit(channel);

		if (level) {
			registers.getTrigger_logic().set_bit(channel);
		} else {
			registers.getTrigger_logic().unset_bit(channel);
		}
	}

	public void unset_logic_trigger(int channel) {
		registers.getTrigger_mask().set_bit(channel);
	}

	public void set_dump_size(int dump_size) {
		this.registers.getDump_size().load_value(dump_size);
	}

	public void set_pre_trigger_delay(int pre_trigger_delay_value) {
		registers.getPre_trigger_delay().load_value(pre_trigger_delay_value);
	}

	public void set_analog_mode_select(int analog_mode) {
		registers.getAnalog_mode_select().load_value(analog_mode);
	}

	public void set_event_scan_mode_select(int event_scan_mode) {
		this.registers.getEvent_mode_select().load_value(event_scan_mode);
	}

	public void set_delayed_trace_mode(int trace_mode) {
		registers.getDelayed_trace_mode_select().load_value(trace_mode);
	}

	public void set_pre_trigger_filter(int pre_trigger_filter_value) {
		registers.getPre_trigger_filter().load_value(pre_trigger_filter_value);
	}

	public void set_post_trigger_filter(int post_trigger_filter_value) {
		registers.getPost_trigger_filter().load_value(post_trigger_filter_value);
	}

	public void set_logic_trace_mode(int logic_trace_mode_value) {
		registers.getLogic_trace_mode_select().load_value(logic_trace_mode_value);
	}
	
	public void upload_registers_to_bitscope(){
		comPort.send_string(registers.make_setup_string());
	}
}
