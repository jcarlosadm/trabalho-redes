package fileserver;

import fileserver.command.Command;
import fileserver.command.DownloadFile;
import fileserver.command.GetPublicKey;
import fileserver.command.Login;
import fileserver.command.Register;
import fileserver.command.SendFile;
import fileserver.util.PrintData;

public class Protocol {

	public static String execute(String clientData) {

		PrintData.print("data from client:", clientData);

		Command command = null;
		try {
			String commandStr = clientData.split("\n")[0];
			
			// TODO implement all protocols. cristiano (implementar por último)
			
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