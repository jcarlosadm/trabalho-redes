package fileserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {

	// TODO manipulate exceptions
	public static void main(String[] args) throws Exception {
		Socket clientSocket = new Socket("localhost", 6789);
		
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
		boolean exit = false;
		while (exit == false) {
			
			String messageToServer = inFromUser.readLine();
			outToServer.writeBytes(messageToServer + "\n");
			
			String response = inFromServer.readLine();
			System.out.println(response + System.lineSeparator());
			
			if (messageToServer.toLowerCase().equals("exit")) {
				System.out.println("exiting...");
				clientSocket.close();
				exit = true;
			}
		}
		
		inFromUser.close();
		outToServer.close();
		inFromServer.close();
	}

}
