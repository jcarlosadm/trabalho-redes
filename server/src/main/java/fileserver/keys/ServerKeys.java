package fileserver.keys;

import java.security.PublicKey;

import br.com.commons.crypto.KeyPairProxy;

public class ServerKeys {
	
	private KeyPairProxy keyPairProxy = null;
	
	private static ServerKeys instance = null;
	
	private ServerKeys() throws Exception {
		this.keyPairProxy = new KeyPairProxy();
		this.keyPairProxy.createNewKeys();
	}

	/**
	 * @return ServerKeys instance, with cryptography keys. Uses Singleton
	 * @throws Exception in case of error to generate cryptography keys
	 */
	public static ServerKeys getInstance() throws Exception {
		if (instance == null)
			instance = new ServerKeys();
		return instance;
	}
	
	/**
	 * @return public key
	 */
	public PublicKey getPublicKey() {
		return this.keyPairProxy.getPublicKey();
	}
	
	/**
	 * @param message message to decrypt
	 * @return message decrypted
	 * @throws Exception in case of error
	 */
	public String decrypt(String message) throws Exception {
		return this.keyPairProxy.decrypt(message);
	}
}
