package fileserver.command;

import fileserver.keys.ServerKeys;
import fileserver.util.ClientState;
import fileserver.util.ClientsPass;
import fileserver.util.FolderManager;

public class Login extends Command {

	public Login(String clientData) {
		super(clientData);
	}

	@Override
	public String run() throws Exception {
		
		try {
			String[] lines = clientData.split("\n");
			String name = lines[1].substring(lines[1].indexOf(":") + 1).trim();
			
			if (ClientsPass.getInstance().userExists(name) == false){
				return ResponseCode.USER_NOT_EXISTS.toString();
			}
			
			String password = lines[2].substring(lines[2].indexOf(":") + 1).trim();
			
			if (!ServerKeys.getInstance().decrypt(password).equals(ClientsPass.getInstance().getUserPassword(name))) {
				return ResponseCode.WRONG_PASSWORD.toString();
			}
			
			FolderManager.getInstance().setClientBasicPath(name);
			ClientState.getInstance().login();
			
			return ResponseCode.LOGGED_IN.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
