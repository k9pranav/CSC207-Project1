package entity;

import java.util.ArrayList;

public class Month {

    private final int monthNumber;

    private final int year;

    private ArrayList<Week> weeks = new ArrayList<>();


    public Month(int monthNumber, int year) {
        this.monthNumber = monthNumber;
        this.year = year;
    }

    public void addWeeks(Week week) {
        this.weeks.add(week);
    }

    public int getMonthNumber() {
        return monthNumber;
    }

    public int getYear() {
        return year;
    }

    public Week getWeek(int index) {
        return weeks.get(index);
    }
}
