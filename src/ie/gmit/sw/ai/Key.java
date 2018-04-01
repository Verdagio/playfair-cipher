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
		StringBuilder newCipherKey = new StringBuilder();
		this.cipherKey = this.cipherKey.toUpperCase().replaceAll("J", "").replaceAll("\\W", "");
		newCipherKey.append((this.cipherKey.length() < 25) ? new FileParser(null).removeRecurringChars(this.cipherKey + "ABCDEFGHIKLMNOPQRSTUVWXYZ") : this.cipherKey);
		
		for (int i = 0; i < this.cipherKey.length(); i++) {	
			for (int j = newCipherKey.length() - 1; j > 0; j--) {
				if (this.cipherKey.charAt(i) == newCipherKey.charAt(j)) {
					// remove non unique letters
					if (i < j) {
						newCipherKey.deleteCharAt(j);
					} // do not replace the first instance of the letter
				} // IF
			} // for each element in the cipherKey
		} // for each element in the original key
		System.out.println("new key: " + newCipherKey.toString());
		return newCipherKey.toString();
	}
	
	public String shuffleKey(String originalKey) {
		Random r = new SecureRandom();
		int x = r.nextInt(99);
		
		if(x >= 0 && x < 2) {
			return swapRows(originalKey, r.nextInt(4), r.nextInt(4));
		} else if ( x >= 2 && x < 4) {
			return swapCols(originalKey, r.nextInt(4), r.nextInt(4));
		} else if ( x >= 4 && x < 6) {
			return flipRows(originalKey);
		} else if ( x >= 6 && x < 8) {
			return flipCols(originalKey);
		} else if ( x >= 8 && x < 10) {
			return new StringBuffer(originalKey).reverse().toString();
		} else {
			int a = r.nextInt(originalKey.length()-1);
			int b = r.nextInt(originalKey.length()-1);
			b = (a == b) ? (b == originalKey.length()-1) ? b - 1 : b + 1 : r.nextInt(originalKey.length()-1);
			char[] res = originalKey.toCharArray();
			char tmp = res[a];
			res[a] = res[b];
			res[b] = tmp;
			return new String(res);
		}// if else shuffle key
	}//shuffleKey
	
	private String flipRows(String key) {
		String[] res = new String[5];
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < 5; i++) {
			res[i] = key.substring(i*5, i*5 + 5);
			res[i] = new StringBuffer(res[i]).reverse().toString();
			sb.append(res[i]);
		}
		return sb.toString();
	}// flip rows
	
	private String flipCols(String key) {
		char[] res = key.toCharArray();
		int l = key.length() - (key.length()/5);
		
		for(int i = 0; i < key.length() / 5; i++) {
			for(int j = 0; j < key.length() / 5; j++) {
				char tmp = key.charAt(i*5 + j);
				res[(i*5) + j] =  key.charAt(l + j);
				res[l + j] =  tmp;
			}
			l -= 5;
		}
		return new String(res);
	}//flipCols
	
	private String swapRows(String key, int r1, int r2) {	
		return (r1 == r2) ? swapRows(key, new SecureRandom().nextInt(4), new SecureRandom().nextInt(4)) :  permutate(key, r1, r2, true);
	}//swapRows
	
	private String swapCols(String key, int c1, int c2) {
		return (c1 == c2) ? swapCols(key, new SecureRandom().nextInt(4), new SecureRandom().nextInt(4)) : permutate(key, c1, c2, false);
	}//swapcols
	
	private String permutate(String key, int a, int b, boolean isRows) {
			char[] newKey = key.toCharArray();
			if(isRows) {
				a *= 5;
				b *= 5;
			} 
			
			for(int i = 0; i < key.length() / 5 ; i++) {
				int index = (isRows) ? i : i*5;
				char tmp =  newKey[(index + a)];
				newKey[(index + a)] = newKey[(index + b)];
				newKey[(index + b)] = tmp;				
			}//for
			return new String(newKey);
	}//permutate

	
}
