package entity;

public class WeekFactory {
    public static Week create(Month month, int weekInMonth) {
        return new Week(month, weekInMonth);

    }
}
