package entity;

public class StudentFactory implements PersonFactory{
    @Override
    public Person create(String firstName, String lastName, String password, String repeatPassword, String email) {
        return new Student(firstName, lastName, password, repeatPassword, email);
    }
}
