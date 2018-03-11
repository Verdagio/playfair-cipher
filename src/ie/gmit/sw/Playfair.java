package ie.gmit.sw;

import java.util.LinkedList;
import java.util.List;

/**
 * The Playfair cipher is a manual symmetric encryption technique and was the
 * first literal digram substitution cipher. The technique encrypts pairs of
 * letters (bigrams or digrams), instead of single letters as in the simple
 * substitution cipher and rather more complex Vigenère cipher systems then in
 * use. The Playfair is thus significantly harder to break since the frequency
 * analysis used for simple substitution ciphers does not work with it. The
 * frequency analysis of bigrams is possible, but considerably more difficult.
 * With 600[1] possible bigrams rather than the 26 possible monograms (single
 * symbols, usually letters in this context), a considerably larger cipher text
 * is required in order to be useful. Reference:
 * https://en.wikipedia.org/wiki/Playfair_cipher
 * 
 * @author Daniel Verdejo - G00282931
 *
 */

public class Playfair extends Crypto {

	List<Position> positions = new LinkedList<Position>();
	String plainText;

	public Playfair() {
		super();
		this.plainText = "";
	}

	/**
	 * The generateKey method Takes a string and formats it to the correct lenght.
	 * If the key is too short it adds random letters using SecureRandom as this
	 * class provides a cryptographically strong random number generator (RNG).
	 * Reference:
	 * https://docs.oracle.com/javase/7/docs/api/java/security/SecureRandom.html
	 * 
	 * Rather than the Linear Congruential Generators (java.util.Random) which are
	 * not secure Reference: Linear Congruential Generators Do Not Produce Random
	 * Sequences http://ieeexplore.ieee.org/document/715950/?reload=true
	 * 
	 * Once the key is the correct length we then inspect each character and replace
	 * any recurring letters with an X, remove blank spaces ,and remove the letter J
	 * completely. we then do one final check to ensure our key contains 25 unique
	 * letters.
	 * 
	 * @param key
	 * @return cipherKey
	 */
	private String generateKey(String key) {

		StringBuilder cipherKey = new StringBuilder();
		key = key.toUpperCase().replaceAll("J", "").replaceAll("\\s+", "");

		// Random r = new SecureRandom();
		// String alphabet = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
		// if (key.length() < 25) {
		// int difference = 25 - key.length();
		// for (int i = 0; i < difference; i++) {
		// key += alphabet.charAt(r.nextInt(alphabet.length()));
		// }// for each index in the difference in length
		// } // if the key is too short

		cipherKey.append(key);
		for (int i = 0; i < key.length(); i++) {
			if (i != 0) {
				if (key.charAt(i - 1) == key.charAt(i)) {
					cipherKey.replace(i, i, "X");
				} // inner if: there are recurring letters replace them with an X
			} // outer if
			for (int j = cipherKey.length() - 1; j > 0; j--) {
				if (key.charAt(i) == cipherKey.charAt(j)) {
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

	/**
	 * The decrypt method will take already encypted text (cipherText) and decypt it
	 * out to plainText using the decryption key
	 * 
	 * @param key
	 * @param cipherText
	 * @return plainText
	 */
	public String decrypt(String key, String cipherText) {
		String[][] cipherTable = new String[5][5];
		String decryptionKey = generateKey(key);
		int index = 0;

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				cipherTable[i][j] = Character.toString(decryptionKey.charAt(index));
				System.out.print(cipherTable[i][j] + " ");
				index++;
			}
			System.out.println();
		}
		index = 0;
		// now for some decryption

		 cipher(cipherTable, cipherText, index);

		return this.plainText;
	}// decrypt

	/**
	 * The cipher method will recursively scan through each letter getting its
	 * position in the 2d array and use this to
	 * 
	 * @param table
	 * @param cipherText
	 * @param index
	 * @return this
	 */
	private int cipher(String[][] table, String cipherText, int index) {

		if (!(index < cipherText.length() / 2)) {
			return 0;
		} else {
			char a = cipherText.charAt(2 * index);
			char b = cipherText.charAt(2 * index + 1);
			int r1 = (int) Position.getPosition(a, table).getPosX();
			int r2 = (int) Position.getPosition(b, table).getPosX();
			int c1 = (int) Position.getPosition(a, table).getPosY();
			int c2 = (int) Position.getPosition(b, table).getPosY();
			
			//System.out.println(r1 + " " + c1 + " " + r2 + " " + c2);
			
		      if(r1 == r2){
		          c1 = (c1 + 4) % 5;
		          c2 = (c2 + 4) % 5;
		        }else if(c1 == c2){
		          r1 = (r1 + 4) % 5;
		          r2 = (r2 + 4) % 5;
		        }else{
		          int temp = c1;
		          c1 = c2;
		          c2 = temp;
		        }
		        plainText = plainText + table[r1][c1] + table[r2][c2];

			return cipher(table, cipherText, 2 + index);
		}
	}

	/**
	 * The encypt method will take plainText and encrypt it into cipherText using
	 * the encyption key and some trickery..
	 * 
	 * While this wasn't specifically specified as a feature to be added into this
	 * project it would be a simple add on to the project.
	 * 
	 * @param key
	 * @param plainText
	 * @return cipherText
	 */
	public String encrypt(String key, String plainText) {
		String cipherText = "";
		String encryptionKey;

		return cipherText;
	}// encrypt

}
