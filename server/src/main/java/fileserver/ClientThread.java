package fileserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import fileserver.util.PrintData;

public class ClientThread implements Runnable {

	private Socket connectionSocket;

	public ClientThread(Socket s) {
		this.connectionSocket = s;
	}

	@Override
	public void run() {
		BufferedReader inFromClient = null;
		DataOutputStream outToClient = null;
		try {
			inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			outToClient = new DataOutputStream(connectionSocket.getOutputStream());

			while (true) {
				System.out.println("======================================");

				String clientSentence = "";
				String line = "";

				try {
					while ((line = inFromClient.readLine()).equals("end_t") == false)
						clientSentence += line + "\n";
				} catch (Exception e) {
					System.out.println("client disconnected. closing thread.");
					return;
				}

				clientSentence = clientSentence.substring(0, clientSentence.length() - 1);

				String message = Protocol.execute(clientSentence);
				if (message == null)
					System.out.println("error to parse command.");
				else {
					PrintData.print("message to client", message);

					outToClient.writeBytes(message + "\n");
					outToClient.writeBytes("end_t\n");
				}
			}
		} catch (IOException e) {
			System.out.println("client disconnected. closing thread.");
		} finally {
			try {
				inFromClient.close();
				outToClient.close();
			} catch (Exception e1) {
				System.out.println("error to close streams");
			}
		}

	}

}
