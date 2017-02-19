
public class Bitscope_registers {

	// spock control registers
	private Register sample_preload_low = new Register(0x00, 3);
	private Register sample_preload_high = new Register(0x00, 4);
	private Register trigger_logic = new Register(0x00, 5);
	private Register trigger_mask = new Register(0x00, 6);
	private Register spock_option = new Register(0x00, 7);

	// spock counter register
	private Register counter_capture_low = new Register(0x00, 9);
	private Register counter_capture_high = new Register(0x00, 10);
	private Register intput_attenuation = new Register(0x00, 14);
	private Register dump_size = new Register(0x00, 15);

	// trace mode & delay register
	private Register trace_mode = new Register(0x00, 8);
	private Register post_trigger_delay_high = new Register(0x00, 11);
	private Register post_trigger_delay_low = new Register(0x00, 12);
	private Register time_base_expansion = new Register(0x00, 13);
	private Register pre_trigger_delay = new Register(0x00, 20);

	// analog dump register
	private Register analog_mode_select = new Register(0x00, 24);
	private Register mark_bytes = new Register(0x00, 25);
	private Register skip_bytes = new Register(0x00, 26);
	private Register repeat_count = new Register(0x00, 27);

	// event scan registers
	private Register event_mode_select = new Register(0x00, 28);
	private Register scan_range_low = new Register(0x00, 29);
	private Register scan_range_high = new Register(0x00, 30);
	private Register positive_transition_level = new Register(0x00, 31);
	private Register negative_transition_level = new Register(0x00, 32);

	// delayed trace mode registers
	private Register delayed_trace_mode_select = new Register(0x00, 33);
	private Register delay_counter_b0 = new Register(0x00, 34);
	private Register delay_counter_b1 = new Register(0x00, 35);
	private Register delay_counter_b2 = new Register(0x00, 36);
	private Register delay_counter_b3 = new Register(0x00, 37);
	private Register capture_counter_b0 = new Register(0x00, 38);
	private Register capture_counter_b1 = new Register(0x00, 39);
	private Register capture_counter_b2 = new Register(0x00, 40);
	private Register capture_counter_b3 = new Register(0x00, 41);
	private Register slow_clock_sample_counter_b0 = new Register(0x00, 42);
	private Register slow_clock_sample_counter_b1 = new Register(0x00, 43);
	private Register pre_trigger_filter = new Register(0x00, 44);
	private Register post_trigger_filter = new Register(0x00, 45);

	// logic trace mode registers
	private Register logic_trace_mode_select = new Register(0x00, 46);
	private Register logic_sample_rate = new Register(0x00, 47);

	public Bitscope_registers() {
		// nothin yet
	}

	// simple getter and setters
	public Register getSample_preload_low() {
		return sample_preload_low;
	}

	public void setSample_preload_low(Register sample_preload_low) {
		this.sample_preload_low = sample_preload_low;
	}

	public Register getSample_preload_high() {
		return sample_preload_high;
	}

	public void setSample_preload_high(Register sample_preload_high) {
		this.sample_preload_high = sample_preload_high;
	}

	public Register getTrigger_logic() {
		return trigger_logic;
	}

	public void setTrigger_logic(Register trigger_logic) {
		this.trigger_logic = trigger_logic;
	}

	public Register getTrigger_mask() {
		return trigger_mask;
	}

	public void setTrigger_mask(Register trigger_mask) {
		this.trigger_mask = trigger_mask;
	}

	public Register getSpock_option() {
		return spock_option;
	}

	public void setSpock_option(Register spock_option) {
		this.spock_option = spock_option;
	}

	public Register getCounter_capture_low() {
		return counter_capture_low;
	}

	public void setCounter_capture_low(Register counter_capture_low) {
		this.counter_capture_low = counter_capture_low;
	}

	public Register getCounter_capture_high() {
		return counter_capture_high;
	}

	public void setCounter_capture_high(Register counter_capture_high) {
		this.counter_capture_high = counter_capture_high;
	}

