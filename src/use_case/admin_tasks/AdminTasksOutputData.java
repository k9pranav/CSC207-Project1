package use_case.admin_tasks;

import entity.Admin;
import entity.CourseTask;

public class AdminTasksOutputData {
    private CourseTask task;
    private Admin admin;

    public AdminTasksOutputData(CourseTask task, Admin admin){
        this.task = task;
        this.admin = admin;
    }

    public CourseTask getTask(){return this.task;}

    public Admin getLoggedIn(){return this.admin;}
}
