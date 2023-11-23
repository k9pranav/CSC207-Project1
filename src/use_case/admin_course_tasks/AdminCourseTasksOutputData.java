package use_case.admin_course_tasks;

import entity.Admin;
import entity.CourseTask;

public class AdminCourseTasksOutputData {
    private CourseTask task;
    private Admin admin;

    public AdminCourseTasksOutputData(CourseTask task, Admin admin){
        this.task = task;
        this.admin = admin;
    }

    public CourseTask getTask(){return this.task;}
    public Admin getLoggedIn(){return this.admin;}
}
