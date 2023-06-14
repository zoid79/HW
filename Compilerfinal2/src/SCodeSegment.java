import java.util.Vector;


public class SCodeSegment implements INode{
		private Vector<SStatement> statements;
		private SSymbolTable symbolTable;
		public SCodeSegment(SSymbolTable symbolTable) {
		this.statements=new Vector<SStatement>();
		this.symbolTable=symbolTable;
		}
		@Override
		public String parse(SLex lex) {
			System.out.println("토큰");

			String[] tokens=lex.getTokens();

			String operator =tokens[0];

			while(operator.compareTo(".end")!=0) {
				if((operator.startsWith("//"))||operator.length()==0) {//skip

				}else if(operator.contains(":")) {//symbol table
					SSymbolEntity entity =new SSymbolEntity();
					entity.setVariableName(operator.replace(":",""));
					//-1? 0? 모르겠다 코드 보고 확정
					entity.setValue(this.statements.size());
					
					this.symbolTable.add(entity);
				}else {//parse tree

					SStatement statement = null;
					switch(tokens.length) {
					case 1:
						 statement =new SStatement(tokens[0]); 

						break;
					case 2:
						 statement =new SStatement(tokens[0], tokens[1]); 

						break;
						default:
							break;
							
					}
					
					this.statements.add(statement);
				}
				tokens=lex.getTokens();
				operator =tokens[0];

			}
			return operator;
		}
		public Vector<SStatement> getStatements() {
			return statements;
		}
		public SSymbolTable getSymbolTable() {
			return symbolTable;
		}
	}