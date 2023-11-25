package entity;

import java.util.ArrayList;

public class Year {

    private final int year;

    private ArrayList<Month> months = new ArrayList<>();


    public Year(int year) {
        this.year = year;
    }

    public void setMonths(Month month) {
        this.months.add(month.getMonthNumber(), month);
    }

    public int getYear() {
        return year;
    }

    public Month getMonth(int monthNumber){
        return months.get(monthNumber);
    }
}
