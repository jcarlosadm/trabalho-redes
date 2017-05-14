package fileserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread implements Runnable {

	private Socket connectionSocket;

	public ClientThread(Socket s) {
		this.connectionSocket = s;
	}

	@Override
	public void run() {
		try {
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			
			while (true) {
				String clientSentence = inFromClient.readLine();
				System.out.println(clientSentence + System.lineSeparator());
				
				outToClient.writeBytes("ok" + "\n");
				if (clientSentence.toLowerCase().trim().equals("exit")) {
					System.out.println("exiting...");
					break;
				}
			}
			
			inFromClient.close();
			outToClient.close();
		} catch (IOException e) {
			System.out.println("error on client thread. exiting.");
			e.printStackTrace();
		}

	}

}
