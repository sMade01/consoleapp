import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
 
public class Main {
    public static final ArrayList<String>CONNECTIONS_LIST= new ArrayList<>();
    public static int activeThreadss=0;
    final static String EXIT = ">exit";
    final static String GET_THREADS = ">threads number";
    final static String HELP =">help";
    final static String SAVE =">save";
    final static String CLIAR = ">clear";

	public static final Scanner systemInput = new Scanner(System.in);
	public static void main(String[] args) throws IOException {
            System.out.println("SERVER STARTED");
         ServerSocket serverSocket = new ServerSocket(3003);
         
           Thread saveData = new Thread(()->{
                try {
                    PrintStream out = new PrintStream(new File("C:\\Users\\sMade\\Desktop\\log file.txt"));
                     for (String str : CONNECTIONS_LIST) {
                          out.println(str); out.flush();
                     }
                    out.close();
                    System.out.println("------LOGS ARE SAVED-----");
                } catch (FileNotFoundException ex) {     
                    System.out.println("! Can`t save file !");
                }
           }); //save data thread is call when you type 'save' in cmd
         
         
         
         
         new Thread(()->{  // COMAND THREAD
             while(true){
                String comand = systemInput.nextLine();
                switch(comand){
                    case EXIT : System.out.println("SYSTEM SHUTDOWN");saveData.start(); System.exit(0);
                     break;
                    case GET_THREADS : System.out.println("Active Threads - "+activeThreadss); 
                     break;
                    case HELP : System.out.println(GET_THREADS + " - use to show active threads");
                                System.out.println(EXIT + " - use to shotdown he server");
                     break;           
                    case SAVE : saveData.start();
                     break;
                    case  CLIAR : try{  
                        new ProcessBuilder("cls").start(); }catch(Exception e){}
                     break;
                    default:System.out.println("Invalid command use 'help' "); 
                     break; 
                }
             }               
         }).start();
         
          
         
         
         
         
	  while(true) {	 //CONNECTION THREDS
             Socket s1;
             if( (s1 = serverSocket.accept())!= null) {
               new Thread(()->{
                  Main.activeThreadss++;
                  Server s = new Server(s1);
               }).start();	       
             }
	  }
	}
        
        
 
}
