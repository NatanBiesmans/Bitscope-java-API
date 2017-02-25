package Registers;

public class Bitscope_registers {

	private Register time_base_clock_ticks_byte0 = new Register(0x2e, 0x00);
	private Register time_base_clock_ticks_byte1 = new Register(0x2f, 0x00);

	private Register spock_option = new Register(0x07, 0x00);
	private Register Analog_channel_enable = new Register(0x37, 0x00);

	private Register Lower_voltage_range_byte0 = new Register(0x64, 0x00);
	private Register Lower_voltage_range_byte1 = new Register(0x65, 0x00);
	private Register Higher_voltage_range_byte0 = new Register(0x66, 0x00);
	private Register Higher_voltage_range_byte1 = new Register(0x67, 0x00);

	private Register Trigger_level_byte0 = new Register(0x68, 0x00);
	private Register Trigger_level_byte1 = new Register(0x69, 0x00);

	private Register dump_size_byte0 = new Register(0x1c, 0x00);
	private Register dump_size_byte1 = new Register(0x1d, 0x00);

	private Register dump_mode = new Register(0x1e, 0x00);

	private Register trace_mode = new Register(0x21, 0x00);

	private Register counter_capture_byte0 = new Register(0x08, 0x00);	
	private Register counter_capture_byte1 = new Register(0x09, 0x00);
	private Register counter_capture_byte2 = new Register(0x0a, 0x00);

	private Register iterations_byte0 = new Register(0x16, 0x00);
	private Register iterations_byte1 = new Register(0x17, 0x00);

	private Register post_trigger_capture_byte0 = new Register(0x2a, 0x00);
	private Register post_trigger_capture_byte1 = new Register(0x2b, 0x00);

	private Register dump_channel = new Register(0x30, 0x00);

	private Register buffer_mode = new Register(0x31, 0x00);

	private Register pre_trigger_capture_byte0 = new Register(0x26, 0x00);
	private Register pre_trigger_capture_byte1 = new Register(0x27, 0x00);

	private Register trigger_checking_delay_period_byte0 = new Register(0x22, 0x00);
	private Register trigger_checking_delay_period_byte1 = new Register(0x23, 0x00);
	private Register trigger_checking_delay_period_byte2 = new Register(0x24, 0x00);
	private Register trigger_checking_delay_period_byte3 = new Register(0x25, 0x00);

	private Register timeout_byte0 = new Register(0x2c, 0x00);
	private Register timeout_byte1 = new Register(0x2d, 0x00);

	private Register clock_scale_byte0 = new Register(0x14, 0x00);
	private Register clock_scale_byte1 = new Register(0x15, 0x00);

	private Register trigger_mask = new Register(0x06, 0x00);
	private Register trigger_logic = new Register(0x05, 0x00);

	private Register trigger_intro_byte0 = new Register(0x32, 0x00);
	private Register trigger_intro_byte1 = new Register(0x33, 0x00);
	private Register trigger_outro_byte0 = new Register(0x34, 0x00);
	private Register trigger_outro_byte1 = new Register(0x35, 0x00);

	private Register trigger_value_byte0 = new Register(0x44, 0x00);
	private Register trigger_value_byte1 = new Register(0x45, 0x00);

	// ----EEPROM----------------------------------------------------------------------------
	private Register eeprom_data = new Register(0x0f, 0x00);
	private Register eeprom_address = new Register(0x10, 0x00);

	public Bitscope_registers() {
	}

	public Register getEeprom_data() {
		return eeprom_data;
	}

	public Register getEeprom_address() {
		return eeprom_address;
	}

	public void load_Eeprom_data(int load_value) {
		eeprom_data.load_value(load_value);
	}

	public void load_Eeprom_address(int load_value) {
		eeprom_address.load_value(load_value);
	}

	public Register getTime_base_clock_ticks_byte0() {
		return time_base_clock_ticks_byte0;
	}

	public Register getTime_base_clock_ticks_byte1() {
		return time_base_clock_ticks_byte1;
	}

	public Register getSpock_option() {
		return spock_option;
	}

	public Register getAnalog_channel_enable() {
		return Analog_channel_enable;
	}

	public Register getLower_voltage_range_byte0() {
		return Lower_voltage_range_byte0;
	}

	public Register getLower_voltage_range_byte1() {
		return Lower_voltage_range_byte1;
	}

	public Register getHigher_voltage_range_byte0() {
		return Higher_voltage_range_byte0;
	}

