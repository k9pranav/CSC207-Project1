package entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Month implements Iterable<Week>{

    private final int monthNumber;

    private final int year;

    private ArrayList<Week> weeksArrayList = new ArrayList<>();


    public Month(int monthNumber, int year) {
        this.monthNumber = monthNumber;
        this.year = year;
    }

    public void addWeeks(Week week) {
        this.weeksArrayList.add(week);
    }

    public int getMonthNumber() {
        return monthNumber;
    }

    public int getYear() {
        return year;
    }

    public Week getWeek(int index) {
        return weeksArrayList.get(index);
    }

    @Override
    public Iterator<Week> iterator() {
        return new weekIterator();
    }

    private class weekIterator implements Iterator<Week> {

        int currentWeek = 0;

        weekIterator() {}

        /**
         * @return true if the weeksArrayList has a next element, otherwise false
         */
        @Override
        public boolean hasNext() {
            return currentWeek < weeksArrayList.size() && weeksArrayList.get(currentWeek) != null;
        }

        /**
         * @return the next week in weeksArrayList
         */
        @Override
        public Week next() {
            if (currentWeek >= weeksArrayList.size()){
                throw new NoSuchElementException();
            } else {
                return weeksArrayList.get(currentWeek++);
            }
        }
    }
}
