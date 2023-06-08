
public class Main {

	public static void main(String[] args) {
		CPU cpu = new CPU();
		Loader loader = new Loader();
		Memory memory = new Memory();
		
		loader.associate(memory);
		loader.associate(cpu.cs, cpu.ds,cpu.ss,cpu.hs);
		cpu.assocate(memory);
		memory.associate(cpu.mar, cpu.mbr);
		loader.load();
		System.out.println("시작");
		cpu.start();
	}

}
