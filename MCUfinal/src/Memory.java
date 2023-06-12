import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

public class Memory {
	private Vector<Integer> memory;
	private Vector<Integer> heapmemory;
	private CPU.Register MAR;
	private CPU.Register MBR;
	public CPU.Register ds;
	public CPU.Register ss;
	public CPU.Register hs;
	public CPU.Register cs;

	public Memory() {
		this.memory = new Vector<Integer>(1000);
		this.memory.setSize(1000);
		for(int i=0;i<1000;i++) {
			this.memory.set(i, -1);
		}
		for(int i=100;i<200;i++) {
			this.memory.set(i, 0);
		}
		this.heapmemory = new Vector<Integer>();	
	}
	public void associate (CPU cpu) {
		this.MAR = cpu.mar;
		this.MBR = cpu.mbr;
		this.hs=cpu.hs;
		this.cs=cpu.cs;
		this.ss=cpu.ss;
		this.ds=cpu.ds;

		
	}
	public void load() {
		MBR.setValue(this.memory.get(this.MAR.getValue()));
	}
	
	public void store() {
		int address = MAR.getValue();
		int value = MBR.getValue();
		this.memory.set(address, value);
	}

	public void csadd(Vector<Integer> memoryTable, int operator, int operand) {
		for(int i=0;i<100;i++) {
			if(this.memory.get(memoryTable.get(this.cs.getValue())*100+i)==-1) {
			this.memory.set(memoryTable.get(this.cs.getValue())*100+i,operand*100+operator);
			break;}
		}
	}
	public void dsadd(Vector<Integer> memoryTable, String[] tokens) {
		for(int i=0;i<100;i++) {
			if(this.memory.get(memoryTable.get(this.ds.getValue())*100+i)==-1) {
			this.memory.set(memoryTable.get(this.ds.getValue())*100+i,Integer.parseInt(tokens[1]));
			break;
			}
		}

	}
	public void add(int index) {
		this.memory.set(index, null);
	}
	public void setSize(int value) {
		this.memory.setSize(value);
		
	}
	
	public void storeHeap() {
		this.heapmemory.set(this.MAR.getValue(), this.MBR.getValue());
		
	}
	public void loadHeap() {
		MBR.setValue(this.heapmemory.get(this.MAR.getValue()));		
	}
	public boolean checkEmpty() {
		if(this.memory.get(this.MAR.getValue())==-1)
			return true;
		else
			return false;
		
	}
}
