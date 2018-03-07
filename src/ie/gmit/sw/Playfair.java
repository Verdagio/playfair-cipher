package ie.gmit.sw;

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
 * @author Daniel Verdejo - G00282931
 *
 */

public class Playfair extends Crypto {

	public Playfair(String key, String[][] map) {
		super(key, map);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String decrypt() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public void encrypt() {
		// TODO Auto-generated method stub
		
	}
	

}
