package entity;

import java.util.ArrayList;

public class Admin implements Person{
    private final String firstName;

    private final String lastName;

    private final String password;

    private final String repeatPassword;

    private final String email;

    public ArrayList<Course> coursesList = new ArrayList<>();

    public Admin(String firstName, String lastName, String password, String repeatPassword, String email) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.email = email;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getRepeatPassword() {
        return repeatPassword;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public ArrayList<String> getCourses(){
        return null;
    }

    public String getCalendarId () {return Calendar.getId();}
}
