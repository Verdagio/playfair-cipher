package ie.gmit.sw.ai;

import java.security.SecureRandom;

import ie.gmit.sw.ai.cipher.Playfair;
import ie.gmit.sw.ai.singleton.Key;
import ie.gmit.sw.ai.singleton.nGrams;

public class SimulatedAnnealing {

	private SecureRandom r;
	private Playfair pf;
	private nGrams g;
	private Key key;
	private int temperature;

	private String bestKey;
	private String bestTxt;
	private String statistics;

	public SimulatedAnnealing(int temperature, String cipherText) {
		super();
		this.r = new SecureRandom();
		this.pf = new Playfair(cipherText);
		this.g = nGrams.getInstance("4grams.txt");
		this.key = Key.getInstance();
		this.statistics = "";
		this.temperature = temperature;
	}// construct

	/*
	 * In the annealing function we will do the following Load our quadGrams
	 * training set into our local map of types <String, Integer> From there we need
	 * a key to start, set the parent key to a randomly generated key Use this key
	 * to decipher the the text, and score the deciphered text for its
	 * (english-ness) set our initial & best score.
	 * 
	 * Loop for x amount of iterations until we reach 0, nested loop of transitions
	 * to be made to the key until we reach 0. Set our child key to a permutation of
	 * the parent key using our key.shuffle mehtod. Use this new key to decipher the
	 * text, and score the deciphered text using the quadGram map. Get the delta
	 * fitness by subtracting the parent score value from the child score. If the
	 * delta is greater than zero, the child score is better than the parent, the
	 * child is more fit, then we always accept it. Otherwise the child is worse,
	 * but to avoid heuristic plateaxing we will take a worse child if its
	 * probability is close to 1 we will almost certainly accept the unfit child, if
	 * the value is close to 0 we will almost certainly reject the unfit child key.
	 * If the value of e^(delta/temp) is closer to one, which means we accept poor
	 * children with higher probability. As the program continues, the value of temp
	 * decreases, and the value of e^(delta/temp) approaches zero. This means
	 * towards the end of the program we very rarely accept poor children but still
	 * always accept children that are better than the parent.
	 * 
	 * From here we now check in with the best score, if this is less than the
	 * parent we have obviously found a more fit key therefore we want to take these
	 * values as they are the creme de la creme!
	 * 
	 * In the Interest of time complexity we will break the loop if the best Score
	 * is roughly 40% better than our initial score At this point the text is pretty
	 * much perfect english and is not likely to get any better. Because we will
	 * rarely accept children as the temperature decreases, the SA will become more
	 * similar to the hill-climbing algorithm.
	 * 
	 * @throws Throwable
	 */
	public void simulatedAnnealing() throws Throwable {
		g.loadNGrams();
		String parent = key.generateKey();
		String decryptedText = pf.decrypt(parent);
		double parentScore = g.scoreText(decryptedText);
		double bestScore = parentScore;
		double initScore = bestScore;
		System.out.println("Initial score: " + initScore + " for key: " + parent);

		for (int temp = temperature; temp > 0; temp--) {
			for (int index = 45000; index > 0; index--) {
				String child = key.shuffleKey(parent);
				decryptedText = pf.decrypt(child);
				double childScore = g.scoreText(decryptedText);
				double delta = childScore - parentScore;
				if (delta > 0) {
					parent = child;
					parentScore = childScore;
				} else {
					if ((Math.exp((delta / temp))) > r.nextDouble()) {
						parent = child;
						parentScore = childScore;
					} // if
				} // if else

				if (parentScore > bestScore) {
					bestScore = parentScore;
					this.bestKey = parent;
					this.bestTxt = decryptedText;
					System.out.printf(
							"\nTransition: %d at Temp: %d\nBest Score: %.2f\tFor Key: %s\nDecrypted message: %s\n",
							index, temp, bestScore, bestKey, bestTxt);
				} // if p > b
			} // transitions
			System.out.println("temp: " + temp);
			if (bestScore > (initScore / 1.55)) {
				break;
			} // escape the loop
		} // temperature
		setStatistics(
				"Best Score: " + bestScore + " For key: " + this.bestKey + "\nDeciphered message: " + this.bestTxt);
	}// annealing

	public void setStatistics(String statistics) {
		this.statistics = statistics;
	}

	public String getStatistics() {
		return this.statistics;
	}

}
