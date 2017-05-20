package fileserver.command;

import fileserver.keys.ServerKeys;

public class Login extends Command {

	public Login(String clientData) {
		super(clientData);
	}

	@Override
	public String run() {
		// TODO Auto-generated method stub
		
		try {
			String[] lines = clientData.split("\n");
			String password = lines[2].split(":")[1];
			System.out.println(ServerKeys.getInstance().decrypt(password));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return ResponseCode.LOGGED_IN.toString();
	}

}
