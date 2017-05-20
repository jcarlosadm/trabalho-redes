package fileserver;

import fileserver.command.Command;
import fileserver.command.CreateFolder;
import fileserver.command.DeleteFile;
import fileserver.command.DeleteFolder;
import fileserver.command.DownloadFile;
import fileserver.command.GetPublicKey;
import fileserver.command.Login;
import fileserver.command.Logout;
import fileserver.command.Register;
import fileserver.command.SendFile;
import fileserver.command.ShowFiles;
import fileserver.util.PrintData;

public class Protocol {

	public synchronized static String execute(String clientData) {

		PrintData.print("data from client:", clientData);

		Command command = null;
		try {
			String commandStr = clientData.split("\n")[0];
			
			if (commandStr.equals("register"))
				command = new Register(clientData);
			
			else if (commandStr.equals("get_public_key"))
				command = new GetPublicKey(clientData);
			
			else if (commandStr.equals("login"))
				command = new Login(clientData);
			
			else if (commandStr.equals("send_file"))
				command = new SendFile(clientData);
			
			else if (commandStr.equals("download_file"))
				command = new DownloadFile(clientData);
			
			else if (commandStr.equals("create_folder"))
				command = new CreateFolder(clientData);
			
			else if (commandStr.equals("show_files"))
				command = new ShowFiles(clientData);
			
			else if (commandStr.equals("delete_file"))
				command = new DeleteFile(clientData);
			
			else if (commandStr.equals("delete_folder"))
				command = new DeleteFolder(clientData);
			
			else if (commandStr.equals("logout"))
				command = new Logout(clientData);
			
			else {
				return null;
			}
			
			return command.run();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}