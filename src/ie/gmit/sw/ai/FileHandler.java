package ie.gmit.sw.ai;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileHandler {
	
	private String fileName;
	
	public FileHandler() {}
	
	public FileHandler(String fileName) {
		this.fileName = fileName;
	}
	
	/* 
	 * Primes plain text by removing any non letter characters, digits, punctuation etc.
	 * 
	 * @param str
	 * @return String
	 * @throws Throwable
	 */
	public String primeText(String str) throws Throwable {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(this.fileName)));
		String line;
		while((line = br.readLine()) != null) {
			line = line.toUpperCase().replaceAll("\\W", "").replace("J", "");
		}
		return br.toString();
	}
	
	/**
	 * Read in a file using the given fileName from root dir
	 * Iterates over byte array and returns to a string
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
		return (result.toString().length() > 750) ? result.toString().substring(0, 750) : result.toString();
	}
	
	/**
	 * Writes from given string to a file using FileOutputStream with specification of file name.
	 * 
	 * @param text
	 * @param fileName
	 * @throws Throwable
	 */
	public void writeFile(String text, String fileName) throws Throwable {
		FileOutputStream fos = new FileOutputStream(fileName);
		byte[] bytes = text.getBytes();
		for(byte b : bytes) {
			fos.write(bytes);
		}
		fos.close();
		
	}
	
	/*
	 * Removes any recurring letters in a give string and replaces with an X
	 * 
	 * @param str
	 * @return
	 */
	public String removeRecurringChars(String str) {
		char[] newLine = str.toCharArray();
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < newLine.length; i++) {
			if(i != newLine.length - 1) 
				newLine[i+1] = (newLine[i] == newLine[i+1]) ? 'X' : newLine[i+1];
		}// for 	
		return new String(newLine);
	}// purge duplicates
	
}
