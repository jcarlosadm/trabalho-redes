package fileserver;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import br.com.commons.properties.PropertiesManager;
import fileserver.parserentry.InputParser;
import fileserver.parserentry.Protocol;
import fileserver.util.PrintData;

public class Client {

	private static final String HELP_FILE_LOCATION = "/help_text";

	public static void main(String[] args) {

		String hostIp = "";
		int hostPort = 0;

		Socket clientSocket;
		try {
			hostIp = PropertiesManager.getProperty("host.ip");
			hostPort = (int) Integer.parseInt(PropertiesManager.getProperty("host.port"));

			clientSocket = new Socket(hostIp, hostPort);
		} catch (IOException e) {
			System.out.println("error to create socket on port " + hostPort + " on address " + hostIp);
			e.printStackTrace();
			return;
		}

		try {
			BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			DataInputStream inFromServer = new DataInputStream(clientSocket.getInputStream());

			PrintData.print("message to server:", "get_public_key");
			outToServer.writeUTF("get_public_key");
			outToServer.flush();
			Protocol.execute(inFromServer.readUTF());

			System.out.println("=================================\n");
			System.out.println("for help, type: -help");

			while (true) {
				System.out.print("type command: ");
				String messageToServer = inFromUser.readLine();

				if (messageToServer.toLowerCase().equals("exit"))
					break;
				else if (messageToServer.equals("-help") || messageToServer.equals("help")) {
					showHelp();
					continue;
				}

				String formatedMessageToServer = InputParser.format(messageToServer);
				if (formatedMessageToServer == null) {
					System.out.println("error to parse command. for help, type: -help");
					continue;
				}

				PrintData.print("message to server:", formatedMessageToServer);

				outToServer.writeUTF(formatedMessageToServer);
				outToServer.flush();

				String response = inFromServer.readUTF();
				Protocol.execute(response);

				System.out.println("=================================\n");
			}

			clientSocket.close();
			inFromUser.close();
			outToServer.close();
			inFromServer.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error in create/manipulate streams");
			return;
		}
	}

	private static void showHelp() throws Exception {

		BufferedReader bReader = new BufferedReader(
				new InputStreamReader(Client.class.getResourceAsStream(HELP_FILE_LOCATION)));

		String line = "";
		while ((line = bReader.readLine()) != null)
			System.out.println(line);
		System.out.println("\n");

		bReader.close();
	}

}
