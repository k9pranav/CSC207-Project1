package entity;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;


public class Student implements Person{
    private final String firstName;

    private final String lastName;

    private final String password;

    private final String repeatPassword;

    private final String email;

    private final CreateCalendar Calendar;

    public ArrayList<Course> coursesList = new ArrayList<>();

    public Dictionary<String, Integer> studentGrades = new Hashtable<>();

    public Student(String firstName, String lastName, String password, String repeatPassword, String email) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.repeatPassword = repeatPassword;
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
    public String getRepeatPassword() {
        return repeatPassword;
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
