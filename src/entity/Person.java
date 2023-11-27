package entity;

import java.util.ArrayList;

public interface Person {
    String getFirstName();

    String getLastName();

    String getPassword();

    String getEmail();

    ArrayList<Course> getCourses();



    String getCalendarId();

}
