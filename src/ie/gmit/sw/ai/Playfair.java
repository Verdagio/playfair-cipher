package ie.gmit.sw.ai;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

	List<Position> positions;
	String plainText;
	String[][] cipherTable;

	public Playfair() {
		super();
		this.positions = new LinkedList<Position>();
		this.plainText = "";
		this.cipherTable = new String[5][5];
	}

	

	/**
	 * The decrypt method will take already encypted text (cipherText) and decypt it
	 * out to plainText using the decryption key
	 * 
	 * @param key
	 * @param cipherText
	 * @return plainText
	 * @throws Exception 
	 */
	public String decrypt(String key, String cipherText) throws Exception {
		Key k = Key.keyInstance(key);
		String decryptionKey = k.generateKey();
		String[][] cipherTable = new String[5][5];

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
		this.cipherTable = cipherTable;
		cipherCrack(cipherTable, cipherText, index); 

		return this.plainText;
	}// decrypt

	/**
	 * The cipher method will recursively scan through each letter getting its
	 * position in the 2d array and use this to crack the cipher text
	 * 	 *
	 * @param table
	 * @param cipherText
	 * @param index
	 * @return this
	 */
	private int cipherCrack(String[][] table, String cipherText, int index) {

		if (!(index < cipherText.length() / 2)) {
			return 0;
		} else {
			char a = cipherText.charAt(2 * index);
			char b = cipherText.charAt(2 * index + 1);
			int r1 = (int) Position.getPosition(a, table).getPosX();
			int c1 = (int) Position.getPosition(a, table).getPosY();
			int r2 = (int) Position.getPosition(b, table).getPosX();
			int c2 = (int) Position.getPosition(b, table).getPosY();

			if (r1 == r2) {
				c1 = (c1 + 4) % 5; 
				c2 = (c2 + 4) % 5;
			} else if (c1 == c2) {
				r1 = (r1 + 4) % 5;
				r2 = (r2 + 4) % 5;
			} else {
		        int temp = c1;
		        c1 = c2;
		        c2 = temp;
		      }

			plainText += table[r1][c1] + table[r2][c2];

			return cipherCrack(table, cipherText, 1 + index);
		}// else if
	}// cipherCrack

	@Override
	public String encrypt(String encryptionKey, String plainText) {
		
		
		return null;
	}

}
