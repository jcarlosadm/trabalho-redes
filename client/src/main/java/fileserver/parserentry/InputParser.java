package fileserver.parserentry;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Base64;

import org.apache.commons.io.FileUtils;

import br.com.commons.crypto.KeyPairProxy;
import fileserver.cryptography.server.ServerKey;

public abstract class InputParser {

	private InputParser() {
	}

	/**
	 * format line and return message formated
	 * 
	 * @param line
	 *            line to be formated
	 * @return formated message, or null in case of error
	 */
	public static String format(String line) {

		String data = "";

		try {
			String[] fields = line.split("[\\s]+");

			if (fields[0].equals("login"))
				data = buildLoginMessage(fields[1]);

			else if (fields[0].equals("register"))
				data = buildRegisterMessage(fields[1]);

			else if (fields[0].equals("send_file"))
				data = buildSendfileMessage(fields[1], fields[2]);

			else if (fields[0].equals("download_file"))
				data = buildDownloadfileMessage(fields[1]);

			else if (fields[0].equals("create_folder"))
				data = buildCreatefolderMessage(fields[1]);

			else if (fields[0].equals("show_files"))
				data = buildShowFilesMessage();

			else if (fields[0].equals("delete_file"))
				data = buildDeleteFileMessage(fields[1]);

			else if (fields[0].equals("delete_folder"))
				data = buildDeleteFolderMessage(fields[1]);

			else if (fields[0].equals("logout"))
				data = buildLogoutMessage();

			else {
				return null;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

		return data;
	}

	private static String buildLogoutMessage() {
		return "logout";
	}

	private static String buildDeleteFolderMessage(String serverPath) {
		return "delete_folder\n" + "path:" + serverPath;
	}

	private static String buildDeleteFileMessage(String serverPath) {
		return "delete_file\n" + "filepath:" + serverPath;
	}

	private static String buildShowFilesMessage() {
		return "show_files";
	}

	private static String buildCreatefolderMessage(String serverPath) {
		return "create_folder\n" + "path:" + serverPath;
	}

	private static String buildDownloadfileMessage(String serverPath) {
		return "download_file\n" + "filepath:" + serverPath;
	}

	private static String buildSendfileMessage(String localPath, String serverPath) throws Exception {
		File file = new File(localPath);
		if (!file.exists() || file.isDirectory())
			throw new Exception("file " + localPath + " don't exists in local system");

		byte[] fileByteArray = FileUtils.readFileToByteArray(file);
		String data = Base64.getEncoder().encodeToString(fileByteArray);

		return "send_file\n" + "filepath:" + serverPath + "\n\n" + data;
	}

	private static String buildRegisterMessage(String username) throws Exception {
		return "register\n" + "user:" + username + "\n" + "password:" + getPassword();
	}

	private static String buildLoginMessage(String username) throws Exception {
		return "login\n" + "user:" + username + "\n" + "password:" + getPassword();
	}

	private static String getPassword() throws Exception {
		Console console = System.console();
		String password = "";
		System.out.print("type password and click ENTER: ");
		if (console == null) {
			BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
			password = bReader.readLine();
		} else
			password = String.valueOf(console.readPassword());

		return KeyPairProxy.encryptWithOtherPublicKey(password, ServerKey.getInstance().getPublicKey());
	}

}
