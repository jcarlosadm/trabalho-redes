package fileserver.command;

import java.io.File;

public class Register extends Command {

	public Register(String clientData) {
		super(clientData);
	}

	@Override
	public String run() {
		// TODO Auto-generated method stub. carlos
		/*
		 * --------------------
		 * 1 registered
		 * --------------------
		 * 
		 * or
		 * 
		 * --------------------
		 * 2 error: user exists
		 * --------------------
		 */

		String[] fields = clientData.split("[\\s]+");

		String user = fields[2];

		File file = new File("\\server\\" + user);
		
		if (!file.exists()) {
			if (file.mkdir()) {
				return ResponseCode.REGISTERED.toString();
				//System.out.println("Directory is created!");
			} 
			else {
				//System.out.println("Failed to create directory!");
			}
		}
		else {
			return ResponseCode.ERROR_USER_EXISTS.toString();
		}


		return null;
	}

}
