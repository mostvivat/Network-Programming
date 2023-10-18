import java.io.*;

public class BrTest {
    public static void main(String[] args) {
        try {
            String msg ;
            File f = new File("myFile.txt");
            FileInputStream fin = new FileInputStream(f);
            InputStreamReader ir = new InputStreamReader(fin);
            BufferedReader br = new BufferedReader(ir);
           
            while((msg=br.readLine())!=null)
                System.out.println(msg);
            fin.close();
        } catch (Exception e) {e.printStackTrace();
            // TODO: handle exception
        }
    }
}
