package use_case.edit_course_task;

import entity.Admin;
import entity.Course;

import java.text.SimpleDateFormat;

public class EditCourseTaskInputData {

    private final String taskName;
    private final String type;

    private final SimpleDateFormat deadline;
    private final Admin loggedIn;

    private final float taskWeight;

    private final Course taskCourse;
    public EditCourseTaskInputData(String taskName,
                                   String type,
                                   SimpleDateFormat deadline,
                                   Float taskWeight,
                                   String taskCourse, Admin loggedIn) {
        this.taskName = taskName;
        this.type = type;
        this.deadline = deadline;
        this.taskWeight = taskWeight;
        this.loggedIn = loggedIn;
        this.taskCourse = loggedIn.getCourseFromCourseCode(taskCourse);
    }

    String getTaskName(){return taskName;}
    String getTaskType(){return type;}

    SimpleDateFormat getDeadline(){return deadline;}

    Admin getLoggedIn(){return loggedIn;}
    float getTaskWeight(){return taskWeight;}

    Course getTaskCourse(){return  taskCourse;}


}
