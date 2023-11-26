package entity;

public class DayFactory {
    public Day create(Month month, int dayOfTheMonth, int dayOfTheWeek){
        return new Day(month, dayOfTheMonth, dayOfTheWeek);
    }
}
