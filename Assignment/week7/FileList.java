package Assignment;

import java.io.*;
import java.net.*;

public class FileList{
    public static void main(String[] args) {
        try {
            Socket s = new Socket("127.0.0.1",5678);
            InputStream in = s.getInputStream();
            OutputStream out = s.getOutputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String filename;
            while((filename = br.readLine()) != null) {
                System.out.println(filename);
            }
            in.close();
            out.close();
            s.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}