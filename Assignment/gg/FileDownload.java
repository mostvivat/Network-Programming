
import java.io.*;
import java.net.*;


public class FileDownload {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("127.0.0.1",5678);
            InputStream in = s.getInputStream();
            OutputStream out = s.getOutputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            PrintWriter pw  = new PrintWriter(out);

            pw.println(args[0]);
            pw.flush();
            String response = br.readLine();
            if(response.equals("OK")){
                File f = new File(args[0]);
                FileOutputStream fout = new FileOutputStream(f);
                byte[] b = new byte[65536];
                int size;
                while((size = in.read(b))>0){
                    fout.write(b,0,size);
                }
                in.close(); 
            }
            else{
                System.out.println("No file found in server");
            }
            s.close();  
        } catch (Exception e) {
         e.printStackTrace();
        }}

}
