import java.io.*;
import java.net.*;
import java.util.*;

import javax.security.auth.x500.X500Principal;
import javax.swing.JOptionPane;

public class Server1
{
	public static ArrayList<Socket> Connections = new ArrayList<Socket>(); // once a client connects, add their socket to this AR
	public static ArrayList<String> Users = new ArrayList<String>();
	
	public static void main(String[] args) throws IOException
	{
		try {
			final int port  = 9002;// defines port for server to operate on
			ServerSocket SERVER = new ServerSocket(port); // creates server port
			System.out.println("looking for Connections"); // tells user that we are looking for connections
			
			while(true)
			{
				Socket Accepts = SERVER.accept(); //accepts connections from clients
				Connections.add(Accepts); // add new connection to the connection array list
				
				System.out.println("Client connected from " + Accepts.getLocalAddress().getHostName()); // shows  new client address
				
				AddUserName(Accepts);  // adds user to the list of users
				
				Server_return CHAT = new Server_return(Accepts); // accepts socket object, builds a server return object for every new client
				Thread X = new Thread(CHAT); // create new thread
				X.start(); // starts subprocess
				
			}
		}
		catch(Exception X) {System.out.println(X);} // catches port not found error
		
		
	} 
	// everything that involves streaming using sockets should
	// be in a try catch block
	
	public static void AddUserName(Socket x) throws IOException
	{
		Scanner input = new Scanner(x.getInputStream()); // gets input from socket
		String UserName = input.nextLine(); //gets username from the first line 
		
		 Users.add(UserName); // add username to user array
		 
		 for (int i = 1; i<=Server1.Connections.size();i++) // updates everyones jlist object so you know who is online
		 {
			 Socket temp = (Socket) Server1.Connections.get(i-1);
			 PrintWriter OUT = new PrintWriter(temp.getOutputStream());
			 OUT.println("#?!" + Users); // prefaces command with these symbols for later use in populating jlist object
			 OUT.flush(); // sends the data on its way to the server, needs to be called whenever we are sending data
			 
		 }
		
	}
	public static void ItemWriter(String Y) throws IOException
	{
		BufferedWriter writer = new BufferedWriter(new FileWriter("db.txt",true));
		writer.append("\n");
		writer.append(Y);
		writer.close();
	}
	
}
