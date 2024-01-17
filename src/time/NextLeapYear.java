package time;

import java.time.LocalDate;

public class NextLeapYear {
    private static int tm_year = LocalDate.now().getYear();
    public NextLeapYear(int year) {
        this.tm_year = year;
    }
    public boolean isLeapYear(int year) {
//        LocalDate localDate = LocalDate.now();
//        tm_year = localDate.getYear();
        tm_year = year;
        return tm_year % 100 == 0 && tm_year % 400 == 0 || tm_year % 100 != 0 && tm_year % 4 == 0;
    }
    public static boolean isLeapYear() {
        tm_year  = LocalDate.now().getYear();
        return tm_year % 100 == 0 && tm_year % 400 == 0 || tm_year % 100 != 0 && tm_year % 4 == 0;
    }
}
