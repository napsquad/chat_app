import java.io.*;

import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUI 
{
		//globals
		private static Client1 chat;
		public static String Usr = "anon";
		
		
		//main menu globals
		
		public static JFrame  main = new JFrame();
		private static JButton B_ABOUT = new JButton();
		private static JButton B_CONNECT = new JButton();
		private static JButton B_DISCONNECT = new JButton();
		private static JButton B_HELP = new JButton();
		private static JButton B_SEND = new JButton();
		
		private static JLabel L_MESSAGE = new JLabel("MESSAGE:");
		public static JTextField TF_MESSAGE = new JTextField(20);
		private static JLabel L_CONVERSATION = new JLabel();
		public static JTextArea TA_CONVERSATION = new JTextArea();
		private static JScrollPane SP_CONVERSATION = new JScrollPane();
		private static JLabel L_ONLINE = new JLabel();
		public static JList JL_ONLINE = new JList();
		private static JScrollPane SP_ONLINE = new JScrollPane();
		private static JLabel L_LoggedInAs = new JLabel();
		private static JLabel L_LoggedInAsBox = new JLabel();
		
		// login window memes
		
		public static JFrame LoginWindow = new JFrame();
		public static JTextField TF_UsernameBox = new JTextField(20);
		private static JButton B_ENTER = new JButton();
		private static JLabel L_EnterUsername = new JLabel("Enter your username: ");
		private static JPanel P_Login = new JPanel();
		
//--------------------------------------------------------------------------------------------
		
		public static void main(String args[])
		{
			BuildMainWindow();
			Initialize();
			
		}
		
		public static void BuildMainWindow()
		{
			main.setTitle(Usr +"'s chat" );
			main.setSize(450,500);
			main.setLocation(220, 180);
			main.setResizable(false);
			ConfigureMainWindow();
			//MainWindow_Action();
			main.setVisible(true);
			
		}
//------------------------------------------------------------------------------------------
		public static void ConfigureMainWindow()
		{
			main.setBackground(new java.awt.Color(255,255,255));
			main.setSize(500,320);
			main.getContentPane().setLayout(null);
			
			B_SEND.setText("SEND");
			main.getContentPane().add(B_SEND);
			B_SEND.setBounds(260, 40,81,25);
			
			B_DISCONNECT.setText("leave");
			main.getContentPane().add(B_DISCONNECT);
			B_DISCONNECT.setBounds(10,40,110,25);
			
			B_CONNECT.setText("join");
			main.getContentPane().add(B_CONNECT);
			B_CONNECT.setBounds(120,40,110,25);
			
			B_HELP.setText("HELP");
			main.getContentPane().add(B_HELP);
			B_HELP.setBounds(420,40,75,25);
			
			B_ABOUT.setText("ABOUT");
			main.getContentPane().add(B_ABOUT);
			B_ABOUT.setBounds(340,40,110,25);
			
			L_MESSAGE.setText("MESSAGE : ");
			main.getContentPane().add(L_MESSAGE);
			L_MESSAGE.setBounds(5, 20, 260, 20);
			
			TF_MESSAGE.setForeground(new java.awt.Color(0,0,255));
			TF_MESSAGE.requestFocus();
			main.getContentPane().add(TF_MESSAGE);
			TF_MESSAGE.setBounds(70,22,260,16);
			
			L_CONVERSATION.setHorizontalAlignment(SwingConstants.CENTER);
			L_CONVERSATION.setText("Conversation");
			main.getContentPane().add(L_CONVERSATION);
			L_CONVERSATION.setBounds(100,70,140,16);
			
			TA_CONVERSATION.setColumns(20);
			TA_CONVERSATION.setFont(new java.awt.Font("Times New Roman", 0 ,12));
			TA_CONVERSATION.setForeground(new java.awt.Color(0,0,255));
			TA_CONVERSATION.setLineWrap(true);
			TA_CONVERSATION.setRows(5);
			TA_CONVERSATION.setEditable(false);
			
			SP_CONVERSATION.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			SP_CONVERSATION.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			SP_CONVERSATION.setViewportView(TA_CONVERSATION);
			main.getContentPane().add(SP_CONVERSATION);
			SP_CONVERSATION.setBounds(10,90,330,180);
			
			L_ONLINE.setHorizontalAlignment(SwingConstants.CENTER);
			L_ONLINE.setText("online");
			L_ONLINE.setToolTipText("");
			main.getContentPane().add(L_ONLINE);
			L_ONLINE.setBounds(350,70,130,16);
			
			String[] TEST = {"USER1","USER2","USER3","USER4"};
			JL_ONLINE.setForeground(new java.awt.Color(0,0,255));
			JL_ONLINE.setListData(TEST);
			
			SP_ONLINE.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			SP_ONLINE.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			SP_ONLINE.setViewportView(JL_ONLINE);
			main.getContentPane().add(SP_ONLINE);
			SP_ONLINE.setBounds(350,90,130,180);
			
			L_LoggedInAs.setFont(new java.awt.Font("Times New Roman",0,12));
			L_LoggedInAs.setText("Currently logged in");
			main.getContentPane().add(L_LoggedInAs);
			L_LoggedInAs.setBounds(348,0,140,15);
			
			L_LoggedInAsBox.setHorizontalAlignment(SwingConstants.CENTER);
			L_LoggedInAsBox.setFont(new java.awt.Font("Times New Roman",0,12));
			L_LoggedInAsBox.setForeground(new java.awt.Color(255,0,0));
			L_LoggedInAsBox.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0,0,255)));
			main.getContentPane().add(L_LoggedInAsBox);
			L_LoggedInAsBox.setBounds(340,17,150,20);
				
		}		
		
