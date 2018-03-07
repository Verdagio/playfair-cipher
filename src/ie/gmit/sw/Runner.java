package ie.gmit.sw;

import java.security.SecureRandom;
import java.util.Random;

public class Runner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello");
		Random r = new SecureRandom();
		Random r2 = new  Random();
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 50; j++) {
				System.out.print(r2.nextInt(25) + " ");
			}
			System.out.println();
		}
	}

}
