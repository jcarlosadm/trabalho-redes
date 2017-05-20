package fileserver.command;

import java.io.File;

import fileserver.util.ClientState;
import fileserver.util.FolderManager;

public class DeleteFolder extends Command {

	public DeleteFolder(String clientData) {
		super(clientData);
	}

	@Override
	public String run() throws Exception {
		if (ClientState.getInstance().isLogged() == false)
			return ResponseCode.NOT_LOGGED_IN.toString();

		String[] lines = clientData.split("\n");
		String folderpath = FolderManager.getInstance().getClientBasicPath() + "/"
				+ lines[1].substring(lines[1].indexOf(":") + 1).trim();

		File folder = new File(folderpath);
		if (!folder.exists() || !folder.isDirectory())
			return ResponseCode.PATH_DONT_EXISTS.toString();

		if (!folder.delete())
			return null;

		return ResponseCode.DELETE_SUCCESSFUL.toString();
	}

}
