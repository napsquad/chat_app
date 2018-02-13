import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.JOptionPane;

public class Client1 
{
	//globals
	
	Socket SOCK;
	Scanner INPUT;
	Scanner SEND = new Scanner(System.in);
	PrintWriter OUT;

	
	public Client1(Socket X)
	{
		this.SOCK = X;
	}
	
	public void run()
	{
		try
		{
			try {
				INPUT = new Scanner(SOCK.getInputStream());
				OUT = new PrintWriter(SOCK.getOutputStream());
				OUT.flush();
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
			
			if(MESSAGE.contains("client said"))
			{
				String TEMP1 = MESSAGE.substring(3);
				TEMP1 =TEMP1.replace("]","");
				TEMP1 = TEMP1.replace("[","");
				
				String[] CurrentUsers = TEMP1.split(", ");
				GUI.JL_ONLINE.setListData(CurrentUsers);				
			}
			else 
			{
				GUI.TA_CONVERSATION.append(MESSAGE+"\n");
			}
			
		}
		
		
	}
	
	public void DISCONNECT() throws IOException
	{
		OUT.println(GUI.Usr + " Has Disconnected."); // broadcast who disconnected
		OUT.flush(); // flush output stream
		SOCK.close(); // close socket
		JOptionPane.showMessageDialog(null, "you Disconncted"); // tell user they have disconnected
		System.exit(0);
	}
	
	public void SEND (String X)
	{
		OUT.println(GUI.Usr + ": " + X); //prints users name before message
		OUT.flush(); // flush datastream
		GUI.TF_MESSAGE.setText(""); // sets textfield to null post send
	}
}
