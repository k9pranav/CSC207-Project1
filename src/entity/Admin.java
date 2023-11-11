package entity;

import java.util.ArrayList;

public class Admin implements Person{
    private final String firstName;

    private final String lastName;

    private final String password;

    private final String repeatPassword;

    private final String id;

    private final String email;

    public ArrayList<Course> coursesList = new ArrayList<>();

    public Admin(String firstName, String lastName, String password, String id, String repeatPassword, String email) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.id = id;
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

    public String get_person_ID(){return id;}

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
