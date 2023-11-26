package entity;

public class MonthFactory {

    public Month create(int monthNumber, int year){
        return new Month(monthNumber, year);
    }
}
