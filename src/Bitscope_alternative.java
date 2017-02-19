import com.fazecast.jSerialComm.SerialPort;

public class Bitscope_alternative {

	SerialPort comPort;

	public Bitscope_alternative(String port, int baudRate) {
		System.out.println("Initialising...");

		comPort = SerialPort.getCommPort(port);
		comPort.setBaudRate(baudRate);
		comPort.openPort();

		System.out.println("Device found");
		System.out.println("Revision: " + get_revision());
	}

	public void close_bitscope() {
		comPort.closePort();
	}

	public String get_revision() {
		return send_string_and_wait_for_response("?");
	}

	public void reset(){
		send_string("!");
	}
	
	public void vertical(int milli_volt) {
		long volt = milli_volt;
		volt /= 1000;

		if (volt <= 1.1) {
			send_string("64@d6z65s");
			send_string("66@bcz69s");
		} else if (volt <= 3.5) {
			send_string("64@62z52s");
			send_string("66@3fz7ds");
		} else if (volt <= 5.2) {
			send_string("64@68z44s");
			send_string("66@ffz8as");
		} else if (volt <= 11) {
			send_string("64@6az12s");
			send_string("66@8czbas");
		} else {
			// false, incompatible voltage type
		}
	}

	public void horizontal(int prescaler, int divisor) {
		int prescaler_high = get_higher_byte(prescaler);
		int prescaler_low = get_lower_byte(prescaler, prescaler_high);

		send_string("14@" + byte_to_hex_string(prescaler_low) + "z" + byte_to_hex_string(prescaler_high) + "s");
		System.out.println("14@" + byte_to_hex_string(prescaler_high) + "z" + byte_to_hex_string(prescaler_low) + "s");
		int divisor_high = get_higher_byte(divisor);
		int divisor_low = get_lower_byte(divisor, divisor_high);

		send_string("2e@" + byte_to_hex_string(divisor_high) + "z" + byte_to_hex_string(divisor_low) + "s");
		System.out.println("2e@" + byte_to_hex_string(divisor_high) + "z" + byte_to_hex_string(divisor_low) + "s");
	}

	public void trigger_timing(int hold_off_time, int hold_on_time, int timeout_time) {
		int hold_off_time_high = get_higher_byte(hold_off_time);
		int hold_off_time_low = get_lower_byte(hold_off_time, hold_off_time_high);

		send_string("32@" + byte_to_hex_string(hold_off_time_high) + "z" + byte_to_hex_string(hold_off_time_low) + "s");
		System.out.println("32@" + byte_to_hex_string(hold_off_time_high) + "z" + byte_to_hex_string(hold_off_time_low) + "s");
		
		int hold_on_time_high = get_higher_byte(hold_on_time);
		int hold_on_time_low = get_lower_byte(hold_on_time, hold_on_time_high);
		
		
		send_string("34@" + byte_to_hex_string(hold_on_time_high) + "z" + byte_to_hex_string(hold_on_time_low) + "s");
		System.out.println("34@" + byte_to_hex_string(hold_on_time_high) + "z" + byte_to_hex_string(hold_on_time_low) + "s");
		
		int timeout_time_high = get_higher_byte(timeout_time);
		int timeout_time_low = get_lower_byte(timeout_time, timeout_time_high);

		send_string("2c@" + byte_to_hex_string(timeout_time_high) + "z" + byte_to_hex_string(timeout_time_low) + "s");
		System.out.println("2c@" + byte_to_hex_string(timeout_time_high) + "z" + byte_to_hex_string(timeout_time_low) + "s");
	}

