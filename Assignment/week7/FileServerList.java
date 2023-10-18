package Assignment;

import java.io.*;
import java.net.*;

public class FileServerList implements Runnable {
    Socket s = null;

    public FileServerList(Socket s){
        this.s = s;
    }
    
    public void run(){
        try {
            InputStream in = s.getInputStream();
            OutputStream out = s.getOutputStream();

            PrintWriter pw = new PrintWriter(out);

            File f = new File("./");
            String[] filename = f.list();
            for(int i =0; i< filename.length; i++){
                pw.println(filename[i]);
            }
            pw.flush();
            in.close();
            out.close();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        try {
            ServerSocket serv = new ServerSocket(5678);
            while(true){
                Socket s = serv.accept();
                FileServerList fs = new FileServerList(s);
                Thread t = new Thread(fs);
                t.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
