package fileserver.command;

import java.util.Base64;

import fileserver.keys.ServerKeys;

public class GetPublicKey extends Command {

	public GetPublicKey(String clientData) {
		super(clientData);
	}

	@Override
	public String run() throws Exception {
		String data = ResponseCode.PUBLIC_KEY.toString() + "\n\n";
		
		try {
			data += Base64.getEncoder().encodeToString(ServerKeys.getInstance().getPublicKey().getEncoded());
		} catch (Exception e) {
			System.out.println("error to get public key");
			return null;
		}

		return data;
	}

}
