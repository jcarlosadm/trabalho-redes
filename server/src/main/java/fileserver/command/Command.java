package fileserver.command;

public abstract class Command {

	protected String clientData;
	
	public Command(String clientData) {
		this.clientData = clientData;
	}
	
	/**
	 * @return response to client
	 */
	public abstract String run() throws Exception;
	
}
