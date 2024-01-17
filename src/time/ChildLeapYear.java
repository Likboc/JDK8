package time;

import java.time.LocalDate;

public class ChildLeapYear extends NextLeapYear {
    public ChildLeapYear() {
        super(LocalDate.now().getYear());

    }

    @Override
    public boolean isLeapYear(int year) {
        return super.isLeapYear(year);
    }

    
}
