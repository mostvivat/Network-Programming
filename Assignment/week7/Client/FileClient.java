import java.io.*;
import java.net.*;

public class FileClient {
    public static void main(String[] args) {
        Socket socket = null;
        
        try {
            // สร้าง Socket โดยใช้ค่าพอร์ต 6789 และ IP Address     
            socket = new Socket("127.0.0.1", 6789);
            //  สร้าง InputStream และ OutputStream โดยใช้เมธอด getInputStream() และ getOutputStream() 
            //ของ Socket ที่เชื่อมต่อกับ Server แล้วเก็บไว้ในตัวแปรชื่อ in และ out  ตามลำดับ  
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            // สร้าง BufferedReader และ PrintWriter โดยใช้ InputStreamReader และ OutputStreamWriter ที่รับข้อมูลจาก 
            //InputStream และ OutputStream ของ Socket ที่เชื่อมต่อกับ Server แล้วเก็บไว้ในตัวแปรชื่อ br และ pw ตามลำดับ
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            PrintWriter pw = new PrintWriter(out);
            // อ่านข้อมูลจากพารามิเตอร์ args ที่มี index เป็น 0 และเก็บไว้ในตัวแปรชื่อ msg  
            //โดยใช้ลูป for ในการวนรอบเพื่อนำข้อมูลใน args มาต่อกันเป็นข้อความ และเก็บไว้ในตัวแปรชื่อ msg  ซึ่งเป็น String   
            String msg = "";
            for (int i = 0; i < args.length; i++) {
                msg = msg + args[i] + (i != args.length - 1 ? " " : "");
            } 
            // ส่งข้อมูลไปยัง Server โดยใช้เมธอด println() ของ PrintWriter โดยข้อมูลที่จะส่งไปคือข้อความที่รับมาจากพารามิเตอร์ args ที่มี index เป็น 0
            // และเมื่อส่งข้อมูลเสร็จแล้วให้เรียกใช้เมธอด flush() เพื่อเขียนข้อมูลที่อยู่ใน PrintWriter ลงใน OutputStream 
            pw.println(msg);
            pw.flush(); 
   
            String command = args[0];
            //ตรวจสอบว่าคำสั่งที่ได้รับจาก client เป็น "upload" หรือไม่ 
            if(command.equals("upload")) {
                //ถ้าใช่ให้อ่านข้อมูลจาก BufferedReader ที่ชื่อว่า br แล้วเก็บไว้ในตัวแปรชื่อ response 
                String response = br.readLine();
                //ตรวจสอบว่าพารามิเตอร์ที่รับมามีค่าเป็น 2 หรือไม่ ถ้าไม่ใช่ให้แสดงข้อความว่า argument error และจบการทำงานของโปรแกรม
                if(args.length != 2){
                    System.out.println("argument error");
                    return;
                }
                
                try{
                    // อ่านข้อมูลจากพารามิเตอร์ args ที่มี index เป็น 1 และเก็บไว้ในตัวแปรชื่อ filename
                    String filename = args[1];
                    // สร้างไฟล์ใหม่ โดยใช้ชื่อไฟล์ที่อ่านมาจากพารามิเตอร์ args ที่มี index เป็น 1 และเก็บไว้ในตัวแปรชื่อ f
                    File f = new File(filename);
                    // ถ้าข้อมูลที่อ่านมาจาก BufferedReader ที่ชื่อว่า br เป็น "OK" 
                    if(response.equals("OK")) {
                        // สร้าง FileInputStream โดยใช้ File ที่ชื่อว่า f แล้วเก็บไว้ในตัวแปรชื่อ fin
                        FileInputStream fin = new FileInputStream(f);
                        //  อ่านข้อมูลจาก InputStream ของ Socket ที่เชื่อมต่อกับ Client แล้วเก็บไว้ใน FileOutputStream ที่ชื่อว่า fout
                        // โดยใช้เมธอด read() ของ InputStream  ซึ่งจะอ่านข้อมูลทีละ byte และเมื่ออ่านเสร็จแล้วจะคืนค่ากลับมาเป็น int ที่เก็บไว้ในตัวแปรชื่อ size
                        byte[] buffer = new byte[65536];
                        int size;
                        // และเมื่อ size มีค่ามากกว่า 0 แสดงว่ามีข้อมูลที่อ่านได้ จึงเขียนข้อมูลที่อ่านได้ลงใน FileOutputStream ที่ชื่อว่า fout 
                        // โดยใช้เมธอด write() ของ FileOutputStream ซึ่งจะเขียนข้อมูลทีละ byte 
                        //  และเมื่อเขียนเสร็จแล้วให้เรียกใช้เมธอด flush() เพื่อเขียนข้อมูลลงในไฟล์จริง และปิด FileOutputStream ที่ชื่อว่า fout ด้วยเมธอด close()
                        while((size = fin.read(buffer)) > 0) {
                            out.write(buffer, 0, size);
                        }
                        out.flush();
                        fin.close();
                    // ถ้าข้อมูลที่อ่านมาจาก BufferedReader ที่ชื่อว่า br เป็น "NOK" ให้แสดงข้อความว่า ชื่อไฟล์นี้มีอยู่แล้ว และจบการทำงานของโปรแกรม
                    } else if(response.equals("NOK")) {
                        System.out.println(filename + " not found");
                    } else {
                        System.out.println("error");
                    }
                }catch(Exception e){}
            // ตรวจสอบว่าคำสั่งที่ได้รับจาก client เป็น "download" หรือไม่ 
            }else if(command.equals("download")){
                // ถ้าใช่ให้อ่านข้อมูลจาก BufferedReader ที่ชื่อว่า br แล้วเก็บไว้ในตัวแปรชื่อ response
                String response = br.readLine();
                if(args.length != 2){
                    System.out.println("argument error");
                    return;
                }
                
                try{ 
                    // อ่านข้อมูลจากพารามิเตอร์ args ที่มี index เป็น 1 และเก็บไว้ในตัวแปรชื่อ filename  
                    String filename = args[1];
                    File f = new File(filename);
                    // ถ้าข้อมูลที่อ่านมาจาก BufferedReader ที่ชื่อว่า br เป็น "OK" ให้สร้าง FileOutputStream โดยใช้ File ที่ชื่อว่า f แล้วเก็บไว้ในตัวแปรชื่อ fout
                    if(response.equals("OK")) {
                        FileOutputStream fout = new FileOutputStream(f);
                        byte[] b = new byte[65536];
                        int size;
                        while((size = in.read(b)) > 0) {
                            fout.write(b, 0, size);
                        }
                    }else if(response.equals("NOK")){
                        System.out.println(filename + " not found");
                    }else{
                        System.out.println("error");
                    }
                }catch(Exception e){}

            }
            //  ตรวจสอบว่าคำสั่งที่ได้รับจาก client เป็น "list" หรือไม่  ถ้าใช่ให้สร้าง File โดยใช้ชื่อได้จากพารามิเตอร์ args ที่มี index เป็น 1 แล้วเก็บไว้ในตัวแปรชื่อ f
            // และเมื่อสร้างไฟล์เสร็จแล้วให้สร้าง BufferedReader โดยใช้ FileReader ที่ชื่อว่า f แล้วเก็บไว้ในตัวแปรชื่อ br
            // และให้สร้าง PrintWriter โดยใช้ OutputStreamWriter ของ Socket ที่เชื่อมต่อกับ Client แล้วเก็บไว้ในตัวแปรชื่อ pw
            //  และให้สร้าง String ที่ชื่อว่า filename โดยให้เก็บชื่อไฟล์ที่อ่านมาจาก BufferedReader ที่ชื่อว่า br แล้วเก็บไว้ในตัวแปรชื่อ filename
            // และให้แสดงชื่อไฟล์ที่อ่านมาจาก BufferedReader ที่ชื่อว่า br แล้วเก็บไว้ในตัวแปรชื่อ filename
            else if(command.equals("list")){
                if(args.length != 1){
                    System.out.println("argument error");
                    return;
                }
                String filename;
                while((filename = br.readLine()) != null) {
                    System.out.println(filename);
                }
            }else{
                System.out.println("command not found");
            }
            pw.flush();
            pw.close();
            br.close();
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
