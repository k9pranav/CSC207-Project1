package entity;

public class WeekFactory {
    public Week create(Month month, int weekInMonth) {
        return new Week(month, weekInMonth);

    }
}
