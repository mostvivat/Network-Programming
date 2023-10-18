import java.io.*;
import java.net.*;

public class FileClient {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", 6789);

            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            PrintWriter pw = new PrintWriter(out);
            
            String msg = "";
            for (int i = 0; i < args.length; i++) {
                msg = msg + args[i] + (i != args.length - 1 ? " " : "");
            } 
            pw.println(msg);
            pw.flush(); 

            String command = args[0];
            if(command.equals("upload")) {
                String response = br.readLine();
                if(args.length != 2){
                    System.out.println("argument error");
                    return;
                }
                try{
                    String filename = args[1];
                    File f = new File(filename);
                    if(response.equals("OK")) {
                        FileInputStream fin = new FileInputStream(f);
                        byte[] buffer = new byte[65536];
                        int size;
                        while((size = fin.read(buffer)) > 0) {
                            out.write(buffer, 0, size);
                        }
                        out.flush();
                        fin.close();
                    } else if(response.equals("NOK")) {
                        System.out.println(filename + " not found");
                    } else {
                        System.out.println("error");
                    }
                }catch(Exception e){}

            }else if(command.equals("download")){
                String response = br.readLine();
                if(args.length != 2){
                    System.out.println("argument error");
                    return;
                }
                try{   
                    String filename = args[1];
                    File f = new File(filename);
                    if(response.equals("OK")) {
                        FileOutputStream fout = new FileOutputStream(f);
                        byte[] b = new byte[65536];
                        int size;
                        while((size = in.read(b)) > 0) {
                            fout.write(b, 0, size);
                        }
                    }else if(response.equals("NOK")){
                        System.out.println(filename + " not found");
                    }else{
                        System.out.println("error");
                    }
                }catch(Exception e){}

            }else if(command.equals("list")){
                if(args.length != 1){
                    System.out.println("argument error");
                    return;
                }
                String filename;
                while((filename = br.readLine()) != null) {
                    System.out.println(filename);
                }
            }else{
                System.out.println("command not found");
            }
            pw.flush();
            pw.close();
            br.close();
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
