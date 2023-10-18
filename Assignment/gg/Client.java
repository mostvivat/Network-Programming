import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Insufficient arguments.");
            return;
        }

        String command = args[0];

        try {
            Socket s = new Socket("127.0.0.1", 23410);
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

            if ("add".equals(command)) {
                if (args.length != 3) {
                    System.out.println("Usage: java Client add <StudentId> <StudentName>");
                    return;
                }
                pw.println("add");
                pw.println(args[1]);
                pw.println(args[2]);
            } else if ("search".equals(command)) {
                if (args.length != 2) {
                    System.out.println("Usage: java Client search <StudentId>");
                    return;
                }
                pw.println("search");
                pw.println(args[1]);
            } else {
                System.out.println("Command not found");
                return;
            }

            String msg = br.readLine();
            System.out.println(msg);

            br.close();
            pw.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
