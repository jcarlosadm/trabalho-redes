package fileserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import fileserver.parserentry.InputParser;
import fileserver.parserentry.Protocol;

public class Client {

	private static final int HOST_PORT = 6789;
	private static final String HOST_IP = "localhost";

	public static void main(String[] args) {
		
		Socket clientSocket;
		try {
			clientSocket = new Socket(HOST_IP, HOST_PORT);
		} catch (IOException e) {
			System.out.println("error to create socket on port " + HOST_PORT + " on address " + HOST_IP);
			e.printStackTrace();
			return;
		}
		
		// TODO criar/recuperar par de chaves locais. carlos

		try {
			BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			outToServer.writeBytes("get_public_key\n");
			Protocol.execute(inFromServer.readLine());
			
			while (true) {
				String messageToServer = inFromUser.readLine();

				if (messageToServer.toLowerCase().equals("exit"))
					break;
				
				String formatedMessageToServer = InputParser.format(messageToServer);
				if (formatedMessageToServer == null) {
					System.out.println("error to parse command. for help, type: -help");
					continue;
				}

				outToServer.writeBytes(formatedMessageToServer + "\n");

				String response = inFromServer.readLine();
				Protocol.execute(response);
			}

			clientSocket.close();
			inFromUser.close();
			outToServer.close();
			inFromServer.close();
			
		} catch (Exception e) {
			System.out.println("error to create/manipulate streams");
			return;
		}
	}

}
