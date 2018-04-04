package ie.gmit.sw.ai;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Grams {
	
	private String fileName;
	private Map<String, Integer> nGrams;
	private long count;
	
	public Grams(String fileName) {
		this.fileName = fileName;
		this.nGrams = new HashMap<String, Integer>();
	}// Constructor
	
	//reference: https://stackoverflow.com/questions/30125296/how-to-sum-a-list-of-integers-with-java-streams
	public Map<String, Integer> loadNGrams()  throws Exception {
		Stream<String> stream = Files.lines(Paths.get(this.fileName));		
		nGrams = stream.map(tmp -> tmp.split(" ")).collect(Collectors.toMap(tmp -> tmp[0], tmp -> Integer.parseInt(tmp[1])));
		stream.close();
		
		count = nGrams.values().stream().mapToLong(i -> i).sum();
		return this.nGrams;
	}
	
	public double scoreText(String cipherText) {
		double score = 0;
		
		int range = (cipherText.length() < 400) ?  cipherText.length() - 4 : 400 - 4;
		
		for(int i = 0; i < range; i++) {
			Integer frequency = nGrams.get(cipherText.substring(i, i+4));
 			if(frequency != null) {
				score +=  Math.log10((double) frequency / getCount());
			}
		}
		return score;
	}
	
	public void setCount(long count) {
		this.count = count;
	}
	
	public long getCount() {
		return this.count;
	}
	
}
