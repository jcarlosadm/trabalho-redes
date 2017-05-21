package fileserver.command;

import java.io.File;

import fileserver.util.ClientState;
import fileserver.util.FolderManager;

public class ShowFiles extends Command {

	public ShowFiles(String clientData) {
		super(clientData);
	}
	
	private String addTab(String str) {
		String[] lines = str.split("\n");
		String fileTree = "";
		int index = 0;
		
		while(index < lines.length) {
			fileTree += "--" + lines[index] + "\n";
			++index;
		}
			
		return fileTree;
	}
	
	private String listFolder(File userPath) {
		File[] listOfFiles = userPath.listFiles();
		String fileTree = "";
		
		for(File file:listOfFiles){
			if (file.isDirectory()) {
				fileTree += file.getName() + "/\n";
				fileTree += addTab(listFolder(file));
			}
			else
				fileTree += file.getName() + "\n";
		}
		
		return fileTree;
	}

	@Override
	public String run() throws Exception {
		if (ClientState.getInstance().isLogged() == false) {
			return ResponseCode.NOT_LOGGED_IN.toString();
		}
		
		// get client base folder
		String filePath = FolderManager.getInstance().getClientBasicPath();
		
		File userPath = new File(filePath);
		// get the files list
		String fileTree = listFolder(userPath);
		
		return ResponseCode.FILE_SYSTEM_TREE.toString() + "\n\n" + fileTree;
	}

}
