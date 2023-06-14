import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
public class SCodeGenerator {
	private SSymbolTable headerSymbolTable;
	private SSymbolTable codeSymbolTable;
	Hashtable<String,Integer> symbolTable;
	Hashtable<String,Integer> assemblyTable;
	Hashtable<String,Integer> registerTable;
	private Vector<SStatement> sStatements;
	public SCodeGenerator(SSymbolTable headerSymbolTable, Vector<SStatement> sStatements, SSymbolTable codeSymbolTable) {
		this.headerSymbolTable=headerSymbolTable;
		this.codeSymbolTable=codeSymbolTable;
		this.sStatements=sStatements;
		this.symbolTable = new Hashtable<>();

		for(SSymbolEntity symbolEntity:this.headerSymbolTable) {
			symbolTable.put(symbolEntity.getVariableName(),symbolEntity.getValue());
		}
		for(SSymbolEntity symbolEntity:this.codeSymbolTable) {
			symbolTable.put(symbolEntity.getVariableName(),symbolEntity.getValue());
		}
		this.assemblyTable = new Hashtable<>();
		assemblyTable.put("Halt", 0);
		assemblyTable.put("LoadO", 2);
		assemblyTable.put("StoreO", 3);
		assemblyTable.put("MoveR", 4);
		assemblyTable.put("AddR", 6);
		assemblyTable.put("DivC", 7);
		assemblyTable.put("Ret", 8);
		assemblyTable.put("Call", 9);
		assemblyTable.put("Push", 10);
		assemblyTable.put("New", 11);
		assemblyTable.put("Out", 12);
		assemblyTable.put("LoadR", 13);
		assemblyTable.put("LoadA", 14);
		

		this.registerTable = new Hashtable<>();
		registerTable.put("ac2", 2);
		

		
		System.out.println("심볼 테이블");
		 Enumeration<String> enumKey = symbolTable.keys();
		 while(enumKey.hasMoreElements()){
	            String key = enumKey.nextElement();
	            Integer value = symbolTable.get(key);
	            System.out.print(key+" ");
	            System.out.println(value);
	        }

		
		 
	}
	public void generate() {
		 System.out.println("기계어");
		 int hs = 0;

		 for(SStatement a:this.sStatements) {
		//	 System.out.println(a.getOperator()+" "+a.getOperand1()+" "+a.getOperand2());

			 String operator=a.getOperator();
			 int j;
			 String operand1=a.getOperand1();
			 if(operand1==null)
				 a.setOperand1("");
			 else if(operand1.charAt(0)=='@') {
				 int i=0;
				 for(String key:this.symbolTable.keySet()) {
					 if(key.equals(operand1))
					 break;
					 i++;
				 }
				 a.setOperand1(Integer.toString(i));
			 }
			 else if(operand1.equals("ac2")) {
				 j=this.registerTable.get(operand1);
				 a.setOperand1(Integer.toString(j));
			 }else if(operator.equals("Call")){
				 int i=0;
				 for(String key:this.symbolTable.keySet()) {
					 if(key.equals(operand1))
					 break;
					 i++;
				 }
				 a.setOperand1(Integer.toString(i));
			 }
			 
			 if(operator.equals("New")) {

				hs=hs+Integer.parseInt(operand1); 
			 }
			// System.out.println(operator);
			 j=this.assemblyTable.get(operator);
			 a.setOperator(Integer.toString(j));
			 System.out.println(a.getOperator()+" "+a.getOperand1());

		 }
		 
		 System.out.println("$DS "+this.symbolTable.size());
		 System.out.println("$CS "+this.sStatements.size());
		 System.out.println("$HS "+hs);
		 System.out.println("$SS "+6);
		 
	}

}
