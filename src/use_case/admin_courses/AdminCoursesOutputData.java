package use_case.admin_courses;

import entity.Admin;
import entity.CourseTask;

import java.util.ArrayList;

public class AdminCoursesOutputData {
    private Admin admin;
    private ArrayList<CourseTask> tasks;

    public AdminCoursesOutputData(ArrayList<CourseTask> tasks, Admin admin){
        this.tasks = tasks;
        this.admin = admin;
    }

    public ArrayList<CourseTask> getTasks(){return tasks;}
    public Admin getLoggedInUser(){return admin;}
}
