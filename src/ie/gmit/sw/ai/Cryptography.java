package ie.gmit.sw.ai;

public interface Cryptography {
	public String decrypt(String decryptionKey, String cipherText) throws Exception;
	public String encrypt(String encryptionKey, String plainText);
}
