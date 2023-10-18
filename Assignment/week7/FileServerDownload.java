// package Assignment;

import java.io.*;
import java.net.*;
public class FileServerDownload implements Runnable{
    Socket s = null;
    public FileServerDownload(Socket s){
        this.s = s; 
    }
    public void run(){
        try {
            InputStream in 
            =s.getInputStream();
            OutputStream out = s.getOutputStream();

            BufferedReader br =new BufferedReader(new InputStreamReader(in));
            PrintWriter pw = new PrintWriter(out);

            String filename = br.readLine();
            File f = new File(filename);
            if(!f.exists()){
                pw.println("NOK");
                pw.flush();
            }else{
                pw.println("OK");
                pw.flush();
                FileInputStream fin =new FileInputStream(f);
                byte[] bufffer = new byte[65536];
                int size;
                while((size = fin.read(bufffer))>0){
                    out.write(bufffer,0,size);
                }
                out.close();
                s.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        try {
            ServerSocket serv = new ServerSocket(5679);
            while(true){
                Socket s = serv.accept();
                FileServerDownload fs = new FileServerDownload(s);
                Thread t = new Thread(fs);
                t.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
