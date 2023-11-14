package entity;

import com.google.api.services.calendar.Calendar;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;


public class Student implements Person{
    private final String firstName;

    private final String lastName;

    private final String password;

    private final String email;

    private final CreateCalendar Calendar;

    public ArrayList<Course> coursesList = new ArrayList<>();

    public Dictionary<String, Integer> studentGrades = new Hashtable<>();

    public Student(String firstName, String lastName, String password, String email) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;

        com.google.api.services.calendar.model.Calendar calendar = new
                com.google.api.services.calendar.model.Calendar();
        calendar.setSummary(email);
        calendar.setTimeZone("America/Toronto");
        com.google.api.services.calendar.model.Calendar createdCalendar =
                service.calendars().insert(calendar).execute();

        this.Calendar = createdCalendar;

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
    public String getEmail() {
        return email;
    }
    @Override
    public ArrayList<String> getCourses(){
        return null; // TODO: implement this
    }

    public Dictionary<String, Integer> getStudentGrades(){
        return studentGrades;
    }

    //Gets the id for the user's google calendar
    public String getCalendarId () {return Calendar.getId();}
}
