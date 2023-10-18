package Assignment;
import java.net.*;
import java.io.*;
public class Client1{
    public static void main(String[] args) {
        try{
            Socket s = new Socket("127.0.0.1", 10000);
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter pw = new PrintWriter(s.getOutputStream());

            pw.println("Hello");
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