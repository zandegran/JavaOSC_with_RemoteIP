import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

import com.illposed.osc.*;


class OSCrelay{
       
       public static void main(String args[]) 
         {
    	   try {
               //send ("/lights".trim());
       } catch (Exception e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
       }
       OSCPortIn receiver=null;
try {
       receiver = new OSCPortIn(5000);
       //receiver = new OSCPortIn(OSCPort.defaultSCOSCPort());
} catch (SocketException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
}
       OSCListener listener = new OSCListener() {
               public void acceptMessage(java.util.Date time, OSCMessage message) {
                       String received=new String(message.getByteArray());
                       String Split[]=received.split("/");
                       String tosend=Split[Split.length-1];
                       System.out.println(""+message.getRemoteIp());
                       
                     
               }
       };
       receiver.addListener( "/lights",listener);
       receiver.addListener( "/station2",listener);
       receiver.startListening();
       
       
         }
       
       public static void send(String sentence) throws Exception
        {
         
         String modifiedSentence;
         BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
         Socket clientSocket = new Socket("localhost",5000);
         DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
         BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
         System.out.println("Binded\n");
                                                                                                                                                                          
         outToServer.writeBytes(sentence+"\n");
         //outToServer.writeBytes("\n");
         modifiedSentence = inFromServer.readLine();
        // modifiedSentence = inFromServer.readLine();
         System.out.println("FROM SERVER: " + modifiedSentence+" End");
         
         clientSocket.close();
         //while (true) {}
        }
       
       //TODO: hjnhj
}