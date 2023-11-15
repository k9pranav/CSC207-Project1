package entity;

public class AdminFactory implements PersonFactory{

    public Admin create(String firstName, String lastName, String password, String repeatPassword, String email) {
        return new Admin(firstName, lastName, password, repeatPassword, email);
    }

}
