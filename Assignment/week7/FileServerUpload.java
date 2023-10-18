package Assignment;

import java.io.*;
import java.net.*;

public class FileServerUpload implements Runnable {
    Socket s = null;

    public FileServerUpload(Socket s){
        this.s = s;
    }
        @Override
        public void run(){
            try {
                InputStream in = s.getInputStream();
                OutputStream out = s.getOutputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                PrintWriter pw = new PrintWriter(out);

                String filename = br.readLine();
                File f = new File(filename);
                if(f.exists()) {
                    pw.println("NOK");
                } else {
                    pw.println("OK");
                    pw.flush();
                    FileOutputStream fout = new FileOutputStream(f);
                    byte[] b = new byte[65536];
                    int size;
                    while((size = in.read(b)) > 0 ) {
                        fout.write(b,0,size);
                    }
                    fout.flush();
                    fout.close();  
                }
                in.close();
                out.close();
                s.close();

            } catch(Exception e){
                e.printStackTrace();
            }
            
    }
    public static void main(String[] args) {
        try {
            ServerSocket serv = new ServerSocket(5678);
            while(true){
                Socket s = serv.accept();
                FileServerUpload fs = new FileServerUpload(s);
                Thread t = new Thread(fs);
                t.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
        

