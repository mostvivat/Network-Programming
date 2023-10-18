import java.net.InetAddress;  

public class JavaIP{  
    public static void main(String[] args) {  
        if(args.length !=1){  // Check if the user provided exactly one argument.
             System.out.println("Usage: java JavaIP <hostname/IP");  // Print usage info if not and exit.
             return;  // Exit the program if the number of arguments is not equal to one.
        }        
        String Dname = args[0];  // Store the first (and only) argument provided by the user in Dname.
        try {
            InetAddress address = InetAddress.getByName(Dname);  // Get the InetAddress object associated with the given hostname/IP.
            String hostname = address.getHostName();  // Get the hostname from the InetAddress object.
            String IPAddress = address.getHostAddress();  // Get the IP address from the InetAddress object.
            System.out.println(("Hostname:"+hostname));  // Print the obtained hostname.
            System.out.println("IP Address:"+IPAddress);  // Print the obtained IP address.
        } catch (Exception e) {  // Catch any exception that might occur (like UnknownHostException).
            System.out.println("Error: unknownhost or IP address");  // Print an error message if an exception occurs.
        }
        
    }
}
