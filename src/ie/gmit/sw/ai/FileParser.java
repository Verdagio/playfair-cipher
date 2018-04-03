package ie.gmit.sw.ai;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class FileParser {
	
	private String fileName;
	
	public FileParser() {}
	
	public FileParser(String fileName) {
		this.fileName = fileName;
	}
	
	public void primeText() throws Throwable {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(this.fileName))));
		String line;
		String regex ="(^\\W)(\\s+)(J)";
		while((line = br.readLine()) != null) {
			line = line.toUpperCase().replaceAll("\\W", "").replace("J", "");
			line = removeRecurringChars(line);
			System.out.println(line);
		}
	}
	
	public String readCipherText(String fileName) throws Throwable {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName))));
		StringBuilder sb = new StringBuilder();
		String line;
		
		while((line = br.readLine()) != null) {
			sb.append(line.toUpperCase());
		}

		return new String(sb.toString());
	}
	
	public void printDecryptedText(String decryptedText) throws Throwable {
		FileOutputStream fos = new FileOutputStream("deciphered.txt");
		byte[] bytes = decryptedText.getBytes();
		fos.write(bytes);
		fos.close();
		
	}
	
	public String removeRecurringChars(String l) {
		char[] newLine = l.toUpperCase().toCharArray();
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < newLine.length; i++) {
			if(i != newLine.length - 1) 
				newLine[i+1] = (newLine[i] == newLine[i+1]) ? 'X' : newLine[i+1];
		}// for 	
		return new String(newLine);
	}// purge duplicates
	
}
