package ie.gmit.sw.ai;

import java.util.Random;

public class Runner {

	public static void main(String[] args) throws Exception {
		//Grams n = new Grams("4grams.txt");
		
		String cipherText = "HFZQLYVEDWNITIQPQDUVHYLGXZHFNYBKPACAZQHFVQIQCUUVYCBXABQZQZURHQDZHBKDMVZQHXRGURLQHTXZQVDFYXZHRGGWHBYEGXNYYEGKYVHFLQDBWDVQIZEAUCAHHPQIBRRVBREZNYYQAHPUQDUVHYZXGNRDEOZWQFKCLZZHXVRDEOFEINQZZKZPKDYDCAMEEQUDBCLDBKPAEDUVYCHFZQQEUMSVPBUMURLQHTXZXZCUHTVTPHMDLDRGMDLDVBHCMGUVYCQVPVDMSZXQCPDIQZLQKDUBEMTCYDDBCQGDFEUKQZVPCYUHKDIABDFVFEETGKIDOZEFURLQUVYCKDPTACYQUCFUPVVBBREZZXDTZPWCMEDILYTHZHADMUDBGQHBKIFEMDEWIZRGVQHTKCNWIEGNHCPLLUDPCOFTQGDPNWBYHCHFQZITQVGKUVYCHFBDQVHVHCHFDIYXHFBRUMLZKDZDFQFHNYLGSAPLQCCAZQHCPCBODITCVBMUHFDIYXHFBRUMLZKDLULIDLIDDLQRKWZQACYQUZBHZBDUBHQZUKUZEDGWTVBXABQZQZBUFEUFFTQVEKZQINAHMEPTDFNYFBIZEXBRRVBREZTCILEVFBEDHUBRWDLYTHFHIZNYCPOVBDLIZQHFQPQDUVHYLGCUNYOKDMPCHTXZPCGCHFDYLQDBLTHPQEKCGKTIQIBRVQHBQNDBRXBZEFRFVUEDQYNYMZCPBDHYLKCUXF";
		System.out.println("pre decryption: " + cipherText);
		Playfair pf = new Playfair();
		long startTime = System.currentTimeMillis();
		//System.out.println(pf.decrypt("the quick brown fox jumped over the lazy dogs", cipherText));
		//n.loadNGrams();
		System.out.println("Running in:");
		for(int i = 5 ; i >0; i--) {
			Thread.sleep(1000);
			System.out.println(i);
		}
	
		SimulatedAnnealing sa = new SimulatedAnnealing(10, 50000, null, cipherText);
		
		try {
			sa.annealing(cipherText);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println(estimatedTime + "ms");
		
		
	}

}
