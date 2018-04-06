package ie.gmit.sw.ai.cipher;
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
	
	private String cipherText;

	public Playfair(String cipherText) {
		super();
		this.cipherText = cipherText;
	}
	
	/**
	 * The decrypt method will take already encypted text (cipherText) and decypt it
	 * out to plainText using the decryption key
	 * 
	 * @param decryptionKey
	 * @return cipherCrack()
	 * @throws Exception IOException
	 */
	public String decrypt(String decryptionKey) throws Exception {		
		char[][] cipherTable = populateTable(decryptionKey);
		return cipherCrack(cipherTable, 0, new StringBuilder(), true);
	}// decrypt
	
	/**
	 * The encrypt method will take plain text and encrypt it
	 * out to cipher text using the encryption key
	 * 
	 * @param String decryptionKey
	 * @return cipherCrack()
	 */
	public String encrypt(String encryptionKey) {
		char[][] cipherTable = populateTable(encryptionKey);
		return cipherCrack(cipherTable, 0, new StringBuilder(), false);
	}

	/**
	 * The cipher method will recursively scan through each pair of letters getting its
	 * position in the 2d array and use this to crack the cipher text until complete
	 * 
	 * We can RE-USE this function for both deciphering and ciphering the test by adding
	 * a simple flag which will dynamically set the modifier for our character swapping.
	 * 
	 * @param table
	 * @param cipherText
	 * @param index
	 * @param decrypt
	 * @return this / string
	 */
	private String cipherCrack(char[][] table, int index, StringBuilder sb, boolean decrypt) {
		
		int modifier = (decrypt) ? 4 : 1;
		
		if(index < (this.cipherText.length() / 2) - 1 ) {
			char a = this.cipherText.charAt(2 * index);
			char b = this.cipherText.charAt(2 * index + 1);
			int r1 = (int) Position.getPosition(a, table).getPosX();
			int c1 = (int) Position.getPosition(a, table).getPosY();
			int r2 = (int) Position.getPosition(b, table).getPosX();
			int c2 = (int) Position.getPosition(b, table).getPosY();

			if (r1 == r2) {
				c1 = (c1 + modifier) % 5; 
				c2 = (c2 + modifier) % 5;
			} else if (c1 == c2) {
				r1 = (r1 + modifier) % 5;
				r2 = (r2 + modifier) % 5;
			} else {
		        int temp = c1;
		        c1 = c2;
		        c2 = temp;
		    }
			sb.append(table[r1][c1] +""+ table[r2][c2]);
			
			return cipherCrack(table, 1 + index, sb, decrypt);
		}else return sb.toString();
	}// cipherCrack
	
	
	/***
	 * Takes in the decryption / encryption key and populates the cipher table 
	 * using the gicen key
	 * 
	 * @param String key
	 * @return 2d char array
	 */
	public char[][] populateTable(String key){
		char[][] cipherTable = new char[5][5];
		int index = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				cipherTable[i][j] = key.charAt(index);
				index++;
			}
		}
		return cipherTable;
	}
	public String getCipherText() {
		return cipherText;
	}

	public void setCipherText(String cipherText) {
		this.cipherText = cipherText;
	}
}
