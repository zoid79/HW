import java.util.Scanner;
import java.util.Vector;


public class SParser {
	public void associate() {
	}
	public void parse(SLex lex) {
		SProgram program =new SProgram();
		program.parse(lex);
		}

}
