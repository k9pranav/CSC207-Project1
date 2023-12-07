package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Month implements Iterable<Week>{
    private final int monthNumber;

    private final int year;

    private HashMap<Integer, Week> weeksList = new HashMap<>();


    public Month(int monthNumber, int year) {
        this.monthNumber = monthNumber;
        this.year = year;
    }

    public void addWeeks(Week week) {
        this.weeksList.put(week.getWeekInMonth() ,week);
    }

    public int getMonthNumber() {
        return monthNumber;
    }

    public int getYear() {
        return year;
    }

    public Week getWeek(int index) {
        if(weeksList.containsKey(index)){
            return weeksList.get(index);
        } else {
            throw new RuntimeException();
        }
    }

    public boolean hasWeek(int weekNum){
        return weeksList.containsKey(weekNum);
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
            return currentWeek < weeksList.size() && weeksList.get(currentWeek) != null;
        }

        /**
         * @return the next week in weeksArrayList
         */
        @Override
        public Week next() {
            if (currentWeek >= weeksList.size()){
                throw new NoSuchElementException();
            } else {
                return weeksList.get(currentWeek++);
            }
        }
    }






}
