package fileserver.command;

import java.io.File;

import fileserver.util.ClientState;

public class DeleteFile extends Command {

	public DeleteFile(String clientData) {
		super(clientData);
	}

	@Override
	public String run() throws Exception {
		// TODO Auto-generated method stub
		if (ClientState.getInstance().isLogged() == false)
			return ResponseCode.NOT_LOGGED_IN.toString();
		
		String[] lines = clientData.split("\n");
		String filepath = lines[1].substring(lines[1].indexOf(":") + 1).trim();
		
		File file = new File(filepath);
		if (!file.exists() || file.isDirectory())
			return ResponseCode.PATH_DONT_EXISTS.toString();
		
		if (file.delete() == false)
			return null;
			
		
		return ResponseCode.DELETE_SUCCESSFUL.toString();
	}

}
