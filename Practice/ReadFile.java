import java.io.*;


public class ReadFile {
    public static void main(String[] args) {
        try {
            int n;
            byte[] b =new byte[16];

            File f =new File("myfile.txt");
            FileInputStream fin =new FileInputStream(f);
            while((n=fin.read(b))>0){
                String data = new String(b,0,n);
                System.out.print(data);
            }
            fin.close();
        } catch (Exception e) {e.printStackTrace();
            // TODO: handle exception
        }
    }
}
