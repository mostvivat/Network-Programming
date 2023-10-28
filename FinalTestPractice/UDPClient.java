package FinalTestPractice;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Error");
            return;
        }
        try {
            // สร้าง DatagramSocket
            DatagramSocket socket = new DatagramSocket();
            // กำหนดที่อยู่และพอร์ตของเซิร์ฟเวอร์
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 6680;
            // สร้างข้อมูลสำหรับส่งไปยังเซิร์ฟเวอร์
            String message = args[0];
            byte[] sendBuffer = message.getBytes();
            // สร้าง DatagramPacket สำหรับส่งข้อมูลไปยังเซิร์ฟเวอร์
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, serverPort);
            // ส่งข้อมูลไปยังเซิร์ฟเวอร์
            socket.send(sendPacket);
            // รับข้อมูลจากเซิร์ฟเวอร์
            byte[] recvBuffer = new byte[512];
            DatagramPacket receivePacket = new DatagramPacket(recvBuffer, recvBuffer.length);
            socket.receive(receivePacket);
            // แปลงข้อมูลที่ได้รับเป็น String และแสดงผล
            String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Server response: " + receivedMessage);
            // ปิด DatagramSocket
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
