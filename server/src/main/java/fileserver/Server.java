package fileserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import fileserver.keys.ServerKeys;

public class Server {

	private static final int ACCEPT_ERROR_MAX = 5;
	private static final int PORT = 6789;

	private static final String WAITING_CONECTTION_MESSAGE = "SERVER STARTED, WAITING CONNECTION ON PORT ";

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		try {
			ServerKeys.getInstance();
		} catch (Exception e) {
			System.out.println("Error to generate cryptography keys. Exiting ...");
			return;
		}
		
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			System.out.println("Error to create socket. exiting...");
			e.printStackTrace();
			return;
		}
		
		System.out.println(WAITING_CONECTTION_MESSAGE + PORT + "...");

		int acceptErrorCount = 0;
		while (true) {
			try {
				Socket connectionSocket = serverSocket.accept();
				Thread t = new Thread(new ClientThread(connectionSocket));
				t.start();
			} catch (IOException e) {
				System.out.println("error to accept connection.");
				e.printStackTrace();

				++acceptErrorCount;
				if (acceptErrorCount > ACCEPT_ERROR_MAX) {
					System.out.println("exiting.");
					break;
				}
			}
		}
	}

}
