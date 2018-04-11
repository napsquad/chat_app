import java.io.*;
import java.net.*;
import java.util.*;
import Message_Objs.*;

public class Client1 implements Runnable, Serializable
{
	//globals
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4220256892269154006L; // serialized for sending
	
	public static Socket SOCK; // makes the socket persist, global makes sure socket doesn't delete itself
	public ObjectInputStream INPUT; // defined for later use in order to read objects
	public static ObjectOutputStream OUT; // defined for later use in thread for sending Objects
	
	Scanner SEND = new Scanner(System.in); // just used to set username in window
	
	public Client1(Socket X) throws IOException
	{
		Client1.SOCK = X;// constructs socket for the client
	}
	
	
	public void run() // this is the thread that we  are going to run
	{
		
		try 
		{
			OUT = new ObjectOutputStream(SOCK.getOutputStream()); // stream to write objects
			INPUT = new ObjectInputStream(SOCK.getInputStream()); // stream to read objects
		} 
		catch (IOException e1) 
		{
			e1.printStackTrace(); // catch error if there is no stream
		}
				
		
		Conglomp addme = new Conglomp("connect", "connect", null, GUI.Usr);
		System.out.println("before conn send :" + addme.user);
				
				try 
				{
					OUT.writeObject(addme); // put object in buffer to later send it
				} 
				catch (IOException e)
				{
					e.printStackTrace();
				} 
				
				
				try 
				{
					CheckStream(); // always checks for new input from users
				}
				catch (ClassNotFoundException e) 
				{
					e.printStackTrace();
				}
				catch (IOException e) 
				{
					e.printStackTrace();
				} 
		
		
		
	}
	
	
	public void CheckStream() throws ClassNotFoundException, IOException
	{
		while(true)
		{
			System.out.println("recieving");
			RECIEVE(); // when we get an object, use recieve
		}
	}
	
	
	public void RECIEVE() throws ClassNotFoundException, IOException, EOFException
	{
		Conglomp x = (Conglomp) INPUT.readObject();
		
		if(x != null)
		{
			System.out.println("i am before checks");
			if( x.type.equals("message")) // checks if object is a message object
			{
				System.out.println("its a message object");

				if(x.s2 == "trade") //is it trade data?
				{
				GUI.TRADE_POST_AREA.append(x.s1 + "\n"); // yes, print to trade window
				}
				else 
				{
					GUI.TA_CONVERSATION.append(x.s1 + "\n"); // no, its a conversation
				}
				
			}
			else if(x.type.equals("search")) // checks if object is a search request
			{
				System.out.println("its a search object");
				GUI.TA_CONVERSATION.append("got search packet from" + x.user);
			}
			else if(x.type.equals("trade")) // checks if object is a trade input
			{
				System.out.println("its a trade object");
				GUI.TA_CONVERSATION.append("request for" + x.s1);
			}
			else if(x.type.equals("connect")) // checks if object is a connection object
			{
				System.out.println("its a connect object");
				GUI.TA_CONVERSATION.append("someones trying to connect:  " + x.user + " , wonder what they want" );
			}
		}
		
	}
	
	
	public void DISCONNECT() throws IOException
	{
		
		Conglomp x = new Conglomp("connect", "disconnect", null, GUI.Usr); 
		
		OUT.writeObject(x); 			// put disconnect object into stream
		System.out.println("should not be null : " + OUT); // just checking to make sure that the stream exists 
		SOCK.close();		// close the socket
		System.exit(0);		// close the GUI
		
		
	}
	
	
	public void SENDI(Conglomp x) throws IOException
	{
		
		OUT.writeObject(x); // write our object out
		
		GUI.TF_MESSAGE.setText(""); // sets text field to null after send
		System.out.println("wrote the thing");
	}
	
	
}