	public Register getIntput_attenuation() {
		return intput_attenuation;
	}

	public void setIntput_attenuation(Register intput_attenuation) {
		this.intput_attenuation = intput_attenuation;
	}
	
	public Register getDump_size() {
		return dump_size;
	}

	public void setDump_size(Register dump_size) {
		this.dump_size = dump_size;
	}

	public Register getTrace_mode() {
		return trace_mode;
	}

	public void setTrace_mode(Register trace_mode) {
		this.trace_mode = trace_mode;
	}

	public Register getPost_trigger_delay_high() {
		return post_trigger_delay_high;
	}

	public void setPost_trigger_delay_high(Register post_trigger_delay_high) {
		this.post_trigger_delay_high = post_trigger_delay_high;
	}

	public Register getPost_trigger_delay_low() {
		return post_trigger_delay_low;
	}

	public void setPost_trigger_delay_low(Register post_trigger_delay_low) {
		this.post_trigger_delay_low = post_trigger_delay_low;
	}

	public Register getTime_base_expansion() {
		return time_base_expansion;
	}

	public void setTime_base_expansion(Register time_base_expansion) {
		this.time_base_expansion = time_base_expansion;
	}

	public Register getPre_trigger_delay() {
		return pre_trigger_delay;
	}

	public void setPre_trigger_delay(Register pre_trigger_delay) {
		this.pre_trigger_delay = pre_trigger_delay;
	}

	public Register getAnalog_mode_select() {
		return analog_mode_select;
	}

	public void setAnalog_mode_select(Register analog_mode_select) {
		this.analog_mode_select = analog_mode_select;
	}

	public Register getMark_bytes() {
		return mark_bytes;
	}

	public void setMark_bytes(Register mark_bytes) {
		this.mark_bytes = mark_bytes;
	}

	public Register getSkip_bytes() {
		return skip_bytes;
	}

	public void setSkip_bytes(Register skip_bytes) {
		this.skip_bytes = skip_bytes;
	}

	public Register getRepeat_count() {
		return repeat_count;
	}

	public void setRepeat_count(Register repeat_count) {
		this.repeat_count = repeat_count;
	}

	public Register getEvent_mode_select() {
		return event_mode_select;
	}

	public void setEvent_mode_select(Register event_mode_select) {
		this.event_mode_select = event_mode_select;
	}

	public Register getScan_range_low() {
		return scan_range_low;
	}

	public void setScan_range_low(Register scan_range_low) {
		this.scan_range_low = scan_range_low;
	}

	public Register getScan_range_high() {
		return scan_range_high;
	}

	public void setScan_range_high(Register scan_range_high) {
		this.scan_range_high = scan_range_high;
	}

	public Register getPositive_transition_level() {
		return positive_transition_level;
	}

	public void setPositive_transition_level(Register positive_transition_level) {
		this.positive_transition_level = positive_transition_level;
	}

	public Register getNegative_transition_level() {
		return negative_transition_level;
	}

	public void setNegative_transition_level(Register negative_transition_level) {
		this.negative_transition_level = negative_transition_level;
	}

	public Register getDelayed_trace_mode_select() {
		return delayed_trace_mode_select;
	}

	public void setDelayed_trace_mode_select(Register delayed_trace_mode_select) {
		this.delayed_trace_mode_select = delayed_trace_mode_select;
	}

	public Register getDelay_counter_b0() {
		return delay_counter_b0;
	}

	public void setDelay_counter_b0(Register delay_counter_b0) {
		this.delay_counter_b0 = delay_counter_b0;
	}

	public Register getDelay_counter_b1() {
		return delay_counter_b1;
	}

	public void setDelay_counter_b1(Register delay_counter_b1) {
		this.delay_counter_b1 = delay_counter_b1;
	}

	public Register getDelay_counter_b2() {
		return delay_counter_b2;
	}

	public void setDelay_counter_b2(Register delay_counter_b2) {
		this.delay_counter_b2 = delay_counter_b2;
	}

