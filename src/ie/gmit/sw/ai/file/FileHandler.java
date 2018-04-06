package ie.gmit.sw.ai.file;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * The FileHandler class holds the responsibility of managing files It has
 * functionality to read from files, write to files, and format files text from
 * files into playfair friendly text (no spaces, digits, symbols, and all upper
 * case)
 * 
 * @author Daniel Verdejo G00282931
 *
 */

public class FileHandler implements FileParseator, Formatator {

	public FileHandler() {
	}

	/**
	 * Read in a file using the given fileName from root dir Iterates over byte
	 * array and returns to a string
	 * 
	 * @param fileName
	 * @return String
	 * @throws Throwable
	 */
	public String readFile(String fileName) throws Throwable {
		InputStream is = new FileInputStream(fileName);

		ByteArrayOutputStream result = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int length;
		while ((length = is.read(buffer)) != -1) {
			result.write(buffer, 0, length);
		}
		is.close();
		return primeText((result.toString().length() > 750) ? result.toString().substring(0, 750) : result.toString());
	}

	/**
	 * Writes from given string to a file using FileOutputStream with specification
	 * of file name.
	 * 
	 * @param text
	 * @param fileName
	 * @throws Throwable
	 */
	public void writeFile(String text, String fileName) throws Throwable {
		FileOutputStream fos = new FileOutputStream(fileName);
		fos.write(text.getBytes());
		fos.close();
	}

	/**
	 * Primes plain text by removing any non letter characters, digits, punctuation
	 * etc.
	 * 
	 * @param str
	 * @return String
	 * @throws Throwable
	 */
	public String primeText(String s) {

		s = (s.length() % 2 == 0) ? s.toUpperCase().replaceAll("\\W", "").replaceAll("[0-9]", "").replace("J", "")
				: s.toUpperCase().replaceAll("\\W", "").replaceAll("[0-9]", "").replace("J", "") + "X";

		return removeRecurringChars(s);
	}

	/**
	 * Removes any recurring letters in a give string and replaces with an X
	 * 
	 * @param str
	 * @return string result
	 */
	public String removeRecurringChars(String str) {
		char[] newLine = str.toCharArray();

		for (int i = 0; i < newLine.length; i++) {
			if (i != newLine.length - 1)
				newLine[i + 1] = (newLine[i] == newLine[i + 1]) ? 'X' : newLine[i + 1];
		} // for
		return new String(newLine);
	}// purge duplicates

}
