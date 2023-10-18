package week4;
public class Fork {
    volatile boolean taken = false; // เก็บสถานะว่าส้อมนี้ถูกใช้งานอยู่หรือไม่
    String holderName = " "; // เก็บชื่อ Philosopher ที่ใช้งานส้อมนี้อยู่

    public String getHolderName() {
        return holderName;
    }

    public synchronized boolean take(String holderName) {
        if (taken) {
            return false;
        } else {
            taken = true;
            this.holderName = holderName;
            return true;
        }
    }

    public synchronized void putDown() {
        taken = false;
        this.holderName = " ";
    }
}
