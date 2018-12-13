import java.net.*;
import java.io.*;
import java.util.*;
class ChatServer 
{
  ServerSocket server;
  ArrayList <Socket> list=new ArrayList<Socket>(); 
  public ChatServer()
  {
     try 
     {
	server=new ServerSocket(2009);
	System.out.println("Server started...");
	while(true)
	{
	  Socket soc=server.accept();
	  System.out.println("User logged in...");
	  list.add(soc);
	  new ChatThread(soc,list).start();
	}
     }
     catch(Exception ex)
     {
	System.out.println(ex);
     }  	
  }
  public static void main(String args[])
  {
	new ChatServer();
  }
}
class ChatThread extends Thread
{
  Socket soc; 
  ArrayList <Socket> list;
  public ChatThread(Socket sc,ArrayList <Socket> lt)
  {
	soc=sc;
	list=lt;
  } 
  public void run()
  {
    try
    {
        DataInputStream dis=new DataInputStream(soc.getInputStream());	
        while(true)
        {
	String msg=dis.readUTF();
	if(msg.equals("@@@@***L%%%@@@999$$$"))
	{
	  list.remove(soc);
	  System.out.println("User logged out...");
	  break;
	}
	for(int i=0;i<list.size();i++)
	{
	   Socket sc=list.get(i);
                  DataOutputStream dos=new DataOutputStream(sc.getOutputStream());
	   dos.writeUTF(msg);
	}
        }  
    }
    catch(Exception ex)
    {
	System.out.println(ex);
    }
  }
}





