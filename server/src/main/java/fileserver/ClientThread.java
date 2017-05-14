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
				System.out.println("-----------------------\n" + clientSentence + "-------------------------------");
				
				String message = Protocol.execute(clientSentence);
				if (message == null)
					System.out.println("error to parse command.");
				else
					outToClient.writeBytes(message + "\n");
			}
		} catch (IOException e) {
			System.out.println("client disconnected. closing thread.");
		}

	}

}
