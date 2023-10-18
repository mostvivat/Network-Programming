
import java.util.concurrent.*;
import java.io.*;
import java.net.*;


public class Server3 implements Runnable {
    Socket s =null;
    public Server3 (Socket s){
        this.s=s;
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
            // TODO: handle exception
        }
    }
    public static void main(String[] args) {
        ServerSocket serv = null;
        ExecutorService es = Executors.newFixedThreadPool(10);
        try  {
            serv=new ServerSocket(30000);   
        } catch (Exception e) {
            e.printStackTrace();
        }
        while(true){
            try {
                Socket s = serv.accept();
                Server3 st = new Server3(s);
                es.execute(st);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }
    
}
