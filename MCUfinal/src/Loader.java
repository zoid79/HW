import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

public class Loader {
	private Memory memory;

	private CPU cpu;
	public void load() {
		Scanner scanner;
		try {
			scanner = new Scanner(new File("code/exe2"));
			parse(scanner);
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void parse(Scanner scanner) {
		while(scanner.hasNext()) {
			String line = scanner.nextLine();
			String[] tokens = line.split("[ \t]");
			if (tokens[0].charAt(0)=='$') {
				if (tokens[0].charAt(1) == 'D') {
					int address=this.cpu.getUnusedMemory(cpu.ds);
					if(address==-1)System.exit(0);
					System.out.println(address+"--DS");

					
				} else if (tokens[0].charAt(1) == 'S') {
					int address=this.cpu.getUnusedMemory(cpu.ss);
					if(address==-1)System.exit(0);
					System.out.println(address+"--SS");

					
				} else if (tokens[0].charAt(1) == 'H') {
					int address=this.cpu.getUnusedMemory(cpu.hs);
					if(address==-1)System.exit(0);
					System.out.println(address+"--HS");
	
				}
				else if (tokens[0].charAt(1) == 'C') {
					int address=this.cpu.getUnusedMemory(cpu.cs);
					if(address==-1)System.exit(0);
					System.out.println(address+"--CS");
	
				}
			} else {
				try{
					if(tokens.length==1) {
						this.memory.csadd(cpu.memoryTable,Integer.parseInt(tokens[0]),0);
					}else 
					this.memory.csadd(cpu.memoryTable,Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]));

				}
				catch (NumberFormatException ex) {
					this.memory.dsadd(cpu.memoryTable,tokens);
				}
				
			}
		}
	}

	public void associate(Memory memory) {
		this.memory=memory;
		
	}

	public void associate(CPU cpu) {
		this.cpu = cpu;	
	}


}
