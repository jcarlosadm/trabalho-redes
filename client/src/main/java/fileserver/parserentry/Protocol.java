package fileserver.parserentry;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import org.apache.commons.io.FileUtils;

import fileserver.cryptography.server.ServerKey;
import fileserver.util.PrintData;

public class Protocol {

	public static void execute(String command) {

		PrintData.print("data from server:", command);

		try {
			int cod = Integer.parseInt(command.substring(0, command.indexOf(" ")));

			String firstLine = command.split("\n")[0];
			System.out.println("message from server: " + firstLine.substring(firstLine.indexOf(" ") + 1));

			switch (cod) {
			case 9:
				getFile(getData(command));
				break;
			case 11:
				System.out.println(getData(command));
				break;
			case 14:
				setServerKey(getData(command));
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void setServerKey(String data) throws Exception {
		if (data.isEmpty())
			return;

		byte[] keybytes = Base64.getDecoder().decode(data.getBytes());
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keybytes);
		KeyFactory kFactory = KeyFactory.getInstance("RSA");

		PublicKey publicKey = kFactory.generatePublic(x509EncodedKeySpec);

		ServerKey.getInstance().setPublicKey(publicKey);
	}

	private static String getData(String command) throws Exception {
		String[] lines = command.split("\n");
		int index = 0;
		String data = "";
		while (lines[index].trim().isEmpty() == false && index < lines.length)
			++index;

		if ((index + 1) < lines.length)
			data = lines[index + 1];

		return data;
	}

	private static void getFile(String data) throws Exception {
		if (data.isEmpty())
			return;

		byte[] fileBytes = Base64.getDecoder().decode(data);
		String filePath = getPath();
		if (filePath == null) {
			System.out.println("canceled operation");
			return;
		}

		FileUtils.writeByteArrayToFile(new File(filePath), fileBytes);
	}

	private static String getPath() throws Exception {
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			System.out.print("type the filepath to store this file (use file separator from your system): ");
			
			String path = bufferedReader.readLine();
			String folderpath = null;
			
			try{
				folderpath = path.substring(0, path.lastIndexOf(File.separator));
			}
			catch (Exception e) {
				folderpath = "." + File.separator;
			}
			
			File folder = new File(folderpath);
			if (!folder.exists() || !folder.isDirectory()) {
				System.out.print("invalid path. continue? Type \"yes\" or \"no\": ");
				String response = bufferedReader.readLine();
				if (response.trim().toLowerCase().equals("yes") == false)
					return null;
			} else {
				return path;
			}
		}
	}

}
