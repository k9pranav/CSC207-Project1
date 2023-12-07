package entity;

public class YearFactory {

    public static Year create(int year) {
        return new Year(year);
    }
}
