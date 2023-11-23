package entity;

import java.util.ArrayList;

public class Admin implements Person{
    private final String firstName;

    private final String lastName;

    private final String password;

    private final String repeatPassword;

    private final String email;
    private ArrayList<CourseTask> tasks;

    public ArrayList<Course> coursesList = new ArrayList<>();

    private String calendarID;

    public Admin(String firstName, String lastName, String password, String repeatPassword, String email) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.email = email;
        this.tasks = new ArrayList<>();
        this.coursesList = new ArrayList<>();
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

    public String getRepeatPassword() {
        return repeatPassword;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public ArrayList<Course> getCourses(){
        return coursesList;
    }

    public void setCalendarID(String id){this.calendarID = id;}

    public String getCalendarId () {return this.calendarID;}

    public void setTask(CourseTask task){this.tasks.add(task);}

    public ArrayList<CourseTask> getTasks(){return this.tasks;}

    public CourseTask getTaskFromName(String taskName){
        CourseTask currentTask = null;
        for (int i = 0; i < tasks.size(); i++) {
            if ((taskName).equals(tasks.get(i).getTaskName())){
                currentTask = tasks.get(i);
            }
        }
        return currentTask;
    }
    public ArrayList<CourseTask> getTasksForCourse(String courseCode){
        ArrayList<CourseTask> courseTasks = null;
        for (int i = 0; i < tasks.size(); i++) {
            if(("courseCode").equals(((CourseTask) tasks.get(i)).getCourse().getCourseCode())){
                courseTasks.add(tasks.get(i));
            }
        }
        return courseTasks;
    }
}