	public Register getDelay_counter_b3() {
		return delay_counter_b3;
	}

	public void setDelay_counter_b3(Register delay_counter_b3) {
		this.delay_counter_b3 = delay_counter_b3;
	}

	public Register getCapture_counter_b0() {
		return capture_counter_b0;
	}

	public void setCapture_counter_b0(Register capture_counter_b0) {
		this.capture_counter_b0 = capture_counter_b0;
	}

	public Register getCapture_counter_b1() {
		return capture_counter_b1;
	}

	public void setCapture_counter_b1(Register capture_counter_b1) {
		this.capture_counter_b1 = capture_counter_b1;
	}

	public Register getCapture_counter_b2() {
		return capture_counter_b2;
	}

	public void setCapture_counter_b2(Register capture_counter_b2) {
		this.capture_counter_b2 = capture_counter_b2;
	}

	public Register getCapture_counter_b3() {
		return capture_counter_b3;
	}

	public void setCapture_counter_b3(Register capture_counter_b3) {
		this.capture_counter_b3 = capture_counter_b3;
	}

	public Register getSlow_clock_sample_counter_b0() {
		return slow_clock_sample_counter_b0;
	}

	public void setSlow_clock_sample_counter_b0(Register slow_clock_sample_counter_b0) {
		this.slow_clock_sample_counter_b0 = slow_clock_sample_counter_b0;
	}

	public Register getSlow_clock_sample_counter_b1() {
		return slow_clock_sample_counter_b1;
	}

	public void setSlow_clock_sample_counter_b1(Register slow_clock_sample_counter_b1) {
		this.slow_clock_sample_counter_b1 = slow_clock_sample_counter_b1;
	}

	public Register getPre_trigger_filter() {
		return pre_trigger_filter;
	}

	public void setPre_trigger_filter(Register pre_trigger_filter) {
		this.pre_trigger_filter = pre_trigger_filter;
	}

	public Register getPost_trigger_filter() {
		return post_trigger_filter;
	}

	public void setPost_trigger_filter(Register post_trigger_filter) {
		this.post_trigger_filter = post_trigger_filter;
	}

	public Register getLogic_trace_mode_select() {
		return logic_trace_mode_select;
	}

	public void setLogic_trace_mode_select(Register logic_trace_mode_select) {
		this.logic_trace_mode_select = logic_trace_mode_select;
	}

	public Register getLogic_sample_rate() {
		return logic_sample_rate;
	}

	public void setLogic_sample_rate(Register logic_sample_rate) {
		this.logic_sample_rate = logic_sample_rate;
	}

	// register functions
	public void set_post_trigger_delay(int delay) {

		int delay_high = get_higher_byte(delay);
		int delay_low = get_lower_byte(delay, delay_high);

		getPost_trigger_delay_high().load_value(delay_high);
		getPost_trigger_delay_low().load_value(delay_low);
	}
	
	public void set_scan_range(int scan_range) {
		int scan_range_high = get_higher_byte(scan_range);
		int scan_range_low = get_lower_byte(scan_range, scan_range_high);

		getScan_range_low().load_value(scan_range_low);
		getScan_range_high().load_value(scan_range_high);
	}
	
	public void set_capture_counter(int capture_counter) {
		int intermediate_value = get_higher_byte(capture_counter);
		int first_byte = get_lower_byte(capture_counter, intermediate_value);

		int previous_intermediate_value = intermediate_value;
		intermediate_value = get_higher_byte(intermediate_value);
		int second_byte = get_lower_byte(previous_intermediate_value, intermediate_value);

		previous_intermediate_value = intermediate_value;
		intermediate_value = get_higher_byte(intermediate_value);
		int third_byte = get_lower_byte(previous_intermediate_value, intermediate_value);

		previous_intermediate_value = intermediate_value;
		intermediate_value = get_higher_byte(intermediate_value);
		int fourth_byte = get_lower_byte(previous_intermediate_value, intermediate_value);

		getCapture_counter_b0().load_value(first_byte);
		getCapture_counter_b1().load_value(second_byte);
		getCapture_counter_b2().load_value(third_byte);
		getCapture_counter_b3().load_value(fourth_byte);
	}

