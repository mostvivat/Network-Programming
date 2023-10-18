

import java.io.*;
import java.net.*;


public class Server4 extends Thread {
    Socket s = null;
    public Server4(Socket s){
        this.s =s;
    }
    public void run(){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter pw = new PrintWriter(s.getOutputStream());

            String sleep = br.readLine();
            Long sleepTime = Long.parseLong(sleep);
            try {
                Thread.sleep(sleepTime*1000);
            } catch (Exception se) {}

            pw.println("OK");
            pw.flush();
            pw.close();
            br.close();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        ServerSocket servSocket = null;
        try {
            servSocket = new ServerSocket(30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        while(true){
            try {
                Socket s = servSocket.accept();
                Server4 server = new Server4(s);
                server.start();
            } catch (Exception e) {
            }
        }
    }
    
}
