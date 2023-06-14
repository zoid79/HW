import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.security.sasl.SaslException;

import org.w3c.dom.ls.LSParser;

public class SMain { 
	private SLex lex;
	private SParser parser;
	
	public SMain() {
		 
	}
	public void initialize() {
		lex = new SLex();
		lex.initialize("source/exe1");
		parser = new SParser();
	}
	public void finalize() {
		lex.finalize();
	}
	public void run() {
		parser.parse(this.lex);
		
	}
	

	public static void main(String[] args) {
		SMain main =new SMain();
		main.initialize();
		main.run();
		main.finalize();

	}

}
