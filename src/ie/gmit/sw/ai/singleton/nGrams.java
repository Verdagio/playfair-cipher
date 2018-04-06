package ie.gmit.sw.ai.singleton;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The Grams class is self contained, this will manage the quadgrams training
 * set, and the scoring of englishness of the given deciphered text.
 * 
 * @author Daniel Verdejo g00282931
 */

public class nGrams extends AbstractSingleton {

	private static nGrams instance;
	private String fileName;
	private Map<String, Integer> nGramsMap;
	private long count;

	/**
	 * there only needs to be one instance of this to ever exist. all of its data is
	 * self contained and should not modifiable from an external source following
	 * the principle of encapsulation.
	 * 
	 */
	private nGrams(String fileName) {
		this.fileName = fileName;
		this.nGramsMap = new HashMap<String, Integer>();
	}// private constructor

	public static nGrams getInstance(String fileName) {
		return (instance == null) ? new nGrams(fileName) : instance;
	}

	/**
	 * Populate a map of types String, Integer Using a Stream set the map using
	 * a lambda function which will do the follwoing for each line in the file,
	 * split on the " " into an array and populate the nGrams maps key value pair
	 * using each element of said array. set the count using a lambda function to
	 * the sum of all values.
	 * 
	 * @throws Exception FileNotFoundException IOException
	 */
	// reference:
	// https://stackoverflow.com/questions/30125296/how-to-sum-a-list-of-integers-with-java-streams
	public void loadNGrams() throws Exception {

		Stream<String> stream = Files.lines(Paths.get(this.fileName));
		nGramsMap = stream.map(tmp -> tmp.split(" "))
				.collect(Collectors.toMap(tmp -> tmp[0], tmp -> Integer.parseInt(tmp[1])));
		stream.close();
		this.count = nGramsMap.values().stream().mapToLong(i -> i).sum();
	}

	/**
	 * Takes string text, calculates the log probability of a quadGram that will
	 * occur based of a training set. log(p(gram)/N) where n is the summed
	 * probability of all quadGrams.
	 * 
	 * @param String text
	 * @return double
	 */
	public double scoreText(String text) {
		double score = 0;

		int range = (text.length() > 800) ? 742 - 4 : text.length() - 4;

		for (int i = 0; i < range; i++) {
			score += Math.log10((double) (((nGramsMap.get(text.substring(i, i + 4)) != null)
					? nGramsMap.get(text.substring(i, i + 4))
					: 1)) / this.count);
		}
		return score;
	}
}
