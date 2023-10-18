package week2;
import java.io.*;

public class JavaList {
    public static void main(String[] args) {
        if(args.length!=1){
            System.out.println("Usage : JavaList <File/Directory Name>");
            return;
        }
        String pathname = args[0];
        File f = new File (pathname);
        if(f.isDirectory()){
            File list[] = f.listFiles();
                int x = 0;
                while (x<list.length){
                    if (list[x].isFile()){
                        System.out.println(list[x].getName()+" "+"is a File "+" "+list[x].length() + "Bytes");
                        
                    } else if (list[x].isDirectory()){
                        System.out.println(list[x].getName()+ " "+"is a Folder");
                        if(list[x].isFile()){
                           System.out.println(list[x].getName()+" "+"is a File "+" "+list[x].length() + "Bytes");
                        }
                    }
                    x++;        
                }
        }
        else if (f.isFile()){
            System.out.println(f.getName()+" "+"is a File Size"+" "+f.length() +" "+ "Bytes");
        } //end
        
        else {
            System.out.println("File not found");
        }              
    }
}
