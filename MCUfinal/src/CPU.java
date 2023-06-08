import java.util.Stack;

public class CPU {
	public enum EState {
		eStopped,
		eRunning
	}
	Stack<Integer> stack = new Stack<>();
	private Memory memory;
	public void assocate(Memory memory) { this.memory = memory;}
	
	private EState eState;
	// registers
	private IR ir;
	public Register mar, mbr;
	public Register cs,ds,ss,hs, pc, ac,ac2,sp;

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
	}
	private void fetch() {
		//System.out.println("fetch");


		mar.setValue(cs.getValue()+pc.getValue());
		memory.load();
		ir.setValue(mbr.getValue());
	}
	private void decode() {
		//System.out.println("decode");
		// load operand
		//System.out.println(ir.getValue());
		//System.out.println("오퍼레이터"+ir.getOperator());
		//System.out.println("오퍼랜드"+ir.getOperand());

	}
	private void execute() {
		pc.setValue(pc.getValue() + 1);
		//System.out.println("execute");
		switch (ir.getOperator()) {
		case 0:
			this.halt();
			break;
		case 1:
			this.load();
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
		case 5:
			this.move();
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
			this.loadC();
			break;
		default:
			break;
		}
	}
	private void divC() {
		ac.setValue(ac.getValue()/ir.getOperand());
		
	}
	private void loadC() {
		ac.setValue(ir.getOperand());
	}
	private void loadR() {
		this.ac.setValue(stack.get(0));
		
	}
	private void out() {
		System.out.println("확성기");
		System.out.println(ac.getValue());
		System.out.println("확성기");

		
	}
	private void newObject() {
			this.memory.addHeap(ir.getOperand()/4);
	}
	private void push() {
		this.stack.push(ir.getOperand());
		
	}
	private void call() {
		this.stack.push(this.pc.getValue());
		this.pc.setValue(this.ir.getOperand());
	}
	private void ret() {
		this.pc.setValue(this.stack.get(ir.getOperand()/4));
		this.stack= new Stack<>();
	}
	private void addR() {
		ac.setValue(ac.getValue()+ac2.getValue());
		
	}
	private void move() {
		
		
	}
	private void moveR() {
		if(ir.getOperand()==2) {
			ac2.setValue(ac.getValue());
		}
		
	}
	private void storeO() {
		this.mar.setValue(ir.getOperand()/4);
		this.mbr.setValue(ac.getValue());
		this.memory.storeHeap();
		
	}
	private void loadO() {
		this.mar.setValue(ir.getOperand()/4);
		this.memory.loadHeap();
		this.ac.setValue(mbr.getValue());
	}
	private void load() {
		
		
	}
	public void add() {
		mar.setValue(ir.getOperand());
		memory.load();		
		ac.setValue(ac.getValue() + mbr.getValue());
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
}
