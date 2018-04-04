package ie.gmit.sw.ai;

import java.util.Scanner;

public class CipherBreaker {

	public static void main(String[] args) throws Exception, Throwable {
		//Grams n = new Grams("4grams.txt");
		Scanner input = new Scanner(System.in);
		
		System.out.println("Playfair Cipher Cracker");
		int choice = 0;
		boolean exit = false;
		do {
		System.out.printf("Please choose yout operatioin\n1\tDecrypt a file\n2\tencrypt a file\n3\tExit\n");
		choice = input.nextInt();
		
		switch(choice) {
		case 1:
			System.out.println("Please enter your filename (extension not neccessary): ");
			String fileName = input.next();
			String cipherText = new FileParser().readFile(fileName);
			System.out.println("pre decryption: " + cipherText);
			
			break;
		case 2: 
			
			break;
		case 3:
			
			break;
		}
		
		
		}while(!exit);
		
		
		String cipherText = "HFZQLYVEDWNITIQPQDUVHYLGXZHFNYBKPACAZQHFVQIQCUUVYCBXABQZQZURHQDZHBKDMVZQHXRGURLQHTXZQVDFYXZHRGGWHBYEGXNYYEGKYVHFLQDBWDVQIZEAUCAHHPQIBRRVBREZNYYQAHPUQDUVHYZXGNRDEOZWQFKCLZZHXVRDEOFEINQZZKZPKDYDCAMEEQUDBCLDBKPAEDUVYCHFZQQEUMSVPBUMURLQHTXZXZCUHTVTPHMDLDRGMDLDVBHCMGUVYCQVPVDMSZXQCPDIQZLQKDUBEMTCYDDBCQGDFEUKQZVPCYUHKDIABDFVFEETGKIDOZEFURLQUVYCKDPTACYQUCFUPVVBBREZZXDTZPWCMEDILYTHZHADMUDBGQHBKIFEMDEWIZRGVQHTKCNWIEGNHCPLLUDPCOFTQGDPNWBYHCHFQZITQVGKUVYCHFBDQVHVHCHFDIYXHFBRUMLZKDZDFQFHNYLGSAPLQCCAZQHCPCBODITCVBMUHFDIYXHFBRUMLZKDLULIDLIDDLQRKWZQACYQUZBHZBDUBHQZUKUZEDGWTVBXABQZQZBUFEUFFTQVEKZQINAHMEPTDFNYFBIZEXBRRVBREZTCILEVFBEDHUBRWDLYTHFHIZNYCPOVBDLIZQHFQPQDUVHYLGCUNYOKDMPCHTXZPCGCHFDYLQDBLTHPQEKCGKTIQIBRVQHBQNDBRXBZEFRFVUEDQYNYMZCPBDHYLKCUXFTHISISATESTA";
		
		System.out.println("Running in:");
		for(int i = 5 ; i >0; i--) {
			Thread.sleep(1000);
			System.out.println(i);
		}
		
		SimulatedAnnealing sa = new SimulatedAnnealing(10, cipherText);

		try {
			long startTime = System.currentTimeMillis();
			sa.annealing(cipherText);
			long estimatedTime = System.currentTimeMillis() - startTime;
			System.out.println(estimatedTime / 1000 + "Seconds");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		
	}

}