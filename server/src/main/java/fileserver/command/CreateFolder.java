package fileserver.command;

import java.io.File;

import fileserver.util.ClientState;
import fileserver.util.FolderManager;

public class CreateFolder extends Command {

	public CreateFolder(String clientData) {
		super(clientData);
	}

	@Override
	public String run() throws Exception {
		if (ClientState.getInstance().isLogged() == false)
			return ResponseCode.NOT_LOGGED_IN.toString();

		String[] lines = clientData.split("\n");

		String path = FolderManager.getInstance().getClientBasicPath() + "/"
				+ lines[1].substring(lines[1].indexOf(":") + 1).trim();

		File folder = new File(path);
		folder.mkdirs();

		return ResponseCode.PATH_SUCCESSFULLY_CREATED.toString();
	}

}
