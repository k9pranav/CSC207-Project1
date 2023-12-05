package use_case.edit_course_task;

import entity.Course;

import java.text.SimpleDateFormat;

public class EditCourseTaskInputData {

    private final String taskName;
    private final String type;

    private final SimpleDateFormat deadline;

    private final float taskWeight;

    private final Course taskCourse;
    public EditCourseTaskInputData(String taskName,
                                   String type,
                                   SimpleDateFormat deadline,
                                   Float taskWeight,
                                   Course taskCourse) {
        this.taskName = taskName;
        this.type = type;
        this.deadline = deadline;
        this.taskWeight = taskWeight;
        this.taskCourse = taskCourse;
    }

    String getTaskName(){return taskName;}
    String getTaskType(){return type;}

    SimpleDateFormat getDeadine(){return deadline;}

    float getTaskWeight(){return taskWeight;}

    Course getTaskCourse(){return  taskCourse;}


}
