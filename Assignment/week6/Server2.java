

import java.io.*;
import java.net.*;


public class Server2 {
    public static void main(String[] args) {
        ServerSocket servSocket = null;
        try  {servSocket=new ServerSocket(20000);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        while(true){
            try {
                Socket s = servSocket.accept();

                BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                PrintWriter pw = new PrintWriter(s.getOutputStream());

                String login= br.readLine();
                String password= br.readLine();

                if(login.equals("admin")&& password.equals("admin"))
                    pw.println("OK");
                else
                    pw.println("NO");
                pw.flush();
                pw.close();
                br.close();
                s.close();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }
    
}
