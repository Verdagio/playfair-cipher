package ie.gmit.sw.ai;

import java.util.LinkedList;
import java.util.List;

public class SimulatedAnnealing {
	
	private LinkedList<String> shingles;
	
	private Key key;
	private String pKey;
	private String cKey;
	
	private int tempurature;
	private int transitions;
	private int steps;
	private double fitness;
	
	public SimulatedAnnealing(int tempurature, int transitions, int steps, double fitness, String key) {
		super();
		this.key = Key.keyInstance(key);
		shingles = new LinkedList<String>();
		this.tempurature = tempurature;
		this.transitions = transitions;
		this.steps = steps;
		this.fitness = fitness;
	}// construct
	
	public int generateShingles(String cipherText, int index) {
		if(index < cipherText.length() - 4) {
			shingles.add(cipherText.substring(index, index + 4));
			return generateShingles(cipherText, 1 + index);
		} else return 0; 
	}// recursive shingler 

	public void annealing() {
		
		pKey = key.generateKey();
		
		
		
	}
	
	public static double logProbability(int fitness, int nextFitness, double distance) {	
		return (nextFitness > fitness) ? 1.0 : Math.log10((fitness - nextFitness) / 4899000); 
	}// return probability
}
