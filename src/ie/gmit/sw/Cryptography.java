package ie.gmit.sw;

public interface Cryptography {
	public String decrypt(String decryptionKey, String cipherText);
	public String encrypt(String encryptionKey, String plainText);
}
