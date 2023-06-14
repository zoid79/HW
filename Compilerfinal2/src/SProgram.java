

public class SProgram implements INode{
	private SSymbolTable symbolTable;
		private SHeader header;
		private SCodeSegment  codeSegment;
		private SCodeGenerator codeGenerator;
		public SProgram() {
			this.symbolTable=new SSymbolTable();
			this.header =new SHeader(symbolTable);
			this.codeSegment = new SCodeSegment(symbolTable);
		}
		@Override
		public String parse(SLex lex) {
			String tokenString = lex.getToken();
			if(tokenString.compareTo(".data")==0) {
				tokenString=this.header.parse(lex);
			}
			
			if(tokenString.compareTo(".code")==0) {
				tokenString=this.codeSegment.parse(lex);
			}
			this.codeGenerator = new SCodeGenerator(this.header.getdeclarations(),this.codeSegment.getStatements(),this.codeSegment.getSymbolTable());
			this.codeGenerator.generate();
			return null;
		}
		
	}
