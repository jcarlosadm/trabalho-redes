package fileserver.command;

import java.io.File;
import java.util.Base64;

import org.apache.commons.io.FileUtils;

public class SendFile extends Command {

	public SendFile(String clientData) {
		super(clientData);
	}

	@Override
	public String run() {
		String[] fields = clientData.split("\n");
		String filePath = fields[1].substring(fields[1].indexOf(":") + 1).trim();
		
		String folderPath = filePath.substring(0, filePath.lastIndexOf("/"));
		File folder = new File(folderPath);
		if (!folder.exists() || !folder.isDirectory()) {
			return ResponseCode.PATH_DONT_EXISTS.toString();
		}
		
		File file = new File(filePath);
		
		try{
			byte[] fileBytes = Base64.getDecoder().decode(fields[3]);
			FileUtils.writeByteArrayToFile(file, fileBytes);
		}
		catch (Exception e) {
			System.out.println("Error to save file");
			return null;
		}
		
		
		return ResponseCode.FILE_UPLOADED_SUCCSESSFULLY.toString();
	}

}
