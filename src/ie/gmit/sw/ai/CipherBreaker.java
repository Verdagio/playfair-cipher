package ie.gmit.sw.ai;

import java.util.Scanner;

public class CipherBreaker {

	public static void main(String[] args) throws Exception, Throwable {
		//Grams n = new Grams("4grams.txt");
		Scanner input = new Scanner(System.in);
		
		System.out.println("Playfair Cipher Cracker");
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
				System.out.println("pre decryption: " + cipherText);
				
				System.out.println("This may take a moment...");
				for(int i = 3 ; i >0; i--) {
					Thread.sleep(1000);
					System.out.println(i);
				}
					long startTime = System.currentTimeMillis();
					SimulatedAnnealing sa = new SimulatedAnnealing(20, cipherText);
					sa.annealing(cipherText);
					long estimatedTime = System.currentTimeMillis() - startTime;
					System.out.println("Executed in: " + estimatedTime / 1000 + " Seconds");
				
				
	
				
				break;
			case 2: 
				System.out.println("Please enter your filename including extension (eg: hello.txt): ");
				fileName = input.next();
				String plainText = new FileHandler().readFile(fileName);
				System.out.println("Please type in a fairly long sentence to be used as a key: ");
				String key = input.next();
				
				break;
			case 3:
				exit = true;
				break;
			}// switch
		}while(!exit);
		
	}

}
