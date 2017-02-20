import java.io.UnsupportedEncodingException;

import com.fazecast.jSerialComm.*;

public class Comport_interface {
	// local variables
	private SerialPort comPort;

	// constructors
	public Comport_interface(String port) {
		comPort = SerialPort.getCommPort(port);
	}

	public Comport_interface(String port, int baudrate) {
		comPort = SerialPort.getCommPort(port);
		comPort.setBaudRate(baudrate);
	}

	// comport settings
	public void open_comPort() {
		comPort.openPort();
	}

	public void set_baudrate(int baudrate) {
		comPort.setBaudRate(baudrate);
	}

	public void close_comport() {
		comPort.closePort();
	}

	// comport communication
	public void send_string(String to_send_string) {
		comPort.writeBytes(to_send_string.getBytes(), to_send_string.getBytes().length);
	}

	public String send_string_and_wait_for_response_as_string(String to_send_string) {
		comPort.writeBytes(to_send_string.getBytes(), to_send_string.getBytes().length);
		try {
			wait_for_response(1);
			byte[] readBuffer = read_comport();
			return process_readBuffer(readBuffer);
		} catch (Exception e) {
			return "";
		}
	}
	
	public byte[] send_string_and_wait_for_response_as_bytes(String to_send_string) {
		comPort.writeBytes(to_send_string.getBytes(), to_send_string.getBytes().length);
		try {
			wait_for_response(1);
			byte[] readBuffer = read_comport();
			return readBuffer;
		} catch (Exception e) {
			return null;
		}
	}

	// comport debugging
	public String[] get_available_comports() {
		String[] comports_string = new String[SerialPort.getCommPorts().length];
		int comport_index = 0;
		for (SerialPort available_port : SerialPort.getCommPorts()) {
			comports_string[comport_index++] = available_port.getDescriptivePortName();
		}
		return comports_string;
	}

	// private calls
	private void wait_for_response(int timeout_in_seconds) throws InterruptedException {
		int timeout_ms = timeout_in_seconds * 1000;
		int time_ms = 0;
		while (comPort.bytesAvailable() == 0) {
			Thread.sleep(20);
			if (time_ms >= timeout_ms) {
				break;
			}
			time_ms += 20;
		}
	}

	private byte[] read_comport() {
		byte[] readBuffer = new byte[comPort.bytesAvailable()];
		comPort.readBytes(readBuffer, readBuffer.length);
		return readBuffer;
	}

	private String process_readBuffer(byte[] readBuffer) throws UnsupportedEncodingException {
		String readBufferString = new String(readBuffer, "UTF-8");
		String[] readBufferStringArray = readBufferString.split("[\\r\\n]+");
		String out_ReadBufferString = "";

		for (int i = 1; i < readBufferStringArray.length; i++) {
			out_ReadBufferString += readBufferStringArray[i];
		}

		return out_ReadBufferString;
	}
}