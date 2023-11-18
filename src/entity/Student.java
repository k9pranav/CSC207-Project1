package entity;


import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class Student implements Person{
    private final String firstName;

    private final String lastName;

    private final String password;

    private final String email;

    private String calendarId;
    public ArrayList<Course> coursesList = new ArrayList<>();

    private ArrayList<Task> tasks;

    public Dictionary<String, Integer> studentGrades = new Hashtable<>();

    public Student(String firstName, String lastName, String password, String email) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.tasks = new ArrayList<>();
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

    public void setTask(StudentTask task){this.tasks.add(task);}

    public ArrayList<Task> getTasks(){return tasks;}

    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    public String getCalendarId(){return this.calendarId;}

    @Override
    public ArrayList<String> getCourses(){
        return null; // TODO: implement this
    }

    public Dictionary<String, Integer> getStudentGrades(){
        return studentGrades;
    }
}
