import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class FileServer implements Runnable {
    Socket client;
    public FileServer(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            InputStream in = client.getInputStream();
            OutputStream out = client.getOutputStream();
            
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            PrintWriter pw = new PrintWriter(out);
            
            String revmsg = br.readLine();
            String[] msg = revmsg.split(" ");
            String command = msg[0];
            if (command.equals("upload")) {
                // upload code
                String fileName = msg[1];
                File f = new File(fileName);
                if(f.exists()) {
                    pw.println("NOK");
                } else {
                    pw.println("OK");
                    FileOutputStream fout = new FileOutputStream(f);
                    byte[] b = new byte[65536];
                    int size;
                    while((size = in.read(b)) > 0) {
                        fout.write(b, 0, size);
                    }
                    fout.flush();
                    fout.close();
                }
                System.out.println("upload successful");
            } else if (command.equals("download")) {
                // download code
                String fileName = msg[1];
                File f = new File(fileName);
                if(!f.exists()) {
                    pw.println("NOK");
                } else {
                    pw.println("OK");
                    FileInputStream fin = new FileInputStream(f);
                    byte[] buffer = new byte[65536];
                    int size;
                    while((size = fin.read(buffer)) > 0) {
                        out.write(buffer, 0, size);
                    }
                    out.flush();
                }
                System.out.println("download successful");
            } else if (command.equals("list")) {
                // list code
                File f = new File("./");
                String[] filename = f.list();
                for(int i=0; i<filename.length; i++){
                    pw.println(filename[i]);
                    System.out.println(filename[i]);
                }
                pw.flush();
                System.out.println("list successful");
            } else {
                System.out.println("Command not found");
                pw.println("Command not found");
            }
            pw.flush();
            pw.close();
            br.close();
            in.close();
            out.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ServerSocket serv = null;

        try {
            serv = new ServerSocket(6789);
            System.out.println("Server started on port " + "6789");
            while (true) {
                Socket client = serv.accept();
                executorService.execute(new FileServer(client));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
