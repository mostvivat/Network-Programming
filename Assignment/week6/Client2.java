package Assignment;
import java.io.*;
import java.net.*;

public class Client2 {
    public static void main(String[] args) {
        try{
            Socket s = new Socket("127.0.0.1",20000);
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter pw = new PrintWriter(s.getOutputStream());

            pw.println(args[0]);
            pw.println(args[1]);
            pw.flush();

            String msg = br.readLine();
            System.out.println(msg);

            br.close();
            pw.close();
            s.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
