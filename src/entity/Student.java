package entity;


import java.util.*;

public class Student implements Person{
    private final String firstName;
    private final String lastName;
    private final String password;
    private final String repeatPassword;
    private final String email;
    private String calendarId;
    public ArrayList<Course> coursesList = new ArrayList<>();
    private ArrayList<Task> tasks;
    private HashMap<String, Float> studentGrades = new HashMap<>();
    private HashMap<String, HashMap<String, Float>> studentTaskGrades = new HashMap<>();

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
    public String getRepeatPassword() {
        return repeatPassword;
    }
    @Override
    public String getEmail() {
        return email;
    }
    public void setTask(StudentTask task){
        this.tasks.add(task);
    }
    public void addCourseTask(CourseTask task){
        this.tasks.add(task);
        this.studentTaskGrades.get(task.getCourse().getCourseCode()).put(task.getTaskName(), 0.0f);
    }

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
                if((courseCode).equals(((CourseTask) tasks.get(i)).getCourse().getCourseCode())){
                    courseTasks.add((CourseTask) tasks.get(i));
                }
            }
        }
        return courseTasks;
    }

    public void setCourse(Course course){
        if(!coursesList.contains(course)){
            this.coursesList.add(course);
            this.studentGrades.put(course.getCourseCode(), 0.0f);
            this.studentTaskGrades.put(course.getCourseCode(), null);
        }
        // adds the course to courses list, grades map, task grades map if it's new
    }

    public void setCourseGrade(String courseCode, Float grade){
        this.studentGrades.put(courseCode, grade);
    }

    public void setTaskGrade(String taskName, Float grade){
        CourseTask task = (CourseTask) this.getTaskFromName(taskName);
        this.studentTaskGrades.get(task.getCourse().getCourseCode()).put(taskName, grade);

        // change the current course grade
        Set<String> gradedTasks = studentTaskGrades.get(task.getCourse().getCourseCode()).keySet();
        Float currentGrade1 = 0.0f;
        for (String key: gradedTasks) {
            currentGrade1 += studentTaskGrades.get(task.getCourse().getCourseCode()).get(key);
        }
        float currentGrade = (currentGrade1 / gradedTasks.size());
        studentGrades.put(task.getCourse().getCourseCode(), currentGrade);
    }

    public Float getTaskGrade(String taskName){
        CourseTask task = (CourseTask) this.getTaskFromName(taskName);
        return this.studentTaskGrades.get(task.getCourse().getCourseCode()).get(taskName);
    }

    public Float getCourseGrade(String courseCode){
        return this.studentGrades.get(courseCode);
    }

    public HashMap<String, Float> getStudentGrades(){
        return studentGrades;
    }

    public void setCalendarID(String id){
        this.calendarId = id;
    }
}
