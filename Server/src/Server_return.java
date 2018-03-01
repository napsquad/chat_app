import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.JOptionPane;

public class Server_return implements Runnable{ // as a threaded object, this will automatically call run

		// global variables
		Socket SOCK;				//global socket variable
		private Scanner INPUT;	// will be used to read in input
		private PrintWriter OUT; // will be used to write to the socket
		String MESSAGE = "";		// message declaration for later use
		
		public Server_return(Socket X)
		{
			this.SOCK = X;
		}

		public void ConnectionCheck() throws IOException 
		{
			if(!SOCK.isConnected())// if not socket is connected
			{
				for(int i = 1; i <= Server1.Connections.size(); i++)
				{
					if(Server1.Connections.get(i) == SOCK)
					{
						Server1.Connections.remove(i); // if the socket is not connected we remove it from
														// the array list of connections
					}
				}
				
				for(int i =1; i <=Server1.Connections.size();i++)
				{
					Socket TEMP_SOCK = (Socket) Server1.Connections.get(i-1);
					PrintWriter TEMP_OUT = new PrintWriter(TEMP_SOCK.getOutputStream());
					TEMP_OUT.println(TEMP_SOCK.getLocalAddress().getHostName() + "Disconnected");
					TEMP_OUT.flush();
					
					System.out.println(TEMP_SOCK.getLocalAddress().getHostName() + "Disconnected");
				}
			}		
		}
		
		public void run()
		{
			try 
			{
				try 
				{
					INPUT = new Scanner(SOCK.getInputStream()); //gets input stream
					OUT = new PrintWriter(SOCK.getOutputStream()); // gets output stream
					
					while(true)
					{
						ConnectionCheck();
						
						if(!INPUT.hasNext())
						{return;}
						
						MESSAGE = INPUT.nextLine();
						
						if(MESSAGE.substring(0, 6) == "!result") // if user wishes to search for an item in a database
						{
							search_database(MESSAGE, OUT);
							
						}
						else if(MESSAGE.substring(0, 7) == "!additem")
						{
							add_item(MESSAGE);
						}
						
						else
						{
						System.out.println("client said: " + MESSAGE); // echos what client said in console
						
						for(int i = 1; i<=Server1.Connections.size(); i++)
						{
							Socket TEMP_SOCK = (Socket) Server1.Connections.get(i-1); // gets new socket from the Array List of sockets
							PrintWriter TEMP_OUT = new PrintWriter(TEMP_SOCK.getOutputStream()); // display line and ip and host name that the user has disconnected
							TEMP_OUT.println(MESSAGE); // cycles through all sockets in the list and sends the sent message to all of them
							TEMP_OUT.flush(); // flushes out data stream
							System.out.println("sent to:"+  TEMP_SOCK.getLocalAddress().getHostName()); // what admin will see
						}
						}
						
					}
					
				}
				finally
				{
					SOCK.close();
				}
			}
			catch(Exception x) {System.out.println(x);}
		}
		
		public static void add_item(String Y) throws IOException
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter("db.txt",true));
			writer.append("\n");
			writer.append(Y);
			writer.close();
			return;
			
		}
		public static void search_database(String Y, PrintWriter X) throws IOException // passes two items to the serach method
		{
			BufferedReader reader = new BufferedReader(new FileReader("db.txt")); // open file for reading 
			
			while(reader.readLine() != null) // while file has a line to show
			{
				String line = reader.readLine(); // store new line in String line
				if(line.contains(Y)) // checks if the line we are looking at contains the item we are searching for 
				{
					X.println("!SEARCH" + line); // if the line does contain this varible then we send append the SEARCH keyword and send to the client
					X.flush(); // flush data to client
				}
				
			}
			reader.close();
			
			return;
			
		}
}
