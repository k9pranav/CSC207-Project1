package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Week implements Iterable<Day>{

    private final Month month;

    private final int weekInMonth;

    private HashMap<Integer, Day> daysList = new HashMap<>();

    public Week(Month month, int weekInMonth) {
        this.month = month;
        this.weekInMonth = weekInMonth;
    }

    public void addDays(Day day) {
        daysList.put(day.getDayOfTheWeek() - 1, day);

    }

    public int getWeekInMonth() {
        return weekInMonth;
    }

    public Month getMonth() {
        return month;
    }

    public Day getDay(int dayOfWeek) {
        if (daysList.containsKey(dayOfWeek)){
            return daysList.get(dayOfWeek);
        } else {
            throw new RuntimeException();
        }
    }

    public boolean hasDay(int dayOfWeek){
        return daysList.containsKey(dayOfWeek);
    }

    @Override
    public Iterator<Day> iterator() {
        return new dayIterator();
    }

    private class dayIterator implements Iterator<Day> {

        int currentDay = 0;

        dayIterator() {}

        @Override
        public boolean hasNext() {
            return currentDay < daysList.size() && daysList.get(currentDay) != null;
        }

        @Override
        public Day next() {
            if (currentDay >= daysList.size()){
                throw new NoSuchElementException();
            } else {
                return daysList.get(currentDay++);
            }
        }
    }


}