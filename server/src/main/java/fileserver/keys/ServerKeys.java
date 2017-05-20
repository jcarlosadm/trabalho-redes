package fileserver.keys;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.security.PublicKey;

import br.com.commons.crypto.KeyPairProxy;

public class ServerKeys {

	private KeyPairProxy keyPairProxy = null;

	private static ServerKeys instance = null;

	private ServerKeys() throws Exception {

		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));

		System.out.println(
				"\nYou will be asked by your user and password. Be careful: if you change that, you will lose the"
				+ "clients passwords");
		System.out.print("type username: ");
		String user = bReader.readLine();
		System.out.print("type password: ");
		String password = this.getPassword(bReader);

		this.keyPairProxy = new KeyPairProxy();
		this.keyPairProxy.createNewKeys(user, password);
	}

	private String getPassword(BufferedReader bReader) throws Exception {
		Console console = System.console();
		String password = "";
		if (console == null)
			password = bReader.readLine();
		else
			password = String.valueOf(console.readPassword());
		
		return password;
	}

	/**
	 * @return ServerKeys instance, with cryptography keys. Uses Singleton
	 * @throws Exception
	 *             in case of error to generate cryptography keys
	 */
	public synchronized static ServerKeys getInstance() throws Exception {
		if (instance == null)
			instance = new ServerKeys();
		return instance;
	}

	/**
	 * @return public key
	 */
	public synchronized PublicKey getPublicKey() {
		return this.keyPairProxy.getPublicKey();
	}

	/**
	 * @param message
	 *            message to decrypt
	 * @return message decrypted
	 * @throws Exception
	 *             in case of error
	 */
	public synchronized String decrypt(String message) throws Exception {
		return this.keyPairProxy.decrypt(message);
	}
	
	public synchronized String encrypt(String message) throws Exception {
		return this.keyPairProxy.encryptWithPublicKey(message);
	}
}