//-------------------------------------------------------------------------------------------
		
		public static void Initialize()
		{
			B_SEND.setEnabled(false);
			B_DISCONNECT.setEnabled(false);
			B_DISCONNECT.setText("JOIN");
			B_CONNECT.setEnabled(false));
			B_DISCONNECT.setText("LEAVE");
			
		}
		
		public static void BuildLoginWindow()
		{
			LoginWindow.setTitle("NAME: ");
			LoginWindow.setSize(400,100);
			LoginWindow.setLocation(250,200);
			LoginWindow.setResizable(false);
			P_Login = new JPanel();
			P_Login.add(L_EnterUsername);
			P_Login.add(TF_UsernameBox);
			P_Login.add(B_ENTER);
			
			LoginWindow.add(P_Login);
			
			//Login_Action();
			LoginWindow.setVisible(true);
		}
		
		public static void Connect()
		{
			try {
				
				final int PORT = 444;
				final String HOST = "memes"; // can be domain name or ip
				
				Socket SOCK = new Socket(HOST,PORT);
				System.out.println("you are connected to" + HOST);
				
				chat = new Client1(SOCK); // still need to build the constructor for the client
				
				PrintWriter OUT  = new PrintWriter(SOCK.getOutputStream());
				OUT.println(Usr); // send users name
				OUT.flush(); // flush stream
				
				Thread X = new Thread(chat); // building thread 
				X.start();
			}
			catch(Exception X)
			{
				System.out.print(X);
				JOptionPane.showMessageDialog(null, "the server is not responding");
				System.exit(0);
			}
		}
		
		
//----------------------start logon button implementation---------------------------------
		
		public static void Login_Action()
		{
			
			B_ENTER.addActionListener
			(
				new java.awt.event.ActionListener()
				{ 
					public void actionPerformed(ActionEvent e)
						{ACTION_B_ENTER();}			
				}
			);
			
		}
		
		public static void ACTION_B_ENTER() 
		{
			if(!TF_UsernameBox.getText().equals("")) //checks if logon is null
			{
				Usr = TF_UsernameBox.getText().trim(); // set user to what is entered
				L_LoggedInAsBox.setText(Usr);		// show their name on window
				Server1.Users.add(Usr);				// adds user to jlist when logged in
				main.setTitle(Usr + "'s chat");		// change  windows jlabel to show their name
				LoginWindow.setVisible(false);
				B_SEND.setEnabled(true); 			// once conncted, allow sending
				B_DISCONNECT.setEnabled(true); 		//once connected, allow disconnection
				B_CONNECT.setEnabled(false); 		// shouldnt be able to connected after youre already connected
				Connect();
				
			}
			else
			{JOptionPane.showMessageDialog(null, "please enter a name");} // if logon is null, prompt for a new one
		}
//-------------------------logon button end------------------------------------------------

		public static void MainWindow_Action() // adding functions to other buttons
		{
			B_SEND.addActionListener( // maps to send 
					new java.awt.event.ActionListener()
					{
						public void actionPerformed(java.awt.event.ActionEvent evt)
						{ ACTION_B_SEND(); }	
					}
			);
			
			B_DISCONNECT.addActionListener( //maps to disconnect
					new java.awt.event.ActionListener()
					{
						public void actionPerformed(java.awt.event.ActionEvent evt)
						{ ACTION_B_DISCONNECT(); }	
					}
			);
			
			B_CONNECT.addActionListener( // I LLOOOOOOVEEEEE BITCOOOOONNNENEEEEECCCT
					new java.awt.event.ActionListener()
					{
						public void actionPerformed(java.awt.event.ActionEvent evt)
						{ ACTION_B_CONNECT(); }	
					} // maps to connect
			);
			
			B_HELP.addActionListener( // I LLOOOOOOVEEEEE BITCOOOOONNNENEEEEECCCT
					new java.awt.event.ActionListener()
					{
						public void actionPerformed(java.awt.event.ActionEvent evt)
						{ ACTION_B_HELP(); }	
					} // maps to connect
			);
			
			B_ABOUT.addActionListener( // I LLOOOOOOVEEEEE BITCOOOOONNNENEEEEECCCT
					new java.awt.event.ActionListener()
					{
						public void actionPerformed(java.awt.event.ActionEvent evt)
						{ ACTION_B_ABOUT(); }	
					} // maps to connect
			);
		
		}
//------------------------------------------------------------------------------------------		
		
		
		
		
		
}
