package fileserver.util;

public class ClientState {

	private boolean logged = false;

	private static final ThreadLocal<ClientState> _threadLocalInstance = new ThreadLocal<ClientState>() {
		protected ClientState initialValue() {
			return new ClientState();
		}
	};
	
	public static ClientState getInstance() {
		return _threadLocalInstance.get();
	}

	private ClientState() {
	}

	public void login() {
		this.logged = true;
	}

	public void logout() {
		this.logged = false;
	}

	public boolean isLogged() {
		return this.logged;
	}

}
