package interface_adapter.edit_course_task;

import entity.Admin;
import interface_adapter.admin_tasks.AdminTasksState;

import java.text.SimpleDateFormat;

public class EditCourseTaskState {

    private String taskName;
    private String taskType;
    private String errorString;
    private String courseCode;
    private SimpleDateFormat taskDeadline;
    private Float taskWeight;
    private Admin loggedInUser;

    public EditCourseTaskState(EditCourseTaskState copy){
        copy.loggedInUser = loggedInUser;
        copy.taskName = taskName;
        copy.taskType = taskType;
        copy.errorString = errorString;
        copy.courseCode = courseCode;
        copy.taskDeadline = taskDeadline;
        copy.taskWeight = taskWeight;
    }

    public EditCourseTaskState(){}

    public void setLoggedInUser(Admin user){loggedInUser = user;}
    public Admin getLoggedInUser(){return loggedInUser;}
    public void setTaskName(String s) {this.taskName = s;}
    public String getTaskName(){return taskName;}

    public void setTaskCourseCode(String s) {this.courseCode = s;}
    public String getCourseCode(){return courseCode;}

    public void setTaskType(String s) {this.taskType = s;}
    public String getTaskType(){return taskType;}

    public void setTaskDeadline(String s) {
        this.taskDeadline = new SimpleDateFormat(s);
    }
    public SimpleDateFormat getTaskDeadline(){return taskDeadline;}

    public void setTaskWeight(float v) {
        this.taskWeight = v;
    }
    public Float getTaskWeight(){return taskWeight;}

    public void setError(String errorstring) {
        this.errorString = errorstring;
    }
}
