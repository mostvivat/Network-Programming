package week8;

import java.net.*;

public class DateTimeClient {
    public static void main(String[] args) {
        try {
            byte[] sendBuffer; // ประกาศตัวแปร sendBuffer สำหรับเก็บข้อมูลที่จะส่งไปที่เซิร์ฟเวอร์
            byte[] recvBuffer = new byte[512]; 
            // ประกาศตัวแปร recvBuffer ขนาด 512 bytes สำหรับเก็บข้อมูลที่ได้รับจากเซิร์ฟเวอร์

            // รับ IP address ของเซิร์ฟเวอร์ (localhost) และสร้าง DatagramSocket
            InetAddress ia = InetAddress.getByName("127.0.0.1");
            DatagramSocket socket = new DatagramSocket();

            // สร้างข้อความที่จะส่งไปยังเซิร์ฟเวอร์และแปลงเป็น byte array
            String msg = "Hello";
            sendBuffer = msg.toString().getBytes();

            // สร้าง DatagramPacket สำหรับส่งข้อมูลไปยังเซิร์ฟเวอร์
            DatagramPacket sendDP = new DatagramPacket(sendBuffer, sendBuffer.length, ia, 9876);
            socket.send(sendDP); // ส่งข้อมูลไปยังเซิร์ฟเวอร์

            // สร้าง DatagramPacket สำหรับรับข้อมูลจากเซิร์ฟเวอร์
            DatagramPacket recvDP = new DatagramPacket(recvBuffer, recvBuffer.length);
            socket.receive(recvDP); // รับข้อมูลจากเซิร์ฟเวอร์

            // แปลงข้อมูลที่ได้รับเป็น string และพิมพ์ผลลัพธ์ (เวลาจากเซิร์ฟเวอร์)
            String s = new String(recvDP.getData(), 0, recvDP.getLength());
            System.out.println("ServerTime --> " + s);

            socket.close(); // ปิด DatagramSocket
        } catch (Exception e) {
            e.printStackTrace(); // พิมพ์ stack trace ถ้าเกิดข้อผิดพลาด
        }
    }
}
