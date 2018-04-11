import java.io.*;
import java.net.*;
import java.util.*;
import Message_Objs.*;
//import java.time.*; // check line 132, time comes later

public class Server_return implements Serializable, Runnable{ // as a threaded object, this will automatically call run

		/**
	 * 
	 */
	private static final long serialVersionUID = -8710493301100415086L;
		
	// global variables
		Socket SOCK;						//global socket variable
		private ObjectInputStream INPUT;	// will be used to read in input
		private ObjectOutputStream OUT; // will be used to write to the socket
		String MESSAGE = "";				// message global declaration for later use
		
		public Server_return(Socket X)
		{
			Server1.PlaceChecker("socket defined");
			this.SOCK = X; // define sock upon creation of thread
		}

		public void ConnectionCheck() throws IOException 
		{
			Server1.PlaceChecker("checking connection");
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
					
					Server1.PlaceChecker("creating output");
					OUT = new ObjectOutputStream(SOCK.getOutputStream()); // gets output stream
					Server1.PlaceChecker("creating input");
					INPUT = new ObjectInputStream(SOCK.getInputStream()); //gets input stream
					
					Server1.PlaceChecker("about to read");
					while(true) // this is the thread we are running
					{
						ConnectionCheck(); //check if we are connected
						
						Conglomp object = (Conglomp) INPUT.readObject();
						
						Server1.PlaceChecker(object.type);
						if(object.type.equals("message")) // checks if object is a message
						{
							Server1.PlaceChecker("it's a message object");
							String test = object.s2;
							
							Server1.PlaceChecker(object.type);
							
							if(test.equals("trade")) //is it trade data?
							{
							System.out.println("trade content");; // no, its a conversation
							}
							else if(test.equals("talk")) 
							{
								Server1.PlaceChecker("about to echo");
								Client_Echo(object.user +": "+ object.s1); // yes, show me the data
							}
							
						}
						else if(object.type.equals("search")) // checks if object is a search request
						{
							Server1.PlaceChecker("it's a search object");
							search_database(object);
							
						}
						else if(object.type.equals("trade")) // checks if object is a trade input
						{
							Server1.PlaceChecker("it's a trade object");
							int i = (int) new Date().getTime(); //saves time as an int so we can later enter it into the table
							String j = Integer.toString(i);
							add_item(object.s1, object.s2, j );
							
						}
						else if(object.type.equals("connect"))
						{
							Server1.PlaceChecker("it's a connect object");
							System.out.println("here");
							
							
							if( object.s1.equals("disconnect")) // if client wants to disconnect
							{
							for (person_obj X : Server1.Clients) // check every member of the client array
							{
								if(X.name.equals(object.user) && X.sock == OUT) // try to find a matching pair
								{
									Conglomp Decon = new Conglomp("message","Has disconnected",null,X.name);
									
									OUT.writeObject(Decon); // when you do, tell everyone they disconnected
									OUT.flush();// send the message
									
									Server1.Clients.remove(X); // actually disconnect them
									// possibly send an update to the list to the users, just like message, but for the online box
								}
							}
							}
							else // if client just connected
							{
								person_obj adder = new person_obj(object.user, OUT);
								Server1.Clients.add(adder);
								Server1.PlaceChecker("added man to list :" + object.user +":   with stream :" + OUT);
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
		
		
		public static void add_item(String Have, String Want, String Time) throws IOException // will be changed later to use database
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter("db.txt",true));
			writer.append("\n");
			writer.append(Have); // add first item to db
			writer.append(Want); // '' second '' 
			writer.append(Time);
			writer.close();
			return;
			
		}
		
		
		public static void search_database(Conglomp x) throws IOException // passes two items to the search method
		{
			return;		
		}
		

		public void Client_Echo(String Msg)
		{
			Server1.PlaceChecker("beforedef");
			
			Conglomp temp = new Conglomp("message", Msg, null, "server");
			
			Server1.PlaceChecker("beforeEcho");
			for(person_obj X : Server1.Clients) // for every client in the list
			{
				try 
				{
					Server1.PlaceChecker("echoing message");
					X.sock.writeObject(temp);
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				
				
				
				System.out.println("sent to: "+  X.name); // what admin will see
			}
			
			
		}
}