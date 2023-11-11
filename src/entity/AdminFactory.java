package entity;

public class AdminFactory implements PersonFactory{
    @Override
    public Person create(String firstName, String lastName, String password, String repeatPassword, String id, String email) {
        return new Admin(firstName, lastName, password, repeatPassword, id, email);
    }
}
