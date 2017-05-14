package fileserver;

import java.net.Socket;

public class ClientThread implements Runnable {

	private Socket connectionSocket;

	public ClientThread(Socket s) {
		this.connectionSocket = s;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
