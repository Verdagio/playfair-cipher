package ie.gmit.sw.ai;

import java.util.HashMap;
import java.util.LinkedList;

public class SimulatedAnnealing {
	
	private LinkedList<String> shingles;
	private Playfair pf;
	private Grams g;
	private String next;
	
	private Key key;
	private String parent;
	private double parentScore;
	private String child;
	private double childScore;
	private String bestKey;
	private double bestScore;
	
	private int tempurature;
	private int transitions;
	private int step;
	private double fitness;
	
	private HashMap<String, Integer> shingleMap; 
	
	public SimulatedAnnealing(int tempurature, int transitions, int steps, String key) {
		super();
		this.g = new Grams("4grams.txt");
		this.pf = new Playfair();
		this.key = Key.keyInstance(key);
		this.shingles = new LinkedList<String>();
		this.tempurature = tempurature;
		this.transitions = transitions;
		this.step = steps;
		this.fitness = 0;
		this.parentScore = 0;
		this.childScore = 0;
		this.bestScore = 0;
	}// construct
	
	public void generateShingles(String cipherText) {
		for(int index = 0; index <= cipherText.length() - 4; index++) {
			this.shingles.add(cipherText.substring(index, index + 4));
			//return generateShingles(cipherText, index++);
		}
		//return 0;
		
	}// recursive shingler 

	public void annealing(String cipherText) throws Throwable {
		double temp = 0;
		
		shingleMap =  (HashMap<String, Integer>) g.loadNGrams();
		
		parent = key.generateKey();
		this.next = pf.decrypt(parent, cipherText);
		generateShingles(cipherText);
		
		for(String shingle : shingles) {
			if(shingleMap.keySet().contains(shingle)) {
				temp += logProbability(shingleMap, shingle);
			}//if
		}//for
		
		System.out.println("4gram len: " + g.getCount());
		this.fitness = temp;
		System.out.println("Score: "+ this.fitness);
		for(int i = tempurature; i > 0; i-= step) {
			//transitions(cipherText, transitions);
			for (int j = 0; j < transitions; j++) {
				child = key.shuffleKey(parent);
				double delta;
				next = pf.decrypt(child, cipherText);
				generateShingles(cipherText);
				
				for(String shingle : shingles) { 
					if(shingleMap.keySet().contains(shingle)) {
						childScore +=  logProbability(shingleMap, shingle); 
					}// if
				}//for
				delta = childScore - fitness;	
				System.out.println(delta);

				if(delta > 0) {
					this.setParent(child);
					this.setParentScore(childScore);
				} else {
					if(Math.exp((delta/tempurature)) > 0.5) { 
						this.setParent(child);
						this.setParentScore(childScore);
					}
				}
				if(parentScore > bestScore) {
					bestScore = parentScore;
					bestKey = parent;
					System.out.printf("\n%d best Score: %f0.3\tFor Key: %s\n", j, bestScore, bestKey);
					if(bestKey.equalsIgnoreCase("THEQUICKBROWNFXMPDVRLAZYDGS")) {
						Thread.sleep(5000);
					}
				}			}
		}//tempurature
		
	}// annealing
	
	public double scoreText(String[] shingles, int index, double score) {
		
		if(index < shingles.length) {
			if(shingleMap.keySet().contains(shingles[index])) {
				score += logProbability(shingleMap, shingles[index]);
			}
			return scoreText(shingles, 1 + index, score);
		}else return score;
		
	}
	
	public int transitions(String cipherText,  int index) throws Throwable {	
		if(index > 0) {
				child = key.shuffleKey(parent);
				double delta;
				this.next = pf.decrypt(child, cipherText);
				generateShingles(cipherText);
				
				for(String shingle : this.shingles) { 
					if(shingleMap.keySet().contains(shingle)) {
						this.childScore += logProbability(shingleMap, shingle);  
					}// if
				}//for
				
				delta = this.childScore - this.fitness;	
				if(delta >= 0) {
					this.setParent(child);
					this.setParentScore(childScore);
				} else {
					if(Math.exp((delta/tempurature)) > 0.5) {
						this.setParent(child);
						this.setParentScore(childScore);
					}
				}
				if(parentScore > bestScore) {
					setBestScore(parentScore);
					setBestKey(parent);
					System.out.printf("%d best Score: %f0.3\tFor Key: %s\n%s", index, getBestScore(), getBestKey(), pf.getPlainText());
				}
				return transitions(pf.getPlainText(), index--);
		}
			return 0;
		
	}
	
	public double logProbability(HashMap <String, Integer> map, String shingle) {
		return  Math.log10(shingleMap.get(shingle).doubleValue()) /  Math.log10(g.getCount());
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public double getParentScore() {
		return parentScore;
	}

	public void setParentScore(double parentScore) {
		this.parentScore = parentScore;
	}

	public String getChild() {
		return child;
	}

	public void setChild(String child) {
		this.child = child;
	}

	public double getChildScore() {
		return childScore;
	}

	public void setChildScore(double childScore) {
		this.childScore = childScore;
	}

	public String getBestKey() {
		return bestKey;
	}

	public void setBestKey(String bestKey) {
		this.bestKey = bestKey;
	}

	public double getBestScore() {
		return bestScore;
	}

	public void setBestScore(double bestScore) {
		this.bestScore = bestScore;
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	
	
	
}


