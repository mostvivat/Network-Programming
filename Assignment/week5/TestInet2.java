import  java.net.*;

public  class TestInet2{
	public static void main(String[]args) {
		try {
			InetAddress[]ad = InetAddress.getAllByName("www.google.com");
                for(int  i  = 0; i < ad.length;  i++) {
			        System.out.println("IP = "+ ad[i].getHostAddress());
                }
             } catch (Exception e) {
                    e.printStackTrace();
             }
	}
}
