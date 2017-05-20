package fileserver.command;

import fileserver.util.ClientState;

public class Logout extends Command {

	public Logout(String clientData) {
		super(clientData);
	}

	@Override
	public String run() throws Exception {
		if (ClientState.getInstance().isLogged() == false)
			return ResponseCode.NOT_LOGGED_IN.toString();
		
		ClientState.getInstance().logout();
		
		return ResponseCode.LOGOUT_SUCCESSFUL.toString();
	}

}
