package fileserver.command;

import fileserver.keys.ServerKeys;
import fileserver.util.ClientsPass;

public class Register extends Command {

	public Register(String clientData) {
		super(clientData);
	}

	@Override
	public String run() throws Exception {

		String[] fields = clientData.split("\n");

		String user = fields[1].substring(fields[1].indexOf(":") + 1).trim();
		String password = fields[2].substring(fields[2].indexOf(":") + 1).trim();

		try {
			if (ClientsPass.getInstance().userExists(user) == true) {
				return ResponseCode.ERROR_USER_EXISTS.toString();
			}
			
			ClientsPass.getInstance().putUser(user, ServerKeys.getInstance().decrypt(password));
			
			return ResponseCode.REGISTERED.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
