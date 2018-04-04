package ie.gmit.sw.ai;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class SimulatedAnnealing {
	
	private SecureRandom r;
	private Playfair pf;
	private Grams g;
 	
	private Key key;
	
	private int temperature;
	
	private Map<String, Integer> nGrams; 
	
	public SimulatedAnnealing(int temperature, String cipherText) {
		super();
		this.r = new SecureRandom();
		this.g = new Grams("4grams.txt");
		this.pf = new Playfair(cipherText);
		this.key = Key.keyInstance();
		this.temperature = temperature;
		this.nGrams = new HashMap<String, Integer>(); 

	}// construct
	
	public void annealing(String cipherText) throws Throwable {		
		
		nGrams = g.loadNGrams();
		String parent = key.generateKey();						// generate our key
		System.out.println(parent);
		String decryptedText = pf.decrypt(parent);				// decrypt text using said key
		double parentScore = g.scoreText(decryptedText);		// score the decrypted text
		double bestScore = parentScore;							// set the preliminary best score
		System.out.println("Initial score: "+bestScore + " for key: "+ parent);
		
		for(int temp = temperature; temp > 0; temp--) {
			for (int index = 50000; index > 0; index--) {
				String child = key.shuffleKey(parent);			//  Change the parent key slightly to get child key, 
				decryptedText = pf.decrypt(child);				// decrypt with the child key
				double childScore = g.scoreText(decryptedText);	// Measure the fitness of the deciphered text using the child key	
				double delta = childScore - parentScore;		// get the delta 	
				if(delta > 0) {									// if the delta is over 0 this key is better
					parent = child;
					parentScore = childScore;

				} else  {
					if(Math.exp(delta/temp) > r.nextDouble()) { // prevent getting stuck
						parent = child;
						parentScore = childScore;
					}
				}
			
				if(parentScore > bestScore) {
					bestScore = parentScore;
					String bestKey = parent;
					System.out.printf("\nTransition: %d at Temp: %d\nBest Score: %.2f\tFor Key: %s\nDecrypted message: %s\n", index, temp, bestScore, bestKey, decryptedText);
					
				}//if p > b	
			}//transitions
			System.out.println(temp);
			
			if(bestScore == parentScore && temp < temp/2) {
				System.out.printf("\nTemp: %d\nBest Score: %.2f\tFor Key: %s\nDecrypted message: %s\n", temp, bestScore, parent, decryptedText);
				new FileHandler().writeFile(decryptedText);
				break;
			}
		}//tempurature
	}// annealing
	

}


