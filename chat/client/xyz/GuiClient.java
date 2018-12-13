import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
class GuiClient extends JFrame
{
  JTextArea ta=new JTextArea(); 
  JTextField tb=new JTextField();
  JButton [] bt=new JButton[3];
  String name;
  Socket soc;
  public GuiClient()
  {
	super("Chat Window");    
	setSize(300,430);
	setLocationRelativeTo(null);
	setLayout(null);
	String [] str={"Login","Logout","Send"};
	ChatListener listener=new ChatListener();
	for(int i=0;i<bt.length;i++)
	{
	  bt[i]=new JButton(str[i]);
	  add(bt[i]);
	  bt[i].addActionListener(listener);
	}
	bt[0].setBounds(60,10,80,30);
	bt[1].setBounds(150,10,80,30);
	ta.setEditable(false);
	JScrollPane pa=new JScrollPane(ta);
	pa.setBounds(10,50,270,270);
	add(pa);
	tb.setBounds(10,335,190,30);
	add(tb);
	bt[2].setBounds(210,335,70,30);
	add(bt[2]);
	dd(false);
	setVisible(true);
  }
  class ChatListener implements ActionListener
  {
    public void actionPerformed(ActionEvent evt)
    {
       if(evt.getSource()==bt[0])//login button
       {
	name=JOptionPane.showInputDialog(GuiClient.this,"Enter your name:");
	connect();
       }  	
       if(evt.getSource()==bt[2])//send button
       {
          try
          {
	DataOutputStream dos=new DataOutputStream(soc.getOutputStream());
	dos.writeUTF(name+" said:"+tb.getText());
	tb.setText("");
          }
          catch(Exception ex)
          {
	System.out.println(ex);
          }
       }
       if(evt.getSource()==bt[1])//logout button
       {
          try
          {
	DataOutputStream dos=new DataOutputStream(soc.getOutputStream());
	dos.writeUTF("@@@@***L%%%@@@999$$$");
	System.exit(0);
          }
          catch(Exception ex)
          {
	System.out.println(ex);
          }
       }
    }
  }
  void connect()
  {
     try{
        soc=new Socket("localhost",2009); 	
        dd(true);
        new ReadThread().start();		
     }catch(Exception ex){System.out.println(ex);}
  } 
  void dd(boolean st)
  {
        bt[1].setEnabled(st); 	
        bt[2].setEnabled(st);
        tb.setEnabled(st);
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
	ta.append(dis.readUTF()+"\n");
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
	JFrame.setDefaultLookAndFeelDecorated(true);
	new GuiClient();
  }
}






