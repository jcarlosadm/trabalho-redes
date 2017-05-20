package fileserver.util;

import java.io.File;

public class FolderManager {
	
	private static final String FILESYSTEM_FOLDER = "FileSystemClients";
	
	private String basicPath = "";
	
	private static final ThreadLocal<FolderManager> _threadLocalInstance = new ThreadLocal<FolderManager>() {
		protected FolderManager initialValue() {
			return new FolderManager();
		}
	};
	
	private FolderManager() {
		File folder = new File(FILESYSTEM_FOLDER);
		if (!folder.exists())
			folder.mkdirs();
	}
	
	public static FolderManager getInstance() {
		return _threadLocalInstance.get();
	}
	
	public void setClientBasicPath(String clientName) {
		this.basicPath = FILESYSTEM_FOLDER + "/" + clientName;
		File folder = new File(this.basicPath);
		if (!folder.exists())
			folder.mkdirs();
	}
	
	public String getClientBasicPath() {
		return this.basicPath;
	}
}
