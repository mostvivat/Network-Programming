package Assignment;
import java.io.*;
import java.net.*;

public class Client3 {
    public static void main(String[] args) {
        try{
            Socket s = new Socket("127.0.0.1",30000);
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter pw = new PrintWriter(s.getOutputStream());

            pw.println(args[0]);
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
