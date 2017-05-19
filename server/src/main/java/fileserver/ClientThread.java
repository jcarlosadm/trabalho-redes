package fileserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

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
				// TODO String clientSentence = inFromClient.readLine();
				String clientSentence = inFromClient.readUTF();
				System.out.println("-----------------------\n" + clientSentence + "\n-------------------------------");

				String message = Protocol.execute(clientSentence);
				if (message == null)
					System.out.println("error to parse command.");
				else {
					// TODO outToClient.writeBytes(message + "\n");
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
