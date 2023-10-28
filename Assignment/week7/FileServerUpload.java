package week7;

import java.io.*;
import java.net.*;

public class FileServerUpload implements Runnable {
    Socket s = null;

    public FileServerUpload(Socket s){
        this.s = s;
    }
        @Override
        public void run(){
            try {
                //  อ่านข้อมูลจาก Client
                InputStream in = s.getInputStream();
                //  ส่งข้อมูลไปยัง Client
                OutputStream out = s.getOutputStream();
                //  อ่านข้อมูลจาก Client  แบบที่สามารถอ่านได้ทีละบรรทัด โดยใช้  
                //  BufferedReader อ่านจาก InputStreamReader ที่รับข้อมูลจาก InputStream ของ Socket ที่เชื่อมต่อกับ Client แล้วเก็บไว้ใน BufferedReader ที่ชื่อว่า br 
                BufferedReader br = new BufferedReader(new InputStreamReader(in));

                // ส่งข้อมูลไปยัง Client  แบบที่สามารถเขียนได้ทีละบรรทัด โดยใช้ 
                //PrintWriter อ่านจาก OutputStreamWriter ที่รับข้อมูลจาก OutputStream ของ Socket ที่เชื่อมต่อกับ Client แล้วเก็บไว้ใน PrintWriter ที่ชื่อว่า pw
                PrintWriter pw = new PrintWriter(out);
                // อ่านข้อมูลจาก BufferedReader ที่ชื่อว่า br แล้วเก็บไว้ในตัวแปรชื่อ filename  ซึ่งเป็นชื่อไฟล์ที่ Client ต้องการส่งมา  
                //โดยใช้เมธอด readLine() ของ BufferedReader  ซึ่งจะอ่านข้อมูลทีละบรรทัด และเมื่ออ่านเสร็จแล้วจะคืนค่ากลับมาเป็น String ที่เก็บไว้ในตัวแปรชื่อ filename 
                String filename = br.readLine();
                //  สร้างไฟล์ใหม่ โดยใช้ชื่อไฟล์ที่อ่านมาจาก Client  และเก็บไว้ในตัวแปรชื่อ f
                File f = new File(filename);
                // ตรวจสอบว่ามีไฟล์ชื่อนี้อยู่หรือไม่  ถ้ามีให้ส่งข้อความกลับไปยัง Client ว่า NOK  ถ้าไม่มีให้ส่งข้อความกลับไปยัง Client ว่า OK และเริ่มรับข้อมูลจาก Client แล้วเขียนลงในไฟล์ที่สร้างขึ้น
                if(f.exists()) {
                    pw.println("NOK");
                } else {
                    pw.println("OK");
                    pw.flush();
                    //  สร้างไฟล์ใหม่ โดยใช้ชื่อไฟล์ที่อ่านมาจาก Client  และเก็บไว้ในตัวแปรชื่อ f และเริ่มรับข้อมูลจาก Client แล้วเขียนลงในไฟล์ที่สร้างขึ้น  
                    // โดยใช้ FileOutputStream อ่านจาก InputStream ของ Socket ที่เชื่อมต่อกับ Client แล้วเก็บไว้ใน FileOutputStream ที่ชื่อว่า fout
                    FileOutputStream fout = new FileOutputStream(f);
                    // 
                    /
                    byte[] b = new byte[65536];
                    int size;
                    // และเมื่อ size มีค่ามากกว่า 0 แสดงว่ามีข้อมูลที่อ่านได้ จึงเขียนข้อมูลที่อ่านได้ลงใน FileOutputStream ที่ชื่อว่า fout 
                    // โดยใช้เมธอด write() ของ FileOutputStream ซึ่งจะเขียนข้อมูลทีละ byte 
                    //  และเมื่อเขียนเสร็จแล้วให้เรียกใช้เมธอด flush() เพื่อเขียนข้อมูลลงในไฟล์จริง และปิด FileOutputStream ที่ชื่อว่า fout ด้วยเมธอด close()
                    while((size = in.read(b)) > 0 ) {
                        fout.write(b,0,size);
                    }
                    fout.flush();
                    fout.close();  
                }
                // ปิดการเชื่อมต่อกับ Client ด้วยเมธอด close()
                in.close();
                out.close();
                s.close();

            } catch(Exception e){
                e.printStackTrace();
            }
            
    }
    public static void main(String[] args) {
        // สร้าง ServerSocket ที่รอการเชื่อมต่อที่พอร์ต 5678 
        //และเมื่อมีการเชื่อมต่อเข้ามาใหม่ ให้สร้าง Thread ใหม่เพื่อรอรับข้อมูลจาก Client และเริ่มการทำงานของ Thread นั้นๆ  
        //โดยใช้คลาส FileServerUpload ที่สร้างขึ้นมาใหม่ และเมื่อ Thread นั้นๆ ทำงานเสร็จแล้วจะสิ้นสุดการทำงานของ Thread นั้นๆ อัตโนมัติ  
        //และเมื่อมีการเชื่อมต่อเข้ามาใหม่อีก Thread ใหม่จะถูกสร้างขึ้นมาอีก และเริ่มการทำงานของ Thread นั้นๆ อีก
        try {
            ServerSocket serv = new ServerSocket(5678);
            while(true){
                Socket s = serv.accept();
                FileServerUpload fs = new FileServerUpload(s);
                Thread t = new Thread(fs);
                t.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
        

