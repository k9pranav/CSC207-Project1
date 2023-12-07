package entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Week implements Iterable<Day>{

    private final Month month;

    private final int weekInMonth;

    private ArrayList<Day> daysArrayList = new ArrayList<>();

    public Week(Month month, int weekInMonth) {
        this.month = month;
        this.weekInMonth = weekInMonth;
    }

    public void addDays(Day day) {
        daysArrayList.add(day.getDayOfTheWeek(), day);

    }

    public int getWeekInMonth() {
        return weekInMonth;
    }

    public Month getMonth() {
        return month;
    }

    public Day getDay(int index) {
        return daysArrayList.get(index);
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
            return currentDay < daysArrayList.size() && daysArrayList.get(currentDay) != null;
        }

        @Override
        public Day next() {
            if (currentDay >= daysArrayList.size()){
                throw new NoSuchElementException();
            } else {
                return daysArrayList.get(currentDay++);
            }
        }
    }


}