	public Register getHigher_voltage_range_byte1() {
		return Higher_voltage_range_byte1;
	}

	public Register getTrigger_level_byte0() {
		return Trigger_level_byte0;
	}

	public Register getTrigger_level_byte1() {
		return Trigger_level_byte1;
	}

	public Register getDump_size_byte0() {
		return dump_size_byte0;
	}

	public Register getDump_size_byte1() {
		return dump_size_byte1;
	}

	public Register getDump_mode() {
		return dump_mode;
	}

	public Register getTrace_mode() {
		return trace_mode;
	}

	public Register getCounter_capture_byte0() {
		return counter_capture_byte0;
	}

	public Register getCounter_capture_byte1() {
		return counter_capture_byte1;
	}

	public Register getIterations_byte0() {
		return iterations_byte0;
	}

	public Register getIterations_byte1() {
		return iterations_byte1;
	}

	public Register getPost_trigger_capture_byte0() {
		return post_trigger_capture_byte0;
	}

	public Register getPost_trigger_capture_byte1() {
		return post_trigger_capture_byte1;
	}

	public Register getDump_channel() {
		return dump_channel;
	}

	public Register getBuffer_mode() {
		return buffer_mode;
	}

	public Register getPre_trigger_capture_byte0() {
		return pre_trigger_capture_byte0;
	}

	public Register getPre_trigger_capture_byte1() {
		return pre_trigger_capture_byte1;
	}

	public Register getTrigger_checking_delay_period_byte0() {
		return trigger_checking_delay_period_byte0;
	}

	public Register getTrigger_checking_delay_period_byte1() {
		return trigger_checking_delay_period_byte1;
	}

	public Register getTrigger_checking_delay_period_byte2() {
		return trigger_checking_delay_period_byte2;
	}

	public Register getTrigger_checking_delay_period_byte3() {
		return trigger_checking_delay_period_byte3;
	}

	public Register getTimeout_byte0() {
		return timeout_byte0;
	}

	public Register getTimeout_byte1() {
		return timeout_byte1;
	}

	public Register getClock_scale_byte0() {
		return clock_scale_byte0;
	}

	public Register getClock_scale_byte1() {
		return clock_scale_byte1;
	}

	public Register getTrigger_mask() {
		return trigger_mask;
	}

	public Register getTrigger_logic() {
		return trigger_logic;
	}

	public Register getTrigger_intro_byte0() {
		return trigger_intro_byte0;
	}

	public Register getTrigger_intro_byte1() {
		return trigger_intro_byte1;
	}

	public Register getTrigger_outro_byte0() {
		return trigger_outro_byte0;
	}

	public Register getTrigger_outro_byte1() {
		return trigger_outro_byte1;
	}

	public Register getTrigger_value_byte0() {
		return trigger_value_byte0;
	}

	public Register getTrigger_value_byte1() {
		return trigger_value_byte1;
	}
	// public functions to load values in registers

	public void load_value_in_Spock_option(int load_value) {
		spock_option.load_value(load_value);
	}

	public void load_value_in_Analog_channel_enable(int load_value) {
		Analog_channel_enable.load_value(load_value);
	}

	public void load_value_in_Dump_mode(int load_value) {
		dump_mode.load_value(load_value);
	}

	public void load_value_in_Trace_mode(int load_value) {
		trace_mode.load_value(load_value);
	}

	public void load_value_in_Dump_channel(int load_value) {
		dump_channel.load_value(load_value);
	}

	public void load_value_in_Buffer_mode(int load_value) {
		buffer_mode.load_value(load_value);
	}

	public void load_value_in_Trigger_mask(int load_value) {
		trigger_mask.load_value(load_value);
	}

	public void load_value_in_Trigger_logic(int load_value) {
		trigger_logic.load_value(load_value);
	}

	// public functions to load values in linked registers

	public void load_time_base_clock_ticks(int load_value) {
		time_base_clock_ticks_byte0.load_value(get_lower_byte(load_value));
		time_base_clock_ticks_byte1.load_value(get_higher_bytes(load_value));
	}

	public void load_Lower_voltage_range(int load_value) {
		Lower_voltage_range_byte0.load_value(get_lower_byte(load_value));
		Lower_voltage_range_byte1.load_value(get_higher_bytes(load_value));
	}

