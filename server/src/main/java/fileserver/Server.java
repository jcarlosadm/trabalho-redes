package fileserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main(String[] args) throws Exception {
		System.out.println("SERVER STARTED, WAITING CONNECTION ON PORT 6789...");
		
		ServerSocket serverSocket = new ServerSocket(6789);

		while (true) {
			Socket connectionSocket = serverSocket.accept();
			Thread t = new Thread(new ClientThread(connectionSocket));
			t.start();
		}
		
	}

}
