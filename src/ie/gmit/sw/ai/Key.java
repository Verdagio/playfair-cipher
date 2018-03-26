package ie.gmit.sw.ai;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Key {
	
	private String cipherKey;
	private static Key instance;
	
	private Key(String cipherKey) {
		this.cipherKey = (cipherKey == null) ? "ABCDEFGHIKLMNOPQRSTUVWXYZ" : cipherKey;
	}
	
	public static Key keyInstance(String cipherKey) {
		return (instance == null) ? new Key(cipherKey) : instance;
	}
	
	/**
	 * The generateKey method Takes a string and formats it to the correct lenght.
	 * Once the key is the correct length we then inspect each character and replace
	 * any recurring letters with an X, remove blank spaces ,and remove the letter J
	 * completely. we then do one final check to ensure our key contains 25 unique
	 * letters.
	 * 
	 * @param key
	 * @return cipherKey
	 */
	public String generateKey() {

		StringBuilder cipherKey = new StringBuilder();
		this.cipherKey = this.cipherKey.toUpperCase().replaceAll("J", "").replaceAll("\\s+", "");
		
		cipherKey.append(this.cipherKey);
		for (int i = 0; i < this.cipherKey.length(); i++) {
			if (i != 0) {
				if (this.cipherKey.charAt(i - 1) == this.cipherKey.charAt(i)) {
					cipherKey.replace(i, i, "X");
				} // inner if: there are recurring letters replace them with an X
			} // outer if
			for (int j = cipherKey.length() - 1; j > 0; j--) {
				if (this.cipherKey.charAt(i) == cipherKey.charAt(j)) {
					// remove non unique letters
					if (i < j) {
						cipherKey.deleteCharAt(j);
					} // do not replace the first instance of the letter
				} // IF
			} // for each element in the cipherKey
		} // for each element in the original key
		System.out.println("key: " + cipherKey.toString());
		return cipherKey.toString();
	}
	
	public String shuffleKey(String originalKey) {
		String modifiedKey = null;
		Random r = new SecureRandom();
		int x = r.nextInt(99);
		
		if(x >= 0 && x < 2) {
			modifiedKey = swapRows(originalKey, r.nextInt(4), r.nextInt(4));
		} else if ( x >= 2 && x < 4) {
			modifiedKey = swapCols(originalKey, r.nextInt(4), r.nextInt(4));
		} else if ( x >= 4 && x < 6) {
			
		} else if ( x >= 6 && x < 8) {
			
		} else if ( x >= 8 && x < 10) {
			modifiedKey = new StringBuffer(originalKey).reverse().toString();
		} else {
			List<String> strList = Arrays.asList(originalKey);
			Collections.shuffle(strList);
			modifiedKey = strList.toArray(new String[strList.size()]).toString();
		}// if else shuffle key
		return modifiedKey;
	}//shuffleKey
	
	private String flipRows(String key) {
		String[] res = new String[5];
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < 5; i++) {
			res[i] = key.substring(i*5, i*5 + 4);
			res[i] = new StringBuffer(res[i]).reverse().toString();
			sb.append(res[i]);
		}
		return sb.toString();
	}// flip rows
	
	private String flipCols(String key) {
		StringBuilder sb = new StringBuilder();
		
		return sb.toString();
	}//flipCols
	
	private String swapRows(String key, int r1, int r2) {
		Random r = new SecureRandom();
		String a = key.substring((r1 * 25)/5, ((r1 * 25)/5) + 4);
		String b = key.substring((r2 * 25)/5, ((r2 * 25)/5) + 4);
		
		return (r1 == r2) ? swapRows(key, r.nextInt(4), r.nextInt(4)) : key.replace(a, b).replace(b, a);		
	}//swapRows
	
	private String swapCols(String key, int c1, int c2) {
		Random r = new SecureRandom();
		
		return (c1 == c2) ? swapCols(key, r.nextInt(4), r.nextInt(4)) : permutate(key, c1, c2);
	}//swapcols
	
	private String permutate(String key, int a, int b) {
			char[] newKey = key.toCharArray();
			
			for(int i = 0; i < key.length() ; i++) {
				char tmp = newKey[i*5 + a];
				newKey[i * 5 + a] = newKey[i * 5 + b];
				newKey[i * 5 + b] = tmp;
			}//for
			return new String(newKey);
	}//permutate

	
}
