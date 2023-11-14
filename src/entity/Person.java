package entity;

import java.util.ArrayList;

public interface Person {
    String getFirstName();

    String getLastName();

    String getPassword();

    String getEmail();

    ArrayList<String> getCourses();

    String getCalendarId();

}
