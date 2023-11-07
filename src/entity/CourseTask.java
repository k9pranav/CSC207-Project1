package entity;

import java.text.SimpleDateFormat;

public class CourseTask extends Task implements CreateCourseTask {

    private final Course taskCourse;

    private Float taskGrade;

    private Float taskWeight;

    private Boolean gradeReleased;


    public CourseTask(String taskName, String type, SimpleDateFormat deadLine,
                      Course taskCourse, Float taskWeight) {
        super(taskName, type, deadLine);
        this.taskCourse = taskCourse;
        this.taskWeight = taskWeight;
        this.gradeReleased = false;

    }

    public Course getCourse(){return taskCourse;}

    public Float getGrade(){return taskGrade;}

    public void setGrade(Float newGrade){taskGrade = newGrade;}

    @Override
    public void setWeight(Float newWeight){taskWeight = newWeight;}

    @Override
    public void setDeadline(SimpleDateFormat newDeadline) {
        deadLine = newDeadline;
    }


    // need getGrade method
}
