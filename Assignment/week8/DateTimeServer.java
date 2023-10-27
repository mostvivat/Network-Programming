package week8;
import java.net.*;
import java.util.*;

public class DateTimeServer {
    public static void main(String[] args) {
        try {
            // Create a socket on the specified port
            DatagramSocket socket = new DatagramSocket (9876);
            
            while(true) {
                // Receive a packet
                byte[] recvBuffer = new byte[8000];
                DatagramPacket packet = new DatagramPacket(recvBuffer,recvBuffer.length);
                socket.receive(packet);
                
                // Get the current time
                Date now = new Date();
                String msg = now.toString();
                
                // Send the time back to the client
                byte[] sendBuffer = msg.getBytes();
                DatagramPacket packet2 = new DatagramPacket(sendBuffer,
                                                            sendBuffer.length,
                                                            packet.getAddress(),
                                                            packet.getPort());
                socket.send(packet2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
