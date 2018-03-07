package ie.gmit.sw;

import java.security.SecureRandom;
import java.util.Random;

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

	private String[][] cipherMap;

	public Playfair() {
		super();
	}

	/**
	 * The generateKey method Takes a string and formats it to the correct lenght.
	 * If the key is too short it adds random letters using SecureRandom as this
	 * class provides a cryptographically strong random number generator (RNG).
	 * Reference: https://docs.oracle.com/javase/7/docs/api/java/security/SecureRandom.html
	 * 
	 * Rather than the Linear Congruential Generators (java.util.Random) which are
	 * not secure Reference: Linear Congruential Generators Do Not Produce Random
	 * Sequences http://ieeexplore.ieee.org/document/715950/?reload=true
	 * 
	 * @param key
	 */
	public String generateKey(String key) {
		String cipherKey = "";

		if (key.length() < 25) {
			Random r = new SecureRandom();
			String alphabet = "ABCDEFGHIKLMNOPQRSTUVWX";
			int distance = 25 - key.length();

			for (int i = 0; i < distance; i++) {
				key += alphabet.charAt(r.nextInt(distance));
			}
		}
		for (int i = 0; i < 25; i++) {
			if (key.charAt(i) != 'J') {
				if (key.charAt(i) == key.charAt(i - 1)) {
					cipherKey += 'X';
				} else {
					cipherKey += key.charAt(i);
				} // replace recurring letters with X
			} // ignore the letter J
		}
		return cipherKey;
	}

	public String[][] getCipherMap() {
		return cipherMap;
	}

	public void setCipherMap(String[][] map) {
		this.cipherMap = map;
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
		String plainText = "";
		String decryptionKey;

		return plainText;
	}// decrypt

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
