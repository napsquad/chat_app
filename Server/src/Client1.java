import java.io.*;
import java.net.*;

public class Client1 
{
	public static void main(String[] args) throws Exception
	{
		Client1 CLIENT = new Client1();
		CLIENT.run();
	}
	
	public void run() throws Exception
	{
		Socket SOCK = new Socket("localhost", 9000);
		PrintStream PS = new PrintStream(SOCK.getOutputStream());
		PS.println("hello from the client");
		
		InputStreamReader IR = new InputStreamReader(SOCK.getInputStream());
		BufferedReader BR = new BufferedReader(IR);
		
		String MESSAGE = BR.readLine();
		System.out.println(MESSAGE);
		
	}

}
