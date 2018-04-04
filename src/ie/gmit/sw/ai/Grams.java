package ie.gmit.sw.ai;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
	
	public double scoreText(String text) {
		double score = 0;
		
		for(int i = 0; i < text.length() - 4; i++) {
			score += Math.log10((double) (((nGrams.get(text.substring(i, i+4)) != null) ? nGrams.get(text.substring(i, i+4)) : 1))/ this.count);
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