	public void load_Higher_voltage_range(int load_value) {
		Higher_voltage_range_byte0.load_value(get_lower_byte(load_value));
		Higher_voltage_range_byte1.load_value(get_higher_bytes(load_value));
	}

	public void load_Trigger_level(int load_value) {
		Trigger_level_byte0.load_value(get_lower_byte(load_value));
		Trigger_level_byte1.load_value(get_higher_bytes(load_value));
	}

	public void load_dump_size(int load_value) {
		dump_size_byte0.load_value(get_lower_byte(load_value));
		dump_size_byte1.load_value(get_higher_bytes(load_value));
	}

	public void load_iterations(int load_value) {
		iterations_byte0.load_value(get_lower_byte(load_value));
		iterations_byte1.load_value(get_higher_bytes(load_value));
	}

	public void load_post_trigger_capture(int load_value) {
		post_trigger_capture_byte0.load_value(get_lower_byte(load_value));
		post_trigger_capture_byte1.load_value(get_higher_bytes(load_value));
	}

	public void load_pre_trigger_capture(int load_value) {
		pre_trigger_capture_byte0.load_value(get_lower_byte(load_value));
		pre_trigger_capture_byte1.load_value(get_higher_bytes(load_value));
	}

	public void load_timeout(int load_value) {
		timeout_byte0.load_value(get_lower_byte(load_value));
		timeout_byte1.load_value(get_higher_bytes(load_value));
	}

	public void load_clock_scale(int load_value) {
		clock_scale_byte0.load_value(get_lower_byte(load_value));
		clock_scale_byte1.load_value(get_higher_bytes(load_value));
	}

	public void load_trigger_intro(int load_value) {
		trigger_intro_byte0.load_value(get_lower_byte(load_value));
		trigger_intro_byte1.load_value(get_higher_bytes(load_value));
	}

	public void load_trigger_outro(int load_value) {
		trigger_outro_byte0.load_value(get_lower_byte(load_value));
		trigger_outro_byte1.load_value(get_higher_bytes(load_value));
	}

	public void load_trigger_value(int load_value) {
		trigger_value_byte0.load_value(get_lower_byte(load_value));
		trigger_value_byte1.load_value(get_higher_bytes(load_value));
	}

	public void load_in_counter_capture_address(int load_value) {
		counter_capture_byte0.load_value(get_lower_byte(load_value));
		counter_capture_byte1.load_value(get_lower_byte(get_higher_bytes(load_value)));
		counter_capture_byte2.load_value(get_lower_byte(get_higher_bytes(get_higher_bytes(load_value))));
	}

	public void load_trigger_checking_delay_period(int load_value) {
		trigger_checking_delay_period_byte0.load_value(get_lower_byte(load_value));
		trigger_checking_delay_period_byte1.load_value(get_lower_byte(get_higher_bytes(load_value)));
		trigger_checking_delay_period_byte2.load_value(get_lower_byte(get_higher_bytes(get_higher_bytes(load_value))));
		trigger_checking_delay_period_byte3
				.load_value(get_lower_byte(get_higher_bytes(get_higher_bytes(get_higher_bytes(load_value)))));
	}

	// spock options: this is from the datasheet so I'm assuming its wrong

	public void spock_set_trigger_source_to_digital_trigger() {
		spock_option.unset_bit(0);
	}

	public void spock_set_trigger_source_to_analog_trigger() {
		spock_option.set_bit(0);
	}
	
	public void spock_set_trigger_bit_7_to_DD7(){
		spock_option.unset_bit(1);
		spock_option.unset_bit(2);
	}
	
	public void spock_set_trigger_bit_7_to_comparator(){
		spock_option.set_bit(1);
		spock_option.unset_bit(2);
	}
	
	public void spock_set_trigger_bit_7_to_event1(){
		spock_option.unset_bit(1);
		spock_option.set_bit(2);
	}
	
	public void spock_set_trigger_bit_7_to_event2(){
		spock_option.set_bit(1);
		spock_option.set_bit(2);
	}
	
	public void spock_set_page_select(boolean lower_not_higher){
		if(lower_not_higher){
			spock_option.unset_bit(3);
		}else{
			spock_option.set_bit(3);
		}
	}
	
	public void spock_set_edge_direction_true_to_false(){
		spock_option.set_bit(4);
	}

	public void spock_set_edge_direction_false_to_true(){
		spock_option.unset_bit(4);
	}
	
	public void spock_set_trigger_to_level_sensitive(){
		spock_option.unset_bit(5);
	}
	
