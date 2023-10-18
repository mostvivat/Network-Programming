import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Server implements Runnable {
    private Socket s;
    private static final HashMap<String, String> studentDatabase = new HashMap<>();

    public Server(Socket s) {
        this.s = s;
    }

    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

            String command = br.readLine();

            if ("add".equals(command)) {
                String studentId = br.readLine();
                String studentName = br.readLine();
                synchronized (studentDatabase) {
                    studentDatabase.put(studentId, studentName);
                }
                pw.println("OK");
            } else if ("search".equals(command)) {
                String studentId = br.readLine();
                String name;
                synchronized (studentDatabase) {
                    name = studentDatabase.getOrDefault(studentId, "N/A");
                }
                pw.println(name);
            }

            br.close();
            pw.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(15);
        ServerSocket serv = null;

        try {
            serv = new ServerSocket(23410);
            while (true) {
                Socket s = serv.accept();
                Server serverThread = new Server(s);
                es.execute(serverThread);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
