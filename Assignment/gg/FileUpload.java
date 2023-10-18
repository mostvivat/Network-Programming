
import java.io.*;
import java.net.*;

public class FileUpload {
    public static void main(String[] args) {
        try {
            File f = new File(args[0]);
            if(!f.exists()){
                System.out.println("File upload not found");
            }
            Socket s = new Socket("127.0.0.1",5678);
            InputStream in = s.getInputStream();
            OutputStream out = s.getOutputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            PrintWriter pw = new PrintWriter(out);
            
            pw.println(args[0]);
            pw.flush();
            String response = br.readLine();
            if(response.equals("OK")){
                FileInputStream fin = new FileInputStream(f);
                byte[] buffer = new byte[65536];
                int size;
                while((size = fin.read(buffer)) > 0 ){
                    out.write(buffer,0,size);
                }
                out.flush();
                fin.close();
            } else {
                System.out.println("File already exists on server");
            }
            in.close();
            out.close();
            s.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
