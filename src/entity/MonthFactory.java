package entity;

public class MonthFactory {

    public static Month create(int monthNumber, int year){
        return new Month(monthNumber, year);
    }
}
