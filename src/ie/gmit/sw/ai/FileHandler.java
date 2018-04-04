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
	
	public String primeText(String str) throws Throwable {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(this.fileName)));
		String line;
		while((line = br.readLine()) != null) {
			line = line.toUpperCase().replaceAll("\\W", "").replace("J", "");
		}
		return br.toString();
	}
	
	public String readFile(String fileName) throws Throwable {
		InputStream is = new FileInputStream(fileName);
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int length;
		while ((length = is.read(buffer)) != -1) {
		    result.write(buffer, 0, length);
		}
		return result.toString();
	}
	
	public void writeFile(String text) throws Throwable {
		FileOutputStream fos = new FileOutputStream("deciphered.txt");
		byte[] bytes = text.getBytes();
		for(byte b : bytes) {
			fos.write(bytes);
		}
		fos.close();
		
	}
	
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
