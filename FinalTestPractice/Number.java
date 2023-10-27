package FinalTestPractice;
import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Number {
    public static void main(String args[]) {
    //basicNumber
        //valueOf() แปลงค่าตัวเลขเป็น Integer, Double, Float
        Integer a =Integer.valueOf(9);
        Double b = Double.valueOf(5d);
        Float c = Float.valueOf("80.699");
        //valueOf() แปลงค่าตัวเลขเป็น String
        System.out.println("valueOf="+a); 
        System.out.println("valueOf="+b);
        System.out.println("valueOf="+c);
        //round() ปัดเศษทศนิยมขึ้นหรือลง
        System.out.println("round="+Math.round(a));
        System.out.println("round="+Math.round(b)); 
        System.out.println("round="+Math.round(c)); 
        //ceil() ปัดเศษทศนิยมขึ้น
        System.out.println("ceil="+Math.ceil(a));
        System.out.println("ceil="+Math.ceil(b));
        System.out.println("ceil="+Math.ceil(c));
        //floor() ปัดเศษทศนิยมลง
        System.out.println("floor="+Math.floor(a));
        System.out.println("floor="+Math.floor(b));
        System.out.println("floor="+Math.floor(c));
        //max() หาค่าสูงสุด
        System.out.println("max="+Math.max(a, b));
        //min() หาค่าต่ำสุด
        System.out.println("min="+Math.min(a, b));
        //random() สุ่มตัวเลข
        System.out.println("random="+Math.random());
        //isUpperCase() ตรวจสอบว่าเป็นตัวพิมพ์ใหญ่หรือไม่
        System.out.println("isUpperCase="+Character.isUpperCase('c'));
        System.out.println("isUpperCase="+Character.isUpperCase('C'));
        // isLowerCase() ตรวจสอบว่าเป็นตัวพิมพ์เล็กหรือไม่
        System.out.println("isLowerCase="+Character.isLowerCase('c'));
        System.out.println("isLowerCase="+Character.isLowerCase('C'));
        // toUpperCase() แปลงเป็นตัวพิมพ์ใหญ่
        System.out.println("toUpperCase="+Character.toUpperCase('c'));
        // toLowerCase() แปลงเป็นตัวพิมพ์เล็ก
        System.out.println("toLowerCase="+Character.toLowerCase('C'));
        // toString() แปลงเป็น String
        System.out.println("toString="+Integer.toString(123));
        // parseInt() แปลงเป็น int
        System.out.println("parseInt="+Integer.parseInt("123"));
        // parseDouble() แปลงเป็น double
        System.out.println("parseDouble="+Double.parseDouble("123.456"));
        // parseFloat() แปลงเป็น float   
        System.out.println("parseFloat="+Float.parseFloat("123.456"));
        //date แสดงวันที่ปัจจุบัน
        Date date = new Date();
        System.out.println(date); 
        //sum() บวกเลข
        int[] numbers = {1, 2, 3, 4, 5};
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        System.out.println("Sum: " + sum);
        //sumarray 
        int[] numbers1 = {1, 2, 3, 4, 5};
        int sum1 = Arrays.stream(numbers1).sum();
        System.out.println("Sum1: " + sum1);
        //sumarraylist
        List<Integer> numbers2 = new ArrayList<>();
        numbers2.add(1);
        numbers2.add(2);
        numbers2.add(3);
        int sum2 = 0;
        for (int number2 : numbers2) {
            sum2 += number2;
        }
        System.out.println("Sum2: " + sum2);
        //sumlist
        List<Integer> numbers3 = List.of(6,7,8,9,10);
        int sum3 = numbers3.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Sum3: " + sum3);

     }
}
