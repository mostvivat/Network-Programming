package week3;
// import java.io.*;

public class JavaThread extends Thread {
    int threadnumber;

    public JavaThread(int threadnumber){
        this.threadnumber=threadnumber;
    }
    public void run(){
            System.out.println(threadnumber+" "+"Hello World");
    }
    public static void main(String[] args) throws InterruptedException {
        if (args.length == 0){
            return;
        }
        int thread = Integer.parseInt(args[0]);
        for (int i = 0; i < thread; i++) {
            JavaThread tt = new JavaThread(i);
            tt.start();
            tt.join();
        }
        
    }
}

