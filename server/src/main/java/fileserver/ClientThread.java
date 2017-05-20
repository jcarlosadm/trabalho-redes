package fileserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import fileserver.util.PrintData;

public class ClientThread implements Runnable {

	private Socket connectionSocket;

	public ClientThread(Socket s) {
		this.connectionSocket = s;
	}

	@Override
	public void run() {
		DataInputStream inFromClient = null;
		DataOutputStream outToClient = null;
		try {
			inFromClient = new DataInputStream(connectionSocket.getInputStream());
			outToClient = new DataOutputStream(connectionSocket.getOutputStream());

			while (true) {
				System.out.println("======================================");
				
				String clientSentence = inFromClient.readUTF();

				String message = Protocol.execute(clientSentence);
				if (message == null)
					System.out.println("error to parse command.");
				else {
					PrintData.print("message to client", message);

					outToClient.writeUTF(message);
					outToClient.flush();
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
