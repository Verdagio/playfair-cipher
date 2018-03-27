package ie.gmit.sw.ai;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Grams {
	
	private String fileName;
	private Map<String, Integer> nGrams = new HashMap<String, Integer>();
	private int count;
	
	public Grams(String fileName) {
		this.fileName = fileName;
		this.count = 0;
	}// Constructor

	public Map<String, Integer> loadNGrams()  throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName))));
		String line = "";
		while((line = br.readLine()) != null) {
			nGrams.put(line.split(" ")[0], Integer.parseInt(line.split(" ")[1]));
			count++;
		}
		br.close();	
		return this.nGrams;
	}
	
	public int getCount() {
		return this.count;
	}
	
}
