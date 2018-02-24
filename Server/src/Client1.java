import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.JOptionPane;

public class Client1 implements Runnable
{
	//globals
	
	Socket SOCK; // makes the socket persist, global makes sure socket doesnt delete itself
	Scanner INPUT;
	Scanner SEND = new Scanner(System.in);
	PrintWriter OUT;

	
	public Client1(Socket X)
	{
		this.SOCK = X;// constructs socket for the client
	}
	
	public void run()
	{
		try
		{
			try {
				INPUT = new Scanner(SOCK.getInputStream());
				OUT = new PrintWriter(SOCK.getOutputStream());
				OUT.flush(); //sends data on its way
				CheckStream();
			}
			finally {
				SOCK.close();
			}
		}
		catch(Exception X) { System.out.print(X); }
	}
	
	public void CheckStream()
	{
		while(true)
		{
			RECIEVE();
		}
	}
	
	public void RECIEVE()
	{
		if(INPUT.hasNext())
		{
			String MESSAGE = INPUT.nextLine();
			
			if(MESSAGE.contains("#?!")) // detects server commands
			{
				String TEMP1 = MESSAGE.substring(3); // strips preamble
				TEMP1 =TEMP1.replace("[","");// surrounds command with brackets
				TEMP1 = TEMP1.replace("]","");//	''
				
				String[] CurrentUsers = TEMP1.split(", "); //delimiter used to split and store string
				
				GUI.JL_ONLINE.setListData(CurrentUsers);	//sets list data from newly created array			
			}
			else 
			{
				GUI.TA_CONVERSATION.append(MESSAGE+"\n"); //echos message to text area
			}
			
		}
		
	}
	
	public void DISCONNECT() throws IOException
	{
		OUT.println(GUI.Usr + " Has Disconnected."); // broadcast who disconnected
		OUT.flush(); // flush output stream, sends data on its way
		SOCK.close(); // close socket
		JOptionPane.showMessageDialog(null, "you Disconncted"); // tell user they have disconnected
		System.exit(0);
	}
	
	public void SEND (String X)
	{
		OUT.println(GUI.Usr + ": " + X); //prints users name before message
		OUT.flush(); // flush datastream, sends data on its way
		GUI.TF_MESSAGE.setText(""); // sets textfield to null after send
	}
}
