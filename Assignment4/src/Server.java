import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 * A server-side class which handles the communication between two client programs.
 * 
 * @author Pang Tin Hei (3036100179)
 * @version 1.0
 * @since 2023-12-04
 */
public class Server {
	ServerSocket serverSock;
	private int playerNum = 0;
	private int readyPlayers = 0;
	private boolean isStarted = false;
	ArrayList<PrintWriter> writers = new ArrayList<PrintWriter>();
	
	/**
	 * This method instantiates a new server
	 * @param args Default value
	 */
	public static void main(String args[]) {
		Server server = new Server();
		server.launch();
	}
	
	/**
	 * This method launches the server and allow client programs to connect to the server.
	 */
	public void launch() {
		try {
			serverSock = new ServerSocket(5050);
			System.out.println("Server is running...");
			
			while (true) {
				Socket sock = serverSock.accept();
				System.out.println("A client is connected to a server");
				playerNum += 1;
				
				ClientHandler clientHandler = new ClientHandler(sock);
				Thread clientThread = new Thread(clientHandler);
				clientThread.start();
			}
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Inner class to handle the connection between server and client programs.
	 */
	class ClientHandler implements Runnable {
		private Socket sock;
		
		/**
		 * ClientHandler constructor
		 * @param sock the server socket of the server
		 */
		public ClientHandler(Socket sock) {
			this.sock = sock;
		}
		
		/**
		 * This method overrides the method from Runnable interface
		 * It sets up the instances of connection with clients for the game.
		 */
		@Override
		public void run() {
			try {
				PrintWriter writer = new PrintWriter(sock.getOutputStream(), true);  
				writers.add(writer);
				
				InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());  
				BufferedReader reader = new BufferedReader(streamReader); 
				
				if (playerNum > 2) {
					writer.println("E2");
					sock.close();
					playerNum -= 1;
				}
				writer.println("P" + playerNum);
			
				String command;
				while ((command = reader.readLine()) != null) {
					System.out.println("Command from client: " + command);
					
					if (command.startsWith("PR")) {
						readyPlayers += 1;
						if (readyPlayers == 2 && !isStarted) {
							command = "BR";
							isStarted = true;
							readyPlayers = 0;
						}
						else {
							continue;
						}
					}
					
					if (command.startsWith("R")) {
						readyPlayers += 1;
						if (readyPlayers == 2) {
							command = "RR";
							readyPlayers = 0;
						}
					}
				
					for (PrintWriter clientWriter: writers) {
						clientWriter.println(command);
					}				
				}
				
				if (streamReader.read() == -1) {
				    System.out.println("client disconnected. socket closing...");
				    sock.close();
				    playerNum -= 1;
				    if (playerNum <= 1) {
				    		for (PrintWriter clientWriter: writers) {
							clientWriter.println("E1");
						}				
					}
				    if (playerNum <= 0) {
				    		System.exit(0);
				    }
				}
			} 
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