	public String trace(int pre_trigger_samples, int post_trigger_samples, int delay_after_trigger) {
		send_string("[7b]@[80]s");
		send_string("[7c]@[80]s");
		send_string("[37]@[01]s");
		send_string("[31]@[00]s");
		send_string("[21]@[00]s");

		int intermediate_value = get_higher_byte(pre_trigger_samples);
		int first_byte = get_lower_byte(pre_trigger_samples, intermediate_value);

		int previous_intermediate_value = intermediate_value;
		intermediate_value = get_higher_byte(intermediate_value);
		int second_byte = get_lower_byte(previous_intermediate_value, intermediate_value);

		previous_intermediate_value = intermediate_value;
		intermediate_value = get_higher_byte(intermediate_value);
		int third_byte = get_lower_byte(previous_intermediate_value, intermediate_value);

		previous_intermediate_value = intermediate_value;
		intermediate_value = get_higher_byte(intermediate_value);
		int fourth_byte = get_lower_byte(previous_intermediate_value, intermediate_value);

		send_string("22@" + byte_to_hex_string(fourth_byte) + "z" + byte_to_hex_string(third_byte) + "z"
				+ byte_to_hex_string(second_byte) + "z" + byte_to_hex_string(first_byte) + "s");

		int post_trigger_samples_high = get_higher_byte(post_trigger_samples);
		int post_trigger_samples_low = get_lower_byte(post_trigger_samples, post_trigger_samples_high);

		send_string("26@" + byte_to_hex_string(post_trigger_samples_high) + "z"
				+ byte_to_hex_string(post_trigger_samples_low) + "s");

		int delay_after_trigger_high = get_higher_byte(delay_after_trigger);
		int delay_after_trigger_low = get_lower_byte(delay_after_trigger, delay_after_trigger_high);

		send_string("2a@" + byte_to_hex_string(delay_after_trigger_high) + "z"
				+ byte_to_hex_string(delay_after_trigger_low) + "s");

		send_string("[06]@[7f]s");
		send_string("[05]@[80]s");
		send_string("[44]@[00]s[45]@[00]s");
		send_string("[68]@[f5]s[69]@[68]s");
		send_string("[07]@[21]s");
		send_string("[3a]@[00]s[3b]@[00]s");
		send_string("[08]@[00]s[09]@[00]s[0a]@[00]s");
		send_string(">");
		send_string("U");
		return send_string_and_wait_for_response("D");
	}

	public String dump(int dump_size) {
		send_string("[31]@[00]s");
		send_string("[08]@[cc]s");
		send_string("[09]@[00]s");
		send_string("[0a]@[00]s");
		send_string("[1e]@[00]s");
		send_string("[30]@[00]s");

		int dump_size_high = get_higher_byte(dump_size);
		int dump_size_low = get_lower_byte(dump_size, dump_size_high);

		send_string("1c@" + byte_to_hex_string(dump_size_high) + "z" + byte_to_hex_string(dump_size_low) + "s");

		send_string("[16]@[01]s");
		send_string("[17]@[00]s");
		send_string("[18]@[01]s");
		send_string("[19]@[00]s");
		send_string("[1a]@[ff]s");
		send_string("[1b]@[ff]s");

		return send_string_and_wait_for_response("A");
	}

	// ------------------------------------------------------------------------------------

	private String send_string_and_wait_for_response(String to_send_string) {
		comPort.writeBytes(to_send_string.getBytes(), to_send_string.getBytes().length);
		try {
			while (comPort.bytesAvailable() == 0)
				Thread.sleep(20);

			byte[] readBuffer = new byte[comPort.bytesAvailable()];
			comPort.readBytes(readBuffer, readBuffer.length);
			try {
				String readBufferString = new String(readBuffer, "UTF-8");
				String[] readBufferStringArray = readBufferString.split("[\\r\\n]+");
				String out_ReadBufferString = "";

				if (to_send_string != "A") {
					for (int i = 1; i < readBufferStringArray.length; i++) {
						out_ReadBufferString += readBufferStringArray[i];
					}
				} else {
					for (int i = 1; i < readBuffer.length; i++) {
						out_ReadBufferString += (Integer.toString(Byte.toUnsignedInt(readBuffer[i])) + "\r\n");
					}
				}
				return out_ReadBufferString;
			} catch (Exception e) {
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void send_string(String to_send_string) {
		comPort.writeBytes(to_send_string.getBytes(), to_send_string.getBytes().length);
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

	private String byte_to_hex_string(int byte_to_convert) {
		return String.format("%02x", byte_to_convert);
	}
}
