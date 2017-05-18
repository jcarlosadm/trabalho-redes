package fileserver.command;

import java.awt.image.BufferedImageFilter;
import java.awt.print.Printable;
import java.io.File;
import java.io.FileOutputStream;

public class SendFile extends Command {

	public SendFile(String clientData) {
		super(clientData);
	}

	@Override
	public String run() {
		String[] fields = clientData.split("[\\s]+");
		String filePath = fields[2];
		String fileString = fields[3];
			
		byte[] fileBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(fileString);
				
		File file = new File(filePath);
		
		try{
			FileOutputStream fop = new FileOutputStream(file);
			fop.write(fileBytes);
			fop.flush();
			fop.close();
		}
		catch (Exception e) {
			return null;
		}
		
		
		return ResponseCode.FILE_UPLOADED_SUCCSESSFULLY.toString();
	}

}
