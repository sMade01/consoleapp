
/*
     .....................................No comment..................................
*/
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;


public class Server {
	
    protected Socket socket;
    protected FileInputStream in;
    protected FileOutputStream out;
	
	protected Server(Socket s) {
		  
	   try {
		   socket = s;
			   
		   
		   System.out.println("------SERVER CONNECTED------");
		   if(socket.isConnected()) {
			   in = (FileInputStream) socket.getInputStream();
		   }
		   getData();
		String c = new Date().toString()+"/INTERNET ADDRESS: "+socket.getInetAddress().toString()+" LOCAL ADDRESS: "+socket.getLocalAddress();
                System.out.println(c);
		Main.CONNECTIONS_LIST.add(c);
	   }
	   catch(Exception e) {
		   System.out.println(e.toString());
	   }
	 
	}

  
	
	
	
	
	private void getData(){
	  try {
		String dataName = new Date().toString().replace(':', '_');
		String filePathAndName = "C:\\Users\\sMade\\Desktop\\"+ dataName +".mp3";
		out = new FileOutputStream(new File(filePathAndName));
		int count;
		byte[] buffer = new byte[1024]; // or 4096, or more
		while ((count = in.read(buffer)) > 0) {		
		  out.write(buffer, 0, count);
		  out.flush();
		}
		
		
		socket.close();
		in.close();
		out.close();
		 
	  } catch (IOException e) {
	      e.printStackTrace();             
	  }
		
	 
	              Main.activeThreadss--;
	}
	
		
}
