package fileserver.command;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;

public class DownloadFile extends Command {

	public DownloadFile(String clientData) {
		super(clientData);
	}

	@Override
	public String run() {
		// split by line break. be clear
		String[] fields = clientData.split("\n");
		// get the second line by index 1
		String filePath = fields[1];
		// get the value after ':'. see protocol
		filePath = filePath.substring(filePath.indexOf(":") + 1).trim();

		File file = new File(filePath);
		// if file don't exists, return message path don't exists
		if (!file.exists() || file.isDirectory()) {
			return ResponseCode.PATH_DONT_EXISTS.toString();
		}

		byte[] fileBytes;
		try {
			fileBytes = FileUtils.readFileToByteArray(file);
		} catch (IOException e1) {
			e1.printStackTrace();
			return null;
		}

		String fileString = Base64.getEncoder().encodeToString(fileBytes);
		return ResponseCode.FILE_FOUND.toString() + "\n\n" + fileString;
	}

}
