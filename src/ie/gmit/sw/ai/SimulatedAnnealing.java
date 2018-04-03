package ie.gmit.sw.ai;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.LinkedList;

public class SimulatedAnnealing {
	
	private SecureRandom r;
	private Playfair pf;
	private Grams g;
 	
	private Key key;
	
	private int temperature;
	private int transitions;
	
	private HashMap<String, Double> nGrams; 
	
	public SimulatedAnnealing(int temperature, int transitions, String cipherText) {
		super();
		r = new SecureRandom();
		this.g = new Grams("4grams.txt");
		this.pf = new Playfair();
		this.pf.setCipherText(cipherText);
		this.key = Key.keyInstance();
		this.temperature = temperature;
		this.transitions = transitions;

	}// construct
	
	public void annealing(String cipherText) throws Throwable {		
		
		nGrams =  (HashMap<String, Double>) g.loadNGrams();		// load our quad grams 
		String parent = key.generateKey();						// generate our key
		String decryptedText = pf.decrypt(parent);				// decrypt text using said key
		double parentScore = g.scoreText(decryptedText);		// score the decrypted text
		double bestScore = parentScore;							// set the preliminary best score
		System.out.println(bestScore);
		
		for(int temp = temperature; temp > 0; temp --) {
			for (int index = transitions; index > 0; index--) {
				String child = key.shuffleKey(parent);			//  Change the parent key slightly to get child key, 
				decryptedText = pf.decrypt(child);		// decrypt with the child key
				double childScore = g.scoreText(decryptedText);	// Measure the fitness of the deciphered text using the child key	
				double delta = childScore - parentScore;		// get the delta 	
				if(delta > 0) {									// if the delta is over 0 this key is better
					parent = child;
					parentScore = childScore;

				} else  {
					double probability = Math.exp(-delta/temp);
					if(probability > 0.5) { // prevent getting stuck
						parent = child;
						parentScore = childScore;
					}
				}
			
				if(parentScore > bestScore) {
					bestScore = parentScore;
					String bestKey = parent;
					System.out.printf("\nTransition: %d at Temp: %d\nBest Score: %f0.3\tFor Key: %s\nDecrypted message: %s\n", index, temp, bestScore, bestKey, decryptedText);
				}//if p > b	
			}//transitions
			System.out.println(temp);
		}//tempurature
	}// annealing
	

}


