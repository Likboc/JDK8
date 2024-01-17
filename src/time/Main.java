package time;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        NextLeapYear nextLeapYear = new NextLeapYear(2001);
        if(nextLeapYear.isLeapYear()) {
            System.out.println("今年是闰年");
        }else{
            System.out.println("今年是平年");
        }
//        System.out.println("Hello world!");
        Class<NextLeapYear> demo = NextLeapYear.class;
        System.out.println(LocalTime.now());

        int max = 0x7FFFFFFF;
        int min = 0x80000000;
        int t = max * 10;
        char a = '1';
        String b = "123";
        if(max * 2 > Integer.MAX_VALUE) {
            System.out.println("hiiii");
        }
        System.out.println(max);

    }
}