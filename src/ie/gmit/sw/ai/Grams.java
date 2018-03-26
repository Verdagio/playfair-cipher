package ie.gmit.sw.ai;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Grams {
	
	private  Map<String, Integer> nGrams = new HashMap<String, Integer>();

	public Map<String, Integer> loadNGrams() throws Exception{	
		BufferedReader br = new BufferedReader(new FileReader(new File("4grams.txt")));
		String line = "";
		while((line = br.readLine()) != null) {
			nGrams.put(line.split(" ")[0], Integer.parseInt(line.split(" ")[1]));
		}
		br.close();
		return this.nGrams;
	}
	
}
