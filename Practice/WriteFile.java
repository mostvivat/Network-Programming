import java.io.*;

public class WriteFile {
    public static void main(String[] args) {
        try {
            String msg = "Hello World";
            File f= new File("myfile.txt");
            FileOutputStream fout = new FileOutputStream(f);
            byte[] b = msg.getBytes();
            fout.write(b);
            fout.close();
        } catch (Exception e) {e.printStackTrace();
            // TODO: handle exception
        }
    }
}
