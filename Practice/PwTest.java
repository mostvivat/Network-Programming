import java.io.*;

public class PwTest {
    public static void main(String[] args) {
        try {
            String msg = "Hello d World";
            File f = new File("myFile.txt");
            FileOutputStream fout = new FileOutputStream(f);
            PrintWriter pout = new PrintWriter(fout);
            pout.print(msg);
            pout.flush();
            fout.close();
        } catch (Exception e) {e.printStackTrace();
            // TODO: handle exception
        }
    }
}
