# HW

## Description

It consists of a computer with registers, memory, and cpu, and a compiler that can translate an assembly language with one operand into machine language.

Example code made with java
````java
public class Main {

    public static void main(String[] args) {
    	Student kim = new Student();
    	Student jang = new Student();
    	kim.sum();
    	kim.average();
    	averageAll(kim,jang);
    	sumAll(kim,jang);
    }
public static int averageAll(Student student1, Student student2) {
	return (student1.english+student1.math+student2.english+student2.math)/4;
	
}
public static int sumAll(Student student1, Student student2) {
	return (student1.english+student1.math+student2.english+student2.math);
	
}
}
public class Student {
    int math=60;
    int english=70;
    int sum() {
		return math+english;
    	
    }
    int average() {
		return math+english/2;
    	
    }
}
````

### mcu

1. Use cisc-style commands
2. Memory management that combines segmentation and paging methods

### compiler

1. Divide source code by token
2. Lexer analyzes the meaning of tokens
3. Parser creates a parsing tree based on what Lexer analyzed
4. create Header(segment, symbol)

