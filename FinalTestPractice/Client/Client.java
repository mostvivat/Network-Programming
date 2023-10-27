package FinalTestPractice.Client;

import java.io.*;
import java.net.*;

class Client {
    public static void main(String[] args) {
        try {
            //ถ้าพารามิเตอร์ตัวแรกเป็น "add" ให้ทำงานตามเงื่อนไข
            if (args[0].equals("add")) {
                //ถ้าพารามิเตอร์ไม่เท่ากับ 3 ให้แสดงข้อความว่า "number of parameters is incorrect"
                if (args.length != 3) {
                    System.out.println("number of parameters is incorrect");
                    return;
                }
                // รับชื่อของนักเรียนเป็น String จาก args[1]
                String name = args[1];
                int score;
                // ใช้ try catch เพื่อตรวจสอบว่าค่า score ที่รับเข้ามาเป็นตัวเลขหรือไม่
                try {
                    //แปลงค่า score จาก String เป็น int โดยใช้ parseInt และเก็บค่าไว้ในตัวแปร score 
                    score = Integer.parseInt(args[2]);
                } catch (Exception e) {
                    //ถ้าไม่สามารถแปลงเป็นตัวเลขได้ ให้แสดงข้อความว่า "score must be a number"
                    System.out.println("score must be a number");
                    return;
                }
                // สร้าง Socket ที่ใช้พอร์ต 56789
                Socket socket = new Socket("127.0.0.1", 56789);
                // สร้าง BufferedReader และ PrintWriter สำหรับส่งข้อมูลไปยังเซิร์ฟเวอร์
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter pw = new PrintWriter(socket.getOutputStream());
                // ส่งคำสั่ง "add" ไปยังbufferของprintwriter
                pw.println("add");
                // ส่งชื่อนักเรียนไปยังbufferของprintwriter
                pw.println(name);
                // ส่งคะแนนนักเรียนไปยังbufferของprintwriter
                pw.println(score);
                // ส่งข้อมูลไปยังเซิร์ฟเวอร์ ( "add", name, และ score ถูกแปลงเป็น bytes และส่งไปยังเซิร์ฟเวอร์ผ่าน Socket connection)
                pw.flush();
                // อ่านข้อมูลจาก InputStream และเก็บไว้ในตัวแปร readLine
                String readLine = br.readLine();
                // ถ้า readLine เป็น "OK" ให้แสดงข้อความว่า "OK" 
                if (readLine.equals("OK")) {
                    System.out.println("OK");
                }
                // ถ้าข้อมูลที่ได้รับไม่ใช่ "OK" โปรแกรมจะแสดงข้อมูลที่ได้รับ (readLine) บนหน้าจอ
                else {
                    System.out.println(readLine);
                }
                // ปิด Socket
                socket.close();
                // ปิด BufferedReader และ PrintWriter
                br.close();
                pw.close();
            } 
            //ถ้าพารามิเตอร์ตัวแรกเป็น "grade" ให้ทำงานตามเงื่อนไข
            else if (args[0].equals("grade")) {
                if (args.length != 2) {
                    System.out.println("number of parameters is incorrect");
                    return;
                }
                String name = args[1];
                Socket socket = new Socket("127.0.0.1", 56789);
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter pw = new PrintWriter(socket.getOutputStream());
                pw.println("grade");
                pw.println(name);
                pw.flush();
                String readLine = br.readLine();
                System.out.println(readLine);
                socket.close();
                br.close();
                pw.close();
            } 
            // ถ้าพารามิเตอร์ตัวแรกไม่ใช่ "add" หรือ "grade" ให้แสดงข้อความว่า "incorect command"
            else {
                System.out.println("incorect command");
            }
        } catch (Exception e) {
            System.out.println("number of parameters is incorrect");
        }
    }
}