public class Grade {
    
    public static void main(String[] args) {
        try {
            //รับค่าGradeเป็นInt
            int score = Integer.parseInt(args[0]);
            //ดักให้ตัวเลขอยู่ช่วง0-100
            if (score > 100 || score < 0) {
                System.out.println(" Please enter number 0-100");
            } else {
                System.out.println("Score: " + score);
                if (score >= 80) {
                    System.out.println("A");
                } else if (score >= 70) {
                    System.out.println("B");
                } else if (score >= 60) {
                    System.out.println("C");
                } else if (score >= 50){
                    System.out.println("D");
                } else {
                    System.out.println("F");
                }
            }
            //ดักไม่ใส่คะแนน
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter your score"); 
            //ดักตัวเลขที่ไม่ใช้Int
        } catch (NumberFormatException e) {
            System.out.println("Please enter integer number");
            //catch length of parameter must be only 1
        } 
        
    }
}
