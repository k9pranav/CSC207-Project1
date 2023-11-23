package interface_adapter.admin_course_tasks;
import entity.Course;
import entity.Admin;
import entity.CourseTask;

import java.util.ArrayList;

public class AdminCourseTasksState {
    private Admin adminLoggedIn;
    private Course course;
    private ArrayList<CourseTask> tasks;

    public AdminCourseTasksState(AdminCourseTasksState copy){adminLoggedIn = copy.adminLoggedIn;}
    public AdminCourseTasksState(){}
    public void setLoggedInUser(Admin admin){this.adminLoggedIn = admin;}
    public Admin getLoggedInUser(){return this.adminLoggedIn;}
    public Course getCourse(){return this.course;}
    public void setTasks(ArrayList<CourseTask> tasks){this.tasks = tasks;}
    public ArrayList<CourseTask> getTasks(){return this.tasks;}
    public void setCourse(Course course){this.course = course;}
}
