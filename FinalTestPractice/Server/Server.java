package FinalTestPractice.Server;

import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.net.*;

class Server implements Runnable {

    // สร้าง Socket สำหรับรับข้อมูลจาก client 
    Socket s = null; 
    // สร้าง HashMap สำหรับเก็บคะแนนของนักเรียน ให้เป็น static เพื่อให้สามารถเข้าถึงได้จาก method run ได้  และให้เป็น volatile เพื่อให้สามารถเข้าถึงได้จาก thread อื่นๆได้
    private static volatile HashMap<String, Integer> stdScore = new HashMap<String, Integer>(); 
    // สร้าง constructor สำหรับรับ Socket ที่ส่งมาจาก main method
    public Server(Socket s) {
        this.s = s; 
    }
    // สร้าง method run สำหรับรับข้อมูลจาก client และส่งข้อมูลกลับไปยัง client
    @Override
    public void run() {
        // ใช้ try catch เพื่อตรวจสอบว่ามีการเชื่อมต่อเข้ามาหรือไม่
        try {
            // สร้าง Scanner และ PrintStream สำหรับรับข้อมูลจาก client และส่งข้อมูลกลับไปยัง client
            Scanner sc = new Scanner(s.getInputStream());
            PrintStream ps = new PrintStream(s.getOutputStream());
            //อ่านชื่อคำสั่งจาก client และเก็บไว้ในตัวแปร str
            String str = sc.nextLine();
            // ถ้า str เป็น "add" ให้ทำงานตามเงื่อนไข
            if (str.equals("add")) {
                try {
                    // อ่านชื่อของนักเรียนจาก client และเก็บไว้ในตัวแปร name
                    String name = sc.nextLine();
                    // รับคะแนนของนักเรียนเป็น int จาก client(อ่านตัวเลข)
                    int score = sc.nextInt();
                    // เพิ่มชื่อและคะแนนของนักเรียนลงใน HashMap โดยใช้ชื่อเป็น key และคะแนนเป็น value
                    stdScore.put(name, score);
                    // ส่งข้อความ "OK" กลับไปยัง client
                    ps.println("OK");
                } 
                // ถ้าข้อมูลที่รับเข้ามาไม่ใช่ตัวเลข ให้แสดงข้อความว่า "score must be a number"
                catch (Exception e) {
                    ps.println("score must be a number");
                }
            } 
            // ถ้า str เป็น "grade" ให้ทำงานตามเงื่อนไข
            else if (str.equals("grade")) {
                // อ่านชื่อของนักเรียนจาก client และเก็บไว้ในตัวแปร name
                String name = sc.nextLine();
                // ถ้าชื่อนักเรียนอยู่ใน HashMap stdScore ให้ทำงานตามเงื่อนไข
                if (stdScore.containsKey(name)) {
                    // อ่านคะแนนของนักเรียนจาก HashMap และเก็บไว้ในตัวแปร score(อ่านvalue จาก key)
                    int score = stdScore.get(name);
                    // ส่งข้อความกลับไปยัง client โดยแสดงคะแนนของนักเรียน
                    if (score >= 80 && score <= 100) {
                        ps.println("Good : " + score);
                    } else if (score >= 50) {
                        ps.println("Pass : " + score);
                    } else if (score < 50 && score >= 0) {
                        ps.println("Fail : " + score);
                    } else {
                        ps.println("Fabulous : " + score);
                    }
                } 
                // ถ้าชื่อนักเรียนไม่อยู่ใน HashMap stdScore ให้ส่งข้อความกลับไปยัง client ว่า "not found"
                else {
                    ps.println("name not found");
                }
                // ถ้าคำสั่งที่รับเข้ามาไม่ใช่ "add" หรือ "grade" ให้ส่งข้อความกลับไปยัง client ว่า "incorrect command"
            } else {
                ps.println("incorrect command");
            }
            // ส่งข้อมูลกลับไปยัง client โดยใช้ PrintStream และ flush ข้อมูลก่อนส่ง
            ps.flush();
            // ปิดการเชื่อมต่อ client และปิด Scanner และ PrintStream ที่ใช้รับข้อมูลจาก client และส่งข้อมูลกลับไปยัง client
            ps.close();
            sc.close();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        
        try {
            //  สร้าง ServerSocket ที่ใช้พอร์ต 56789
            ServerSocket serv = null;
            int port = 56789; 
            // สร้าง ExecutorService สำหรับจัดการ thread และสร้าง thread pool ที่มีขนาด 20
            ExecutorService es = Executors.newFixedThreadPool(20);
            // ใช
            try {
                // 
                serv = new ServerSocket(port);
                // สร้าง thread ใหม่เมื่อมีการเชื่อมต่อเข้ามา
                while (true) {
                    Socket cs = serv.accept();
                    Server sv = new Server(cs);
                    es.execute(sv);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}