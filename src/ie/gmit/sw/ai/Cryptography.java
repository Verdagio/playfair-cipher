package ie.gmit.sw.ai;

public interface Cryptography {
	public String decrypt(String decryptionKey) throws Exception, Throwable;
	public String encrypt(String encryptionKey, String plainText);
}
