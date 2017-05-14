package fileserver.server;

import java.security.PublicKey;

public class ServerKey {
	
	private PublicKey publicKey = null;
	
	private static ServerKey instance = null;
	
	private ServerKey() {
	}
	
	public static ServerKey getInstance() {
		if (instance == null){
			instance = new ServerKey();
		}
		
		return instance;
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}
	

}
