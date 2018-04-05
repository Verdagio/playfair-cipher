package ie.gmit.sw.ai;

import java.util.Scanner;

public class CipherBreaker {

	public static void main(String[] args) throws Exception, Throwable {
		Scanner input = new Scanner(System.in);	
		System.out.println("Playfair Cipher Decryptor / encryptor");
		int choice = 0;
		boolean exit = false;
		String fileName = "";
		
		
		do {
			System.out.printf("Please choose yout operatioin\n1\tDecrypt a file\n2\tEncrypt a file\n3\tExit\n");
			choice = input.nextInt();
			
			switch(choice) {
			case 1:
				System.out.println("Please enter your filename including extension (eg: hello.txt): ");
				fileName = input.next();
				String cipherText = new FileHandler().readFile(fileName);	
				System.out.println(cipherText.length());
				System.out.println("This may take a moment...");
				for(int i = 3 ; i >0; i--) {
					Thread.sleep(1000);
					System.out.println(i);
				}
					long startTime = System.currentTimeMillis();
					SimulatedAnnealing sa = new SimulatedAnnealing(20, cipherText);
					sa.simulatedAnnealing();
					long estimatedTime = System.currentTimeMillis() - startTime;
					System.out.println("Executed in: " + estimatedTime / 1000 + " Seconds");
					System.out.println("enter file name including extension(eg. myfile.txt): ");	
					fileName = "";
					fileName = input.next();
					new FileHandler().writeFile("Executed in: " + estimatedTime / 1000 + " Seconds\n" +  sa.getStatistics(), fileName);
				break;
			case 2: 
				System.out.println("Please enter your filename including extension (eg: hello.txt): ");
				fileName = input.next();
				String plainText = new FileHandler().readFile(fileName);
				System.out.println("Generating a random key...");
				String key = Key.keyInstance().generateKey();
				Playfair pf = new Playfair(plainText);
				System.out.println("enter file name including extension(eg. myfile.txt): ");	
				fileName = "";
				fileName = input.next();
				long start = System.currentTimeMillis();
				new FileHandler().writeFile(pf.encrypt(key), fileName);
				long end = System.currentTimeMillis() - start;
				System.out.println("Done");
				System.out.println("Encrypted using the key: " + key + "  - In : "  + end + " ms");
				break;
			case 3:
				exit = true;
				break;
			}// switch
		}while(!exit);
		
		System.out.println("Bye bye...");
	}

}
