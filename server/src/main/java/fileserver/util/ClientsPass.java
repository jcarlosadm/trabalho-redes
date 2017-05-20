package fileserver.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Properties;

import fileserver.keys.ServerKeys;

public class ClientsPass {

	private String clientsPassFilePath = "clientsPass";

	private static ClientsPass instance = null;

	private ClientsPass() throws Exception {
		File file = new File(this.clientsPassFilePath);
		if (!file.exists())
			file.createNewFile();
	}

	public static synchronized ClientsPass getInstance() throws Exception {
		if (instance == null) {
			instance = new ClientsPass();
		}

		return instance;
	}

	public synchronized void putUser(String name, String password) throws Exception {
		password = ServerKeys.getInstance().encrypt(password);

		File file = new File(this.clientsPassFilePath);
		Properties prop = new Properties();

		if (file.exists()) {
			prop.load(new FileReader(file));
			prop.setProperty(name, password);
		} else {
			prop.setProperty(name, password);
			
			file.createNewFile();
		}
		
		FileOutputStream fStream = new FileOutputStream(file);
		prop.store(fStream, null);
	}

	public synchronized String getUserPassword(String name) throws Exception {
		
		Properties properties = new Properties();
		File file = new File(this.clientsPassFilePath);
		
		if (file.exists()) {
			properties.load(new FileReader(file));
			return ServerKeys.getInstance().decrypt(properties.getProperty(name));
		}
				
		return null;
	}
}
