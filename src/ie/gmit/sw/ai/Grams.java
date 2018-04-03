package ie.gmit.sw.ai;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Grams {
	
	private String fileName;
	private Map<String, Double> nGrams;
	private int no;
	
	public Grams(String fileName) {
		this.fileName = fileName;
		this.nGrams = new HashMap<String, Double>();
	}// Constructor

	public Map<String, Double> loadNGrams()  throws Exception {
		int count = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName))));
		String line = "";
		System.out.println("Loading n-grams...");
		while((line = br.readLine()) != null) {
			nGrams.put(line.split(" ")[0], Double.parseDouble(line.split(" ")[1]));
			count++;
		}
		setNo(count);
		System.out.println("Sucessfully loaded n-grams...");
		br.close();	
		return this.nGrams;
	}
	
	public double scoreText(String cipherText) {
		double score = 0;
		
		int range = (cipherText.length() < 400) ?  cipherText.length() - 4 : 400 - 4;
		
		for(int i = 0; i < range; i++) {
			Double frequency = (Double) nGrams.get(cipherText.substring(i, i+4));
			if(frequency != null) {
				score +=  (frequency / getNo());
			}
		}
		return score;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	
	public int getNo() {
		return this.no;
	}
	
}
