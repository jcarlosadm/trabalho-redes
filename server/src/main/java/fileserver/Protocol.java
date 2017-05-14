package fileserver;

import fileserver.command.Command;
import fileserver.command.Register;

public class Protocol {

	public static String execute(String clientData) {

		System.out.println("-------------------\n" + clientData + "------------------------");

		Command command = null;
		try {
			String commandStr = clientData.substring(0, clientData.indexOf("\n"));
			
			// TODO implement all protocols. cristiano (implementar por último)
			
			if (commandStr.equals("register")) {
				command = new Register(clientData);
			}
			
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