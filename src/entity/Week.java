package entity;

import java.util.ArrayList;

public class Week {

    private final String month;

    private final int weekInMonth;

    private ArrayList<Day> daysArrayList = new ArrayList<>();

    public Week(String month, int weekInMonth) {
        this.month = month;
        this.weekInMonth = weekInMonth;
    }

    public void addDays(Day day) {
        daysArrayList.add(day.getDayOfTheWeek(), day);

    }

    public int getWeekInMonth() {
        return weekInMonth;
    }

    public String getMonth() {
        return month;
    }

    public Day getDay(int index) {
        return daysArrayList.get(index);
    }
}
