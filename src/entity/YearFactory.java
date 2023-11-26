package entity;

public class YearFactory {

    public Year create(int year) {
        return new Year(year);
    }
}
