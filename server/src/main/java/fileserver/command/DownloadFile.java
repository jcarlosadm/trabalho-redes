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
		String[] fields = clientData.split("[\\s]+");
		String filePath = fields[2];
			
		File file = new File(filePath);		
		byte[] fileBytes;
		
		try {
			fileBytes = FileUtils.readFileToByteArray(file);
		} catch (IOException e1) {
			e1.printStackTrace();
			return null;
		}
		
		String fileString = Base64.getEncoder().encodeToString(fileBytes);
		String message = ResponseCode.FILE_FOUND.toString() + "\n\n" + fileString;
		
		return message;
	}

}
