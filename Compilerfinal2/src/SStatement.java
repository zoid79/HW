
public class SStatement implements INode{
		private String operator;
		private String operand1;
		private String operand2;
		public void setOperator(String operator) {
			this.operator = operator;
		}
		public void setOperand1(String operand1) {
			this.operand1 = operand1;
		}
		public void setOperand2(String operand2) {
			this.operand2 = operand2;
		}
		public SStatement(String operator) {
			this.operator =operator;
		}public SStatement(String operator,String operand1) {
			this.operator =operator;
			this.operand1 =operand1;

		}
		@Override
		public String parse(SLex lex) {
			String[] tokens=lex.getTokens();
			operator=tokens[0];
			if(tokens.length==2) {
				operand1=tokens[1];
			}
			if(tokens.length==3) {
				operand1=tokens[1];
				operand1=tokens[2];
			}
			return operator;
		}
		public String getOperator() {
			return operator;
		}
		public String getOperand1() {
			return operand1;
		}
		public String getOperand2() {
			return operand2;
		}
		
	}