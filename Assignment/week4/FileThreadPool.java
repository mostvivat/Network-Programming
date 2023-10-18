package week4;
import java.io.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;;

    public class FileThreadPool implements Runnable{       
        //สร้างconstructure 
        int n;
        public FileThreadPool(int n){
            this.n=n;
        } 
        //แสดงผลn=n^2
        @Override
        public void run(){
            System.out.println(n+"=>"+n*n);

        } 
        public static void main(String[] args) {
            try {
                //รับค่าจากผู้ใช้ชื่อpath

                String pathname = args[0];
                String msg ;
                BufferedReader br = new BufferedReader(new FileReader(pathname));
                //กำหนดให้เทรดทำงานทีละ3ตัว
                ExecutorService es = Executors.newFixedThreadPool(3);
                //อ่านค่าเป็นบรรทัดจากbr
                while((msg=br.readLine())!=null){
                    //แปลงข้อมูลที่อ่านได้เป็นint
                    int num = Integer.parseInt(msg);
                    //สร้างให้threadpoolทมีthreadตามจำนวนบรรทัดที่อ่านเป็นตัวเลขได้
                    FileThreadPool n = new FileThreadPool(num);
                    //เริ่มการทำงาน

                    es.execute(n);
                }
                es.shutdown();
                while(!es.isTerminated()){ }
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } 
        
        
}
