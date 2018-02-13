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
	
	
	public void DISCONNECT() throws IOException
	{
		OUT.println(GUI.Usr + " Has Disconnected."); // broadcast who disconnected
		OUT.flush(); // flush output stream
		SOCK.close(); // close socket
		JOptionPane.showMessageDialog(null, "you Disconncted"); // tell user they have disconnected
	}
}
