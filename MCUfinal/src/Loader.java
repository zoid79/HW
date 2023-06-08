import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Loader {
	private Memory memory;
	private CPU.Register CS;
	private CPU.Register SS;
	private CPU.Register HS;
	private CPU.Register DS;
	
	public enum ESymbolType {
		eVariable,
		eLabel,
		eRegister
	}
	class SymbolEntity {
		public SymbolEntity(ESymbolType eSymbolType, int value) {
			this.eSymbolType = eSymbolType;
			this.value = value;
		}
		public ESymbolType eSymbolType;
		public int value;
	}
	private HashMap<String, SymbolEntity> symbolTable;

	public Loader() {
		symbolTable = new HashMap<String, SymbolEntity>();
	}
	
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
			System.out.println(line);
			String[] tokens = line.split("[ \t]");
			if (tokens[0].charAt(0)=='$') {
				if (tokens[0].charAt(1) == 'D') {
					DS.setValue(Integer.parseInt(tokens[1]));
				} else if (tokens[0].charAt(1) == 'S') {
					SS.setValue(Integer.parseInt(tokens[1]));
				} else if (tokens[0].charAt(1) == 'H') {
					HS.setValue(Integer.parseInt(tokens[1]));		
				}
				else if (tokens[0].charAt(1) == 'C') {
					CS.setValue(Integer.parseInt(tokens[1]));		
				}
			} else {
				try{
					if(tokens.length==1) {
						this.memory.csadd(Integer.parseInt(tokens[0]),0);
					}else 
					this.memory.csadd(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]));

				}
				catch (NumberFormatException ex) {
					this.memory.dsadd(tokens);
				}
				
			}
		}
		
		
		this.memory.show();
	}
	private String[] getTokens(String line) {
		String[] tokens = line.split("[ \t]*");
		return tokens;
	}


	public void associate(Memory memory) {
		this.memory=memory;
		
	}

	public void associate(CPU.Register cs, CPU.Register ds, CPU.Register ss, CPU.Register hs) {
		System.out.println(cs);
		this.CS=cs;
		this.HS=hs;
		this.SS=ss;
		this.DS=ds;
		
	}


}
