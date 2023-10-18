import java.io.*;  
import java.net.*; 

public class PortScan { 
    public static void main(String[] args) { // Main method where execution starts.
        if (args.length != 1) { // Check if the user provided exactly one argument.
            System.out.println("Usage: java PortScan <hostname/IP>"); // Print usage info if not and exit.
            return; // Exit the program if the number of arguments is not equal to one.
        }
        String IPHost = args[0]; // Store the first (and only) argument provided by the user in IPHost.

        // Loop from port 70 to 100 to check if these ports are open on the given hostname/IP.
        for (int i = 70; i <= 100; i++) { 
            try {
                Socket socket = new Socket(IPHost, i); // Try to create a Socket connection on the current port.
                // If the connection is successful, print that the port is open.
                System.out.println(IPHost + " is open on Port " + i); 
            } catch (Exception e) {
                // If an exception occurs (likely a ConnectException), print that the port is not open.
                System.out.println(IPHost + " is not open on Port " + i); 
            }
        }   
    }
}
