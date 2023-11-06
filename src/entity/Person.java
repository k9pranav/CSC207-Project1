package entity;

import java.util.ArrayList;

public interface Person {
    String getFirstName();

    String getLastName();

    String getPassword();

    String getRepeatPassword();

    String getEmail();

    ArrayList<String> getCourses();

}
