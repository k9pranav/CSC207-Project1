package use_case.admin_courses;

import entity.Admin;
import entity.Course;
import entity.CourseTask;

import java.util.ArrayList;

public class AdminCoursesOutputData {
    private Admin admin;
    private ArrayList<CourseTask> tasks;

    private Course course;

    public AdminCoursesOutputData(ArrayList<CourseTask> tasks, Admin admin, Course course){
        this.tasks = tasks;
        this.admin = admin;
        this.course = course;
    }

    public ArrayList<CourseTask> getTasks(){return tasks;}
    public Admin getLoggedInUser(){return admin;}

    public void setCourse(Course course){this.course = course;}
    public Course getCourse(){return course;}
}
