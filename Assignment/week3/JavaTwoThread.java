package week3;
public class JavaTwoThread implements Runnable {
    int where;
    int from;
    int result;

    public JavaTwoThread(int from, int where) {
        this.from = from;
        this.where = where;
        this.result = 0;
    }

    public void run() {
        // บันทึกเวลาเริ่มต้น
        long startTime = System.currentTimeMillis();

        for (int i = from; i <= where; i++) {
            result += i;
        }
        try {
            // หลับรอตามเวลาที่กำหนด
            if (from == 1) {
                Thread.sleep(5000); // หลับ 5 วินาที สำหรับ Thread 1
            } else {
                Thread.sleep(10000); // หลับ 10 วินาที สำหรับ Thread 2
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // บันทึกเวลาสิ้นสุด
        long endTime = System.currentTimeMillis();

        // คำนวณหาเวลาที่ใช้ในการทำงานของ Thread
        long executionTime = endTime - startTime;
        System.out.println("Thread " + from + "-" + where + "  : " + executionTime + " milliseconds");
    }

    public int getResult() {
        return result;
    }

    public static void main(String[] args) {
        int s = 0;
        JavaTwoThread s1 = new JavaTwoThread(1, 5000);
        JavaTwoThread s2 = new JavaTwoThread(5001, 10000);

        Thread t1 = new Thread(s1);
        Thread t2 = new Thread(s2);
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
            s = s1.getResult() + s2.getResult();
            System.out.println("Result = " + s);
        } catch (Exception e) {

        }

    }
}
