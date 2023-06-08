import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Loader {
	int sizeDS, sizeHS, sizeSS;
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
			parseHeader(scanner);
			parseCode(scanner);
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void parseHeader(Scanner scanner) {
		
		String line = scanner.nextLine();
		String[] tokens = line.split("[ \t]");
		System.out.println(tokens[1]);

		if (tokens[0].charAt(0)=='$') {
			if (tokens[0].charAt(1) == 'D') {
				sizeDS = Integer.parseInt(tokens[1]);
			} else if (tokens[0].charAt(1) == 'S') {
				sizeSS = Integer.parseInt(tokens[1]);
			} else if (tokens[0].charAt(1) == 'H') {
				sizeHS = Integer.parseInt(tokens[1]);			
			}
			else if (tokens[0].charAt(1) == 'C') {
				sizeHS = Integer.parseInt(tokens[1]);			
			}
		} else {
			this.symbolTable.put(tokens[0], 
					new SymbolEntity(ESymbolType.eVariable, Integer.parseInt(tokens[1])));
		}
		
	}
	private String[] getTokens(String line) {
		String[] tokens = line.split("[ \t]*");
		return tokens;
	}

	private void parseCode(Scanner scanner) {
		
		
	}


}
