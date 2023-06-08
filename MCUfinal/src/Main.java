
public class Main {

	public static void main(String[] args) {
		CPU cpu = new CPU();
		Loader loader = new Loader();
		loader.load();
		Memory memory = new Memory();
		

		cpu.assocate(memory);
		memory.associate(cpu.mar, cpu.mbr);
		cpu.start();
	}

}