	public void set_slow_clock_sample_counter(int slow_clock_sample_counter_value) {
		int slow_clock_sample_counter_value_high = get_higher_byte(slow_clock_sample_counter_value);
		int slow_clock_sample_counter_value_low = get_lower_byte(slow_clock_sample_counter_value,
				slow_clock_sample_counter_value_high);

		getSlow_clock_sample_counter_b0().load_value(slow_clock_sample_counter_value_low);
		getSlow_clock_sample_counter_b1().load_value(slow_clock_sample_counter_value_high);
	}
	
	public String make_setup_string() {
		String setup_string = "";

		setup_string += make_load_string_for_register(getSample_preload_low());
		setup_string += make_load_string_for_register(getSample_preload_high());
		setup_string += make_load_string_for_register(getTrigger_logic());
		setup_string += make_load_string_for_register(getTrigger_mask());
		setup_string += make_load_string_for_register(getSpock_option());
		setup_string += make_load_string_for_register(getCounter_capture_low());
		setup_string += make_load_string_for_register(getCounter_capture_high());
		setup_string += make_load_string_for_register(getIntput_attenuation());
		setup_string += make_load_string_for_register(getDump_size());

		setup_string += make_load_string_for_register(getTrace_mode());
		setup_string += make_load_string_for_register(getPost_trigger_delay_high());
		setup_string += make_load_string_for_register(getPost_trigger_delay_low());
		setup_string += make_load_string_for_register(getTime_base_expansion());
		setup_string += make_load_string_for_register(getPre_trigger_delay());

		setup_string += make_load_string_for_register(getAnalog_mode_select());
		setup_string += make_load_string_for_register(getMark_bytes());
		setup_string += make_load_string_for_register(getSkip_bytes());
		setup_string += make_load_string_for_register(getRepeat_count());

		setup_string += make_load_string_for_register(getEvent_mode_select());
		setup_string += make_load_string_for_register(getScan_range_low());
		setup_string += make_load_string_for_register(getScan_range_high());
		setup_string += make_load_string_for_register(getPositive_transition_level());
		setup_string += make_load_string_for_register(getNegative_transition_level());

		setup_string += make_load_string_for_register(getDelayed_trace_mode_select());
		setup_string += make_load_string_for_register(getDelay_counter_b0());
		setup_string += make_load_string_for_register(getDelay_counter_b1());
		setup_string += make_load_string_for_register(getDelay_counter_b2());
		setup_string += make_load_string_for_register(getDelay_counter_b3());

		setup_string += make_load_string_for_register(getCapture_counter_b0());
		setup_string += make_load_string_for_register(getCapture_counter_b1());
		setup_string += make_load_string_for_register(getCapture_counter_b2());
		setup_string += make_load_string_for_register(getCapture_counter_b3());
		setup_string += make_load_string_for_register(getSlow_clock_sample_counter_b0());

		setup_string += make_load_string_for_register(getSlow_clock_sample_counter_b1());
		setup_string += make_load_string_for_register(getPre_trigger_filter());
		setup_string += make_load_string_for_register(getPost_trigger_filter());
		setup_string += make_load_string_for_register(getLogic_trace_mode_select());
		setup_string += make_load_string_for_register(getLogic_sample_rate());

		setup_string += ">";

		return setup_string;
	}

	// private functions
	private String make_load_string_for_register(Register register) {
		String formatted_string = "[" + String.format("%02x", register.register_address) + "]@["
				+ String.format("%02x", register.register_value) + "]s";
		return formatted_string;
	}
	
	private int get_lower_byte(int value, int higher_byte) {
		int lower_byte = value - higher_byte * 256;
		return lower_byte;
	}

	private int get_higher_byte(int value) {
		int higher_byte = 0;
		if (value >= 256) {
			higher_byte = value / 256;
		}
		return higher_byte;
	}
}