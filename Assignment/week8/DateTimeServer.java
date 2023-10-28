package week8;
import java.net.*;
import java.util.*;

public class DateTimeServer {
    public static void main(String[] args) {
        try {
            // สร้าง DatagramSocket ที่ทำงานบน port 9876
            DatagramSocket socket = new DatagramSocket(9876);
            
            while(true) { // วน loop ตลอดเวลา
                // รับ packet
                byte[] recvBuffer = new byte[8000];
                DatagramPacket packet = new DatagramPacket(recvBuffer, recvBuffer.length);
                socket.receive(packet); // รับข้อมูลจาก client
                
                // หาว่าตอนนี้เวลาเป็นเท่าไหร่
                Date now = new Date();
                String msg = now.toString();
                
                // ส่งเวลากลับไปที่ client
                byte[] sendBuffer = msg.getBytes();
                DatagramPacket packet2 = new DatagramPacket(sendBuffer,
                                                            sendBuffer.length,
                                                            packet.getAddress(),
                                                            packet.getPort());
                socket.send(packet2); // ส่งข้อมูลกลับไปที่ client
            }
        } catch (Exception e) {
            e.printStackTrace(); // พิมพ์ stack trace ถ้าเกิดข้อผิดพลาด
        }
    }
}
