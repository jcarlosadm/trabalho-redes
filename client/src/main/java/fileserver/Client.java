package fileserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) throws Exception {
		Socket clientSocket = new Socket("localhost", 6789);
		
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(
				System.in));
		
		DataOutputStream outToServer = new DataOutputStream(
				clientSocket.getOutputStream());
		
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));
		
		clientSocket.close();
	
	}
	
}