	public void spock_set_trigger_to_edge_sensitive(){
		spock_option.set_bit(5);
	}
	// create load strings

	public String create_setup_string() {
		String setup_string = "";

		setup_string += make_load_string_for_register(time_base_clock_ticks_byte0);
		setup_string += make_load_string_for_register(time_base_clock_ticks_byte1);
		setup_string += make_load_string_for_register(spock_option);
		setup_string += make_load_string_for_register(Analog_channel_enable);
		setup_string += make_load_string_for_register(Lower_voltage_range_byte0);
		setup_string += make_load_string_for_register(Lower_voltage_range_byte1);
		setup_string += make_load_string_for_register(Higher_voltage_range_byte0);
		setup_string += make_load_string_for_register(Higher_voltage_range_byte1);
		setup_string += make_load_string_for_register(Trigger_level_byte0);
		setup_string += make_load_string_for_register(Trigger_level_byte1);
		setup_string += make_load_string_for_register(dump_size_byte0);
		setup_string += make_load_string_for_register(dump_size_byte1);
		setup_string += make_load_string_for_register(dump_mode);
		setup_string += make_load_string_for_register(trace_mode);
		setup_string += make_load_string_for_register(counter_capture_byte0);
		setup_string += make_load_string_for_register(counter_capture_byte1);
		setup_string += make_load_string_for_register(counter_capture_byte2);
		setup_string += make_load_string_for_register(iterations_byte0);
		setup_string += make_load_string_for_register(iterations_byte1);
		setup_string += make_load_string_for_register(post_trigger_capture_byte0);
		setup_string += make_load_string_for_register(post_trigger_capture_byte1);
		setup_string += make_load_string_for_register(dump_channel);
		setup_string += make_load_string_for_register(buffer_mode);
		setup_string += make_load_string_for_register(pre_trigger_capture_byte0);
		setup_string += make_load_string_for_register(pre_trigger_capture_byte1);
		setup_string += make_load_string_for_register(trigger_checking_delay_period_byte0);
		setup_string += make_load_string_for_register(trigger_checking_delay_period_byte1);
		setup_string += make_load_string_for_register(trigger_checking_delay_period_byte2);
		setup_string += make_load_string_for_register(trigger_checking_delay_period_byte3);
		setup_string += make_load_string_for_register(timeout_byte0);
		setup_string += make_load_string_for_register(timeout_byte1);
		setup_string += make_load_string_for_register(clock_scale_byte0);
		setup_string += make_load_string_for_register(clock_scale_byte1);
		setup_string += make_load_string_for_register(trigger_mask);
		setup_string += make_load_string_for_register(trigger_logic);
		setup_string += make_load_string_for_register(trigger_intro_byte0);
		setup_string += make_load_string_for_register(trigger_intro_byte1);
		setup_string += make_load_string_for_register(trigger_outro_byte0);
		setup_string += make_load_string_for_register(trigger_outro_byte1);
		setup_string += make_load_string_for_register(trigger_value_byte0);
		setup_string += make_load_string_for_register(trigger_value_byte1);

		return setup_string;
	}

	public String create_eeprom_write_string() {
		return String.format("[%02x]@[%02x]s[%02x]@[%02x]s", eeprom_address.getRegister_address(),
				eeprom_address.getRegister_value(), eeprom_data.getRegister_address(), eeprom_data.getRegister_value());
	}

	public String create_eeprom_read_string() {
		return String.format("[%02x]@[%02x]s", eeprom_address.getRegister_address(),
				eeprom_address.getRegister_value());
	}

	public String create_print_string(Register to_print_register) {
		return String.format("[%02x]@p", to_print_register.getRegister_address());
	}

	// private functions
	private String make_load_string_for_register(Register register) {
		if(register.get_update_pending()){
			register.unset_update_pending();
			return String.format("[%02x]@[%02x]s", register.getRegister_address(),register.getRegister_value());
		} else {
			return "";
		}
	}

	private int get_lower_byte(int value) {
		return get_lower_byte_internal(value, get_higher_bytes(value));
	}

	private int get_lower_byte_internal(int value, int higher_byte) {
		int lower_byte = value - higher_byte * 256;
		return lower_byte;
	}

	private int get_higher_bytes(int value) {
		int higher_byte = 0;
		if (value >= 256) {
			higher_byte = value / 256;
		}
		return higher_byte;
	}
}