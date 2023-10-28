package FinalTestPractice;
import java.net.*;

public class UDPServer {
    public static void main(String[] args) {
        try {
            // สร้าง DatagramSocket ที่ใช้พอร์ต 6680
            DatagramSocket socket = new DatagramSocket(6680);

            while (true) {
                // กำหนดขนาดของ buffer สำหรับรับข้อมูล
                byte[] recvBuffer = new byte[512];
                // สร้าง DatagramPacket สำหรับรับข้อมูล (buffer, buffer.length)
                DatagramPacket receivePacket = new DatagramPacket(recvBuffer, recvBuffer.length);

                // รับข้อมูลจาก DatagramSocket
                socket.receive(receivePacket);

                // ดึงข้อมูลที่ได้รับเป็น String
                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());

                // ตรวจสอบว่ามีคำหยาบแฝงอยู่ในข้อความหรือไม่
                if (receivedMessage.contains("bad")) {
                    // แทนคำหยาบด้วย '*'
                    receivedMessage = receivedMessage.replaceAll("bad", "***");
                } else {
                    // ถ้าไม่มีคำหยาบแฝงอยู่ในข้อความให้ตอบว่า "OK"
                    receivedMessage = "OK";
                }
                // แปลงข้อความที่จะส่งกลับเป็น byte array
                byte[] sendBuffer = receivedMessage.getBytes();

                // สร้าง DatagramPacket สำหรับส่งข้อมูลกลับไปยัง client
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, receivePacket.getAddress(), receivePacket.getPort());

                // ส่งข้อมูลกลับไปยัง client
                socket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
