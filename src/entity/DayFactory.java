package entity;

public class DayFactory {
    public static Day create(Month month, int dayOfTheMonth, int dayOfTheWeek){
        return new Day(month, dayOfTheMonth, dayOfTheWeek);
    }
}
