import java.net.*;
import java.io.*;
import java.util.*;
class ChatClient
{
  Socket soc;
  public ChatClient(String name)
  {
     try 
     {
	soc=new Socket("localhost",2009);
	new ReadThread().start();
	DataOutputStream dos=new DataOutputStream(soc.getOutputStream());
	Scanner sc=new Scanner(System.in);
	while(true)
	{
	  String msg=sc.nextLine();
	  dos.writeUTF(name+" said:"+msg);
	}
     }
     catch(Exception ex)
     {
	System.out.println(ex);
     }  	
  }
  class ReadThread extends Thread
  {
    public void run()
    {
       try
       {
          DataInputStream dis=new DataInputStream(soc.getInputStream());
          while(true)
          {
	System.out.println(dis.readUTF());
          }  	
       }
       catch(Exception ex)
       {
	System.out.println(ex);
       } 
    }
  }
  public static void main(String args[])
  {
	new ChatClient(args[0]);
  }
}