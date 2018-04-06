package ie.gmit.sw.ai.cipher;

public interface Cryptography {
	public String decrypt(String decryptionKey) throws Exception, Throwable;
	public String encrypt(String encryptionKey);
}
