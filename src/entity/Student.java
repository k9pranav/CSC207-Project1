package entity;


import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;

public class Student implements Person{
    private final String firstName;

    private final String lastName;

    private final String password;

    private final String repeatPassword;

    private final String email;

    private String calendarId;
    public ArrayList<Course> coursesList = new ArrayList<>();

    private ArrayList<Task> tasks;

    public HashMap<String, Integer> studentGrades = new HashMap<>();

    public Student(String firstName, String lastName, String password, String repeatPassword, String email) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.email = email;
        this.tasks = new ArrayList<>();
        this.coursesList = new ArrayList<Course>();
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

    public void setTask(StudentTask task){this.tasks.add(task);}

    public ArrayList<Task> getTasks(){return tasks;}

    public Task getTaskFromName(String taskName){
        Task currentTask = null;
        for (int i = 0; i < tasks.size(); i++) {
            if ((taskName).equals(tasks.get(i).getTaskName())){
                currentTask = tasks.get(i);
            }
        }
        return currentTask;
    }

    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    public String getCalendarId(){return this.calendarId;}

    @Override
    public ArrayList<Course> getCourses(){
        return coursesList;
    }

    public ArrayList<CourseTask> getTasksForCourse(String courseCode){
        ArrayList<CourseTask> courseTasks = null;
        for (int i = 0; i < tasks.size(); i++) {
            if((tasks.get(i)) instanceof CourseTask){
                if(("courseCode").equals(((CourseTask) tasks.get(i)).getCourse().getCourseCode())){
                    courseTasks.add((CourseTask) tasks.get(i));
                }
            }
        }
        return courseTasks;
    }

    public void setCourse(Course course){this.coursesList.add(course);}
    public HashMap<String, Integer> getStudentGrades(){
        return studentGrades;
    }
}
