package entity;

public interface PersonFactory {
    Person create(String firstName, String lastName, String password, String repeatPassword, String email);

}
