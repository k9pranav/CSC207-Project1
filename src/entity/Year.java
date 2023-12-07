package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Year implements Iterable<Month>{

    private final int year;

    private HashMap<Integer, Month> monthsList = new HashMap<>();


    public Year(int year) {
        this.year = year;
    }

    public void addMonth(Month month) {
        this.monthsList.put(month.getMonthNumber(), month);
    }

    public int getYear() {
        return year;
    }

    public Month getMonth(int monthNumber){
        return monthsList.get(monthNumber);
    }

    public boolean hasMonth(int index){
        return monthsList.containsKey(index);

    }



    /**
     * @return a new Iterator object
     */
    @Override
    public Iterator<Month> iterator() {
        return new monthIterator();
    }

    private class monthIterator implements Iterator<Month>{

        int currentMonth = 0;

        /**
         * @return true if there is a next valid month available in the monthsArrayList, otherwise false
         */
        @Override
        public boolean hasNext() {
            return currentMonth < monthsList.size() && monthsList.get(currentMonth) != null;
        }

        /**
         * @return the next Month object in the monthsArrayList if there is a next month, otherwise throw a NoSuchElementException
         */
        @Override
        public Month next() {
            if (currentMonth >= monthsList.size()){
                throw new NoSuchElementException();
            } else {
                return  monthsList.get(currentMonth);
            }
        }
    }
}



