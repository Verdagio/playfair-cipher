package ie.gmit.sw.ai;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.LinkedList;

public class SimulatedAnnealing {
	
	private SecureRandom r;
	private Playfair pf;
	private Grams g;
 	
	private Key key;
	
	private int tempurature;
	private int transitions;
	
	private HashMap<String, Integer> nGrams; 
	
	public SimulatedAnnealing(int tempurature, int transitions, String key, String cipherText) {
		super();
		r = new SecureRandom();
		this.g = new Grams("4grams.txt");
		this.pf = new Playfair();
		this.pf.setCipherText(cipherText);
		this.key = Key.keyInstance(key);
		this.tempurature = tempurature;
		this.transitions = transitions;

	}// construct
	
	public void annealing(String cipherText) throws Throwable {		
		
		nGrams =  (HashMap<String, Integer>) g.loadNGrams();	// load our quad grams 
		String parent = key.generateKey();						// generate our key
		String decryptedText = pf.decrypt(parent);				// decrypt text using said key
		double parentScore = scoreText(decryptedText);			// score the decrypted text
		double bestScore = parentScore;							// set the preliminary best score
		
		for(int temp = tempurature; temp > 0; temp--) {
			//transitions(cipherText, parent, parentScore, bestScore, 0);
			for (int index = transitions; index > 0; index--) {
				String child = key.shuffleKey(parent);			// set the child key
				decryptedText = pf.decrypt(child);				// decrypt with the child key
				double childScore = scoreText(decryptedText);	// score the childs version of the decrypted text	
				double delta = childScore - parentScore;		// get the delta 	
			
				System.out.println(delta);
				if(delta > 0) {									// if the delta is over 0 this key is better
					parent = child;
					parentScore = childScore;
				} else {
					if(Math.exp((delta/temp)) > r.nextDouble()) { // prevent getting stuck
						parent = child;
						parentScore = childScore;
					}
				}
			
				if(parentScore > bestScore) {
					bestScore = parentScore;
					String bestKey = parent;
					System.out.printf("\n%d best Score: %f0.3\tFor Key: %s\n%s\n", index, bestScore, bestKey, decryptedText);
				}//if p > b	
			}//transitions
			if(parentScore == bestScore) {
				break;
			}
		}//tempurature
	}// annealing
	
	public double scoreText(String cipherText) {
		double score = 0;
		
		int range = (cipherText.length() > 400) ? 100 : cipherText.length();
		
		for(int i = 0; i < range; i++) {
			String shingle = cipherText.substring(i, (i+4));
			if(nGrams.containsKey(shingle)){
				score = score + Math.log10(nGrams.get(shingle).doubleValue() / g.getCount());
				
			}
		}
		return score;
	}
}


