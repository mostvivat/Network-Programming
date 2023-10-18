package week2;
import java.io.*;

public class JavaBinaryCopy {
    public static void main(String[] args)   {
        try {
            if (args.length == 2) {
                String sourceFile = args[0];
                String destinationFile = args[1];
                File file = new File(sourceFile);
                if (file.exists() && file.isFile()) {
                    FileInputStream fin = new FileInputStream(sourceFile);
                    FileOutputStream fout = new FileOutputStream(destinationFile);
                    int Bytes;
                        while ((Bytes = fin.read()) != -1) {
                            fout.write(Bytes);
                        }   
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
