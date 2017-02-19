public class Bitscope {
	Comport_interface comPort;
	Bitscope_registers registers;
	Bitscope_debugging debugger;
	Bitscope_control control;

	public Bitscope(String port, int baudRate) {
		System.out.println("Initialising...");
		Bitscope_init(port, baudRate);
	}

	private void Bitscope_init(String port, int baudRate) {
		comPort = new Comport_interface(port, baudRate);
		comPort.open_comPort();

		registers = new Bitscope_registers();
		debugger = new Bitscope_debugging(registers, comPort);
		control = new Bitscope_control(registers, comPort);
	}

	public void close_bitscope() {
		comPort.close_comport();
	}

	public String trace_until_trigger() {
		return control.trace_until_trigger();
	}

	public String measure_time_period() {
		return control.measure_time_period();
	}

	public String delay_until_triger_then_trace() {
		return control.delay_until_triger_then_trace();
	}

	public String request_sample_dump() {
		return control.request_sample_dump();
	}

	public String request_mixed_memory_dump() {
		return control.request_mixed_memory_dump();
	}

	public String request_analog_memory_dump() {
		return control.request_analog_memory_dump();
	}

	public void reset_VM() {
		control.reset_VM();
	}

	public void set_trace_mode(int load_value) {
		registers.getTrace_mode().load_value(load_value);
	}

	public String get_revision() {
		return control.get_revision();
	}
	
	public void upload_registers_to_bitscope(){
		control.upload_registers_to_bitscope();
	}

	// Data capture registers
	// spock option alteration
	public void set_trigger_type_to_level_sensitive() {
		control.set_trigger_type_to_level_sensitive();
	}

	public void set_trigger_type_to_edge_sensitive() {
		control.set_trigger_type_to_edge_sensitive();
	}

	public void set_edge_direction_from_false_to_true() {
		control.set_edge_direction_from_false_to_true();
	}

	public void set_edge_direction_from_true_to_false() {
		control.set_edge_direction_from_true_to_false();
	}

	public void set_page_to_lower() {
		control.set_page_to_lower();
	}

	public void set_page_to_higher() {
		control.set_page_to_higher();
	}

	public void set_trigger_source_to_analog() {
		control.set_trigger_source_to_analog();
	}

	public void set_trigger_source_to_digital() {
		control.set_trigger_source_to_digital();
	}

	public void set_trigger_bit7_DD7() {
		control.set_trigger_bit7_DD7();
	}

	public void set_trigger_bit7_comparator() {
		control.set_trigger_bit7_comparator();
	}

	public void set_trigger_bit7_event1() {
		control.set_trigger_bit7_event1();
	}

	public void set_trigger_bit7_event2() {
		control.set_trigger_bit7_event2();
	}

	// trigger logic
	public void use_analog_trigger() {
		control.use_analog_trigger();
	}

	public void set_logic_trigger(int channel, boolean level) {
		control.set_logic_trigger(channel, level);
	}

	public void unset_logic_trigger(int channel) {
		control.unset_logic_trigger(channel);
	}

	public void set_dump_size(int dump_size) {
		control.set_dump_size(dump_size);
	}

	// Trace mode & delay registers
	public void set_pre_trigger_delay(int pre_trigger_delay_value) {
		control.set_pre_trigger_delay(pre_trigger_delay_value);
	}

	public void set_analog_mode_select(int analog_mode) {
		control.set_analog_mode_select(analog_mode);
	}

	public void set_event_scan_mode_select(int event_scan_mode) {
		control.set_event_scan_mode_select(event_scan_mode);
	}

	public void set_delayed_trace_mode(int trace_mode) {
		control.set_delayed_trace_mode(trace_mode);
	}

	public void set_pre_trigger_filter(int pre_trigger_filter_value) {
		control.set_pre_trigger_filter(pre_trigger_filter_value);
	}

	public void set_post_trigger_filter(int post_trigger_filter_value) {
		control.set_post_trigger_filter(post_trigger_filter_value);
	}

	public void set_logic_trace_mode(int logic_trace_mode_value) {
		control.set_logic_trace_mode(logic_trace_mode_value);
	}
	
	public void set_post_trigger_delay(int delay){
		control.set_post_trigger_delay(delay);
	}

	// Frequency measurement registers

		// TODO
	
}
