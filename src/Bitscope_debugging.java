public class Bitscope_debugging {
	Bitscope_registers registers;
	Comport_interface comPort_interface;

	public Bitscope_debugging(Bitscope_registers registers, Comport_interface comport_interface) {
		this.registers = registers;
		this.comPort_interface = comport_interface;

		System.out.println("Device found");
		System.out.println("Revision: " + get_revision());
	}

	public String get_revision() {
		return comPort_interface.send_string_and_wait_for_response_as_string("?");
	}

	public void force_read_all_registers() {
		for (int i = 0; i <= 0xff; i++) {
			Register temp_register = new Register(0x00, i);
			System.out.println("address: " + Integer.toHexString(i) + "\t value: " + load_register_from_bitscope(temp_register));
		}
	}
	
	public void read_registers() {
		get_counter_capture();

		System.out.println(registers.getSample_preload_low().register_address + ": "
				+ load_register_from_bitscope(registers.getSample_preload_low()));
		System.out.println(registers.getSample_preload_high().register_address + ": "
				+ load_register_from_bitscope(registers.getSample_preload_high()));
		System.out.println(registers.getTrigger_logic().register_address + ": "
				+ load_register_from_bitscope(registers.getTrigger_logic()));
		System.out.println(registers.getTrigger_mask().register_address + ": "
				+ load_register_from_bitscope(registers.getTrigger_mask()));
		System.out.println(registers.getSpock_option().register_address + ": "
				+ load_register_from_bitscope(registers.getSpock_option()));
		System.out.println(registers.getCounter_capture_low().register_address + ": "
				+ load_register_from_bitscope(registers.getCounter_capture_low()));
		System.out.println(registers.getCounter_capture_high().register_address + ": "
				+ load_register_from_bitscope(registers.getCounter_capture_high()));
		System.out.println(registers.getIntput_attenuation().register_address + ": "
				+ load_register_from_bitscope(registers.getIntput_attenuation()));
		System.out.println(registers.getDump_size().register_address + ": "
				+ load_register_from_bitscope(registers.getDump_size()));
		System.out.println(registers.getTrace_mode().register_address + ": "
				+ load_register_from_bitscope(registers.getTrace_mode()));
		System.out.println(registers.getPost_trigger_delay_high().register_address + ": "
				+ load_register_from_bitscope(registers.getPost_trigger_delay_high()));
		System.out.println(registers.getPost_trigger_delay_low().register_address + ": "
				+ load_register_from_bitscope(registers.getPost_trigger_delay_low()));
		System.out.println(registers.getTime_base_expansion().register_address + ": "
				+ load_register_from_bitscope(registers.getTime_base_expansion()));
		System.out.println(registers.getPre_trigger_delay().register_address + ": "
				+ load_register_from_bitscope(registers.getPre_trigger_delay()));

		System.out.println(registers.getAnalog_mode_select().register_address + ": "
				+ load_register_from_bitscope(registers.getAnalog_mode_select()));
		System.out.println(registers.getMark_bytes().register_address + ": "
				+ load_register_from_bitscope(registers.getMark_bytes()));
		System.out.println(registers.getSkip_bytes().register_address + ": "
				+ load_register_from_bitscope(registers.getSkip_bytes()));
		System.out.println(registers.getRepeat_count().register_address + ": "
				+ load_register_from_bitscope(registers.getRepeat_count()));

		System.out.println(registers.getEvent_mode_select().register_address + ": "
				+ load_register_from_bitscope(registers.getEvent_mode_select()));
		System.out.println(registers.getScan_range_low().register_address + ": "
				+ load_register_from_bitscope(registers.getScan_range_low()));
		System.out.println(registers.getScan_range_high().register_address + ": "
				+ load_register_from_bitscope(registers.getScan_range_high()));
		System.out.println(registers.getPositive_transition_level().register_address + ": "
				+ load_register_from_bitscope(registers.getPositive_transition_level()));
		System.out.println(registers.getNegative_transition_level().register_address + ": "
				+ load_register_from_bitscope(registers.getNegative_transition_level()));

		System.out.println(registers.getDelayed_trace_mode_select().register_address + ": "
				+ load_register_from_bitscope(registers.getDelayed_trace_mode_select()));
		System.out.println(registers.getDelay_counter_b0().register_address + ": "
				+ load_register_from_bitscope(registers.getDelay_counter_b0()));
		System.out.println(registers.getDelay_counter_b1().register_address + ": "
				+ load_register_from_bitscope(registers.getDelay_counter_b1()));
		System.out.println(registers.getDelay_counter_b2().register_address + ": "
				+ load_register_from_bitscope(registers.getDelay_counter_b2()));
		System.out.println(registers.getDelay_counter_b3().register_address + ": "
				+ load_register_from_bitscope(registers.getDelay_counter_b3()));
		System.out.println(registers.getCapture_counter_b0().register_address + ": "
				+ load_register_from_bitscope(registers.getCapture_counter_b0()));
		System.out.println(registers.getCapture_counter_b1().register_address + ": "
				+ load_register_from_bitscope(registers.getCapture_counter_b1()));
		System.out.println(registers.getCapture_counter_b2().register_address + ": "
				+ load_register_from_bitscope(registers.getCapture_counter_b2()));
		System.out.println(registers.getCapture_counter_b3().register_address + ": "
				+ load_register_from_bitscope(registers.getCapture_counter_b3()));

		System.out.println(registers.getSlow_clock_sample_counter_b0().register_address + ": "
				+ load_register_from_bitscope(registers.getSlow_clock_sample_counter_b0()));
		System.out.println(registers.getSlow_clock_sample_counter_b1().register_address + ": "
				+ load_register_from_bitscope(registers.getSlow_clock_sample_counter_b1()));
		System.out.println(registers.getPre_trigger_filter().register_address + ": "
				+ load_register_from_bitscope(registers.getPre_trigger_filter()));
		System.out.println(registers.getPost_trigger_filter().register_address + ": "
				+ load_register_from_bitscope(registers.getPost_trigger_filter()));
		System.out.println(registers.getLogic_trace_mode_select().register_address + ": "
				+ load_register_from_bitscope(registers.getLogic_trace_mode_select()));
		System.out.println(registers.getLogic_sample_rate().register_address + ": "
				+ load_register_from_bitscope(registers.getLogic_sample_rate()));
	}

	private void get_counter_capture() {
		comPort_interface.send_string("<");
	}

	private String load_register_from_bitscope(Register load_register) {
		return comPort_interface.send_string_and_wait_for_response_as_string(create_load_register_string(load_register));
	}

	private String create_load_register_string(Register load_register) {
		return "[" + String.format("%02x", load_register.register_address) + "]@p";
	}

}
