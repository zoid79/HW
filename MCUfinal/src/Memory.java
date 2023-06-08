import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class Memory {
	private Vector<Integer> memory;
	private Vector<Integer> heapmemory;
	private Vector<Integer> stackmemory;
	private CPU.Register MAR;
	private CPU.Register MBR;
	public Memory() {
		this.memory = new Vector<Integer>();
		this.heapmemory = new Vector<Integer>();
		this.stackmemory = new Vector<Integer>();
//		Scanner scanner;
//		try {
//			scanner = new Scanner(new File("code/exe2"));
//			while (scanner.hasNext()) {
//				String line = scanner.next();
//				if (!line.startsWith("//")&&!line.startsWith("$")) {
//					System.out.println(line);
//					memory.add(Integer.parseInt(line));
//				}
//			}
//			scanner.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
	}
	public void associate (CPU.Register MAR, CPU.Register MBR) {
		this.MAR = MAR;
		this.MBR = MBR;
		
	}
	public void load() {

		MBR.setValue(this.memory.get(this.MAR.getValue()));
	}
	
	public void store() {
		int address = MAR.getValue();
		int value = MBR.getValue();
		this.memory.set(address, value);
	}

	public void csadd(int operator, int operand) {
		this.memory.add(operand*100+operator);
		
	}
	public void dsadd(String[] tokens) {
		this.memory.add(Integer.parseInt(tokens[1]));

	}
	public void add(int index) {
		this.memory.set(index, null);
	}
	public void show() {
	//	for(int a:this.memory) {
			//System.out.println(a);
		//}
	}
	public void setSize(int value) {
		this.memory.setSize(value);
		
	}
	public void addHeap(int size) {

		for(int i=0;i<size;i++) {
			this.heapmemory.add(null);
		}
		
	}
	public void storeHeap() {
		this.heapmemory.set(this.MAR.getValue(), this.MBR.getValue());
		
	}
	public void loadHeap() {
		MBR.setValue(this.heapmemory.get(this.MAR.getValue()));		
	}
}
