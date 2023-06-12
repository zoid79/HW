
public class Main {

	public static void main(String[] args) {
		CPU cpu = new CPU();
		Loader loader = new Loader();
		Memory memory = new Memory();
		
		loader.associate(memory);
		loader.associate(cpu);
		cpu.assocate(memory);
		memory.associate(cpu);
		loader.load();
		System.out.println("시작");
		cpu.start();
	}

}
