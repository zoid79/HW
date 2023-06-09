import java.util.Stack;
import java.util.Vector;

public class CPU {
	public enum EState {
		eStopped,
		eRunning
	}
	Stack<Integer> stack = new Stack<>();
	private Memory memory;
	public void assocate(Memory memory) { this.memory = memory;
	}
	
	private EState eState;
	// registers
	private IR ir;
	public Register mar, mbr;
	public Register cs,ds,ss,hs, pc, ac,ac2,sp;
	public Vector<Integer> memoryTable;
	public Vector<Integer> unUsedMemory;

	public CPU() {
		ir = new IR();
		mar = new Register();
		mbr = new Register();
		cs = new Register();
		ss = new Register();
		ds = new Register();
		hs = new Register();
		pc = new Register();
		ac = new Register();
		ac2 = new Register();
		sp = new Register();
		this.memoryTable = new Vector<Integer>();
		this.unUsedMemory = new Vector<Integer>();
		this.memoryTable.add(1);
		this.unUsedMemory.add(0);
		this.unUsedMemory.add(2);
		this.unUsedMemory.add(3);
		this.unUsedMemory.add(4);
		this.unUsedMemory.add(5);
		this.unUsedMemory.add(6);
		this.unUsedMemory.add(7);
		this.unUsedMemory.add(8);
		this.unUsedMemory.add(9);
		this.unUsedMemory.add(10);

	}
	private void fetch() {
		mar.setValue((this.memoryTable.get(cs.getValue())*100)+pc.getValue());

		memory.load();
		ir.setValue(mbr.getValue());
	}
	private void decode() {

	}
	private void execute() {
		pc.setValue(pc.getValue() + 1);
		//System.out.println("execute");
		switch (ir.getOperator()) {
		case 0:
			this.halt();
			break;
	
		case 2:
			this.loadO();
			break;
		case 3:
			this.storeO();
			break;
		case 4:
			this.moveR();
			break;
		
		case 6:
			this.addR();
			break;
		case 7:
			this.divC();
			break;
		case 8:
			this.ret();
			break;
		case 9:
			this.call();
			break;
		case 10:
			this.push();
			break;
		case 11:
			this.newObject();
			break;
		case 12:
			this.out();
			break;
		case 13:
			this.loadR();
			break;
		case 14:
			this.loadA();
			break;
		default:
			break;
		}
	}
	private void divC() {
		ac.setValue(ac.getValue()/ir.getOperand());
		
	}
	private void loadA() {
		this.mar.setValue(memoryTable.get(this.ds.getValue())*100+ir.getOperand());
		if(this.mar.getValue()>=100)
			System.exit(0);
		this.memory.load();
		ac.setValue(this.mbr.getValue());
	}
	private void loadR() {
		this.mar.setValue(memoryTable.get(this.ss.getValue())*100+ir.getOperand()/4);
		if(this.mar.getValue()>=500||this.mar.getValue()<300)
		System.exit(0);
		this.memory.load();
		this.ac.setValue(mbr.getValue());
		
	}
	private void out() {
		System.out.println(ac.getValue());
	}
	private void newObject() {
		int count=ir.getOperand()/4;
		this.mbr.setValue(0);
		for(int i=memoryTable.get(this.hs.getValue())*100;count>0;i++) {
			if(this.mar.getValue()>=400||this.mar.getValue()<200)
			System.exit(0);
			this.mar.setValue(i);
			if(this.memory.checkEmpty()) {
				this.memory.store();
				count=count-1;
				}
		}
			
	}
	
	private void push() {
		int count=1;
		this.mbr.setValue(ir.getOperand());
		for(int i=memoryTable.get(this.ss.getValue())*100;count>0;i++) {
			this.mar.setValue(i);
			if(this.mar.getValue()>=500||this.mar.getValue()<300)
			System.exit(0);
			if(this.memory.checkEmpty()) {
				this.memory.store();
				count=count-1;
			}
		}
	}
	private void call() {
		int count=1;
		this.mbr.setValue(pc.getValue());
		for(int i=memoryTable.get(this.ss.getValue())*100;count>0;i++) {
			this.mar.setValue(i);
			if(this.mar.getValue()>=500||this.mar.getValue()<300)
				System.exit(0);
			if(this.memory.checkEmpty()) {
				this.memory.store();
				count=count-1;
			}
		}
		this.mar.setValue(memoryTable.get(this.ds.getValue())*100+ir.getOperand());
		if(this.mar.getValue()>=100)
			System.exit(0);
		this.memory.load();
		this.pc.setValue(this.mbr.getValue());
	}
	private void ret() {
		this.mar.setValue(memoryTable.get(this.ss.getValue())*100+ir.getOperand()/4);
		if(this.mar.getValue()>=500||this.mar.getValue()<300)
			System.exit(0);
		this.memory.load();
		this.pc.setValue(this.mbr.getValue());
		this.mbr.setValue(-1);
		for(int i=this.ss.getValue()*100;i<memoryTable.get(this.ss.getValue())*100+100;i++) {
			mar.setValue(i);
			if(this.mar.getValue()>=500&&this.mar.getValue()<300)
				System.exit(0);
			this.memory.store();
		}
	}
	private void addR() {
		ac.setValue(ac.getValue()+ac2.getValue());
		
	}

	private void moveR() {
		if(ir.getOperand()==2) {
			ac2.setValue(ac.getValue());
		}
		
	}
	private void storeO() {
		this.mar.setValue(memoryTable.get(this.hs.getValue())*100+ir.getOperand()/4);
		if(this.mar.getValue()>=400||this.mar.getValue()<200)
			System.exit(0);
		this.mbr.setValue(ac.getValue());
		this.memory.store();
		
	}
	private void loadO() {
		this.mar.setValue(memoryTable.get(this.hs.getValue())*100+ir.getOperand()/4);
		if(this.mar.getValue()>=400||this.mar.getValue()<200)
			System.exit(0);
		this.memory.load();
		this.ac.setValue(mbr.getValue());
	}


	public void halt() {
		this.eState = EState.eStopped;
	}
	public void start() {
		this.eState = EState.eRunning;
		this.run();
	}

	public void run() {
		while (this.eState == EState.eRunning) {
			this.fetch();
			this.decode();
			this.execute();
		}
	}
	public class Register {
		protected int value;
		public Register() {
			value = 0;
		}
		public int getValue() {
			return value;
		}
		public void setValue(int string) {
			this.value = string;		
		}
		
		
				
	}
	private class IR extends Register {
		public int getOperator() {
			return this.value%100;
		}
		public int getOperand() {
			int operand = value/100;
			return operand;
		}
	}
	public int getUnusedMemory(CPU.Register segment) {
		try {
			int index =this.unUsedMemory.get(0);
			this.memoryTable.add(index);
			this.unUsedMemory.remove(0);
			segment.setValue(this.memoryTable.size()-1);
			return index;
		}catch(IndexOutOfBoundsException e) {
			return -1;
		}
		
	}
}
