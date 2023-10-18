package week2;
import java.io.*;

public class JavaTextCopy {
    public static void main(String[] args) {
        try {
            if (args.length == 2) {
                String sourceFile = args[0];
                String destinationFile = args[1];
                File file = new File(sourceFile);
                if (file.exists() && file.isFile()) {
                    String msg ;
                    FileInputStream fin = new FileInputStream(sourceFile);
                    InputStreamReader ir = new InputStreamReader(fin);
                    BufferedReader br = new BufferedReader(ir);
                    FileOutputStream fout = new FileOutputStream(destinationFile);
                    PrintWriter pout = new PrintWriter(fout);
                    while ((msg = br.readLine()) != null) {
                        pout.println(msg);
                    }
                    pout.flush();
                    fin.close();
                    fout.close();
                }else {
                System.out.println("Usage: JavaBinaryCopy <source file> <destination file>");
                }   
            } else {
                System.out.println("Usage: JavaBinaryCopy <source file> <destination file>");
            }
        } catch (Exception e){ 
            e.printStackTrace(); 
        }
    }
}
