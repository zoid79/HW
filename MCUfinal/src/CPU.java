
public class CPU {
	public enum EState {
		eStopped,
		eRunning
	}
	
	private Memory memory;
	public void assocate(Memory memory) { this.memory = memory; }
	
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
		System.out.println("fetch");
		mar.setValue(cs.getValue()+pc.getValue());
		memory.load();
		ir.setValue(mbr.getValue());
	}
	private void decode() {
		System.out.println("decode");
		// load operand
	}
	private void execute() {
		pc.setValue(pc.getValue() + 1);
		System.out.println("execute");
		switch (ir.getOperator()) {
		case 1:
			this.halt();
			break;
		case 2:
			this.load();
			break;
		case 3:
			this.loadO();
			break;
		case 4:
			this.storeO();
			break;
		case 5:
			this.moveR();
			break;
		case 6:
			this.move();
			break;
		case 7:
			this.addR();
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
		default:
			break;
		}
	}
	private void loadR() {
		
		
	}
	private void out() {
		
		
	}
	private void newObject() {
		
		
	}
	private void push() {
		
		
	}
	private void call() {
		// TODO Auto-generated method stub
		
	}
	private void ret() {
		// TODO Auto-generated method stub
		
	}
	private void addR() {
		// TODO Auto-generated method stub
		
	}
	private void move() {
		// TODO Auto-generated method stub
		
	}
	private void moveR() {
		// TODO Auto-generated method stub
		
	}
	private void storeO() {
		// TODO Auto-generated method stub
		
	}
	private void loadO() {
		// TODO Auto-generated method stub
		
	}
	private void load() {
		// TODO Auto-generated method stub
		
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
			// checkInterrupt();
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

			return this.value;
		}
		public int getOperand() {
			int operand = value & 0x00FFFFFF;
			return operand;
		}
	}
}
