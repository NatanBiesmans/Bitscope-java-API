package Registers;

public class Register {
	int register_value = 0x00;
	private int register_address = 0x00;
	boolean update_pending = false;

	public Register(int register_address, int register_value) {
		this.register_value = register_value;
		this.register_address = register_address;
		this.update_pending = true;
	}

	// register address

	public int getRegister_address() {
		return register_address;
	}

	public void setRegister_address(int register_address) {
		this.register_address = register_address;
	}

	// register value
	public void set_bit(int bit_index) {
		this.update_pending = true;
		register_value = register_value | (1 << bit_index);
	}

	public void unset_bit(int bit_index) {
		this.update_pending = true;
		register_value = register_value & ~(1 << bit_index);
	}

	public void load_value(int to_load_register) {
		this.update_pending = true;
		register_value = to_load_register;
	}

	public int getRegister_value() {
		return register_value;
	}

	public void set_update_pending() {
		update_pending = true;
	}

	public void unset_update_pending() {
		update_pending = false;
	}

	public boolean get_update_pending() {
		return update_pending;
	}

	// print register content
	public void print() {
		System.out.println("Register address: 0x" + String.format("%02x", getRegister_address()) + " ,Value: 0x"
				+ String.format("%02x", register_value) + " , update to bitscope pending: " + update_pending);
	}

}
