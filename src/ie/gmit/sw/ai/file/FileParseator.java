package ie.gmit.sw.ai.file;

public interface FileParseator {
	public String readFile(String fileName) throws Throwable;
	public void writeFile(String text, String fileName) throws Throwable;
}
