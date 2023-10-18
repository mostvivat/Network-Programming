import java.io.*;


public class Example2 {
    public static void main(String[] args) {
        File f = new File ("myFile");
        if (!f.exists()) {
            System.out.println("File does not exist");
            System.exit(1);
        } if (f.isFile()) {
            System.out.println("myFile.txt is a File");
            System.out.println("File size = "+f.length());
            
        } else if(f.isDirectory()){
            System.out.println("myFile.txt is a directory");
            
        } else{
           System.out.println("....."); 
        }
    }
}