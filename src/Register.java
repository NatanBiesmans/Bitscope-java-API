public class Register {
	int register_value = 0x00;
	int register_address = 0x00;
	
	public Register(int register_value, int register_address){
		this.register_value = register_value;
		this.register_address = register_address;
	}
	
	// register address	
	public void set_register_address(int register_address){
		this.register_address = register_address;
	}
	
	public int get_register_address(){
		return register_address;
	}
	
	//register value
	public void set_bit(int bit_index){
		register_value = register_value | (1 << bit_index);
	}
	
	public void unset_bit(int bit_index){
		register_value = register_value & ~(1 << bit_index);
	}
	
	public void load_value(int to_load_register){
		register_value = to_load_register;
	}
	
	public int get_register_value(){
		return register_value;
	}
	
	//print register content
	public void print(){
		System.out.println("Register address: 0x" + String.format("%02x", register_address) + " ,Value: 0x" + String.format("%02x", register_value));
	}
}
