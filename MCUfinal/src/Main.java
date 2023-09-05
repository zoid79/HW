import java.io.File;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		CPU cpu = new CPU();
		Loader loader = new Loader();
		Memory memory = new Memory();
		
		loader.associate(memory);
		loader.associate(cpu);
		cpu.assocate(memory);
		memory.associate(cpu);
		String DATA_DIRECTORY = "code/";
		File dir = new File(DATA_DIRECTORY);
		String[] filenames = dir.list();
		for (String filename : filenames) {
			System.out.println("filename : " + filename);
		}
		System.out.println("원하는 프로그램의 번호를 입력하세요");
		Scanner scanner = new Scanner(System.in);
		boolean flag=true;
		
		while(flag) {
			int index = scanner.nextInt();
			if(index<filenames.length+1&&index>0) {
				flag = false;
				String filename = DATA_DIRECTORY+filenames[index-1];
				loader.load(filename);
			}else {
				System.out.println("다시 입력해주세요");
			}
			
		}
		
		System.out.println("프로그램 시작");
		cpu.start();
	}

}
