import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class Memory {
	private Vector<Integer> memory;
	
	private CPU.Register MAR;
	private CPU.Register MBR;
	public Memory() {
		this.memory = new Vector<Integer>();
		Scanner scanner;
		try {
			scanner = new Scanner(new File("code/exe2"));
			while (scanner.hasNext()) {
				String line = scanner.next();
				if (!line.startsWith("//")&&!line.startsWith("$")) {
					System.out.println(line);
					memory.add(Integer.parseInt(line));
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void associate (CPU.Register MAR, CPU.Register MBR) {
		this.MAR = MAR;
		this.MBR = MBR;
	}
	public void load() {
		int address = MAR.getValue();
		MBR.setValue(this.memory.get(address));
	}
	
	public void store() {
		int address = MAR.getValue();
		int value = MBR.getValue();
		this.memory.set(address, value);
	}
}
