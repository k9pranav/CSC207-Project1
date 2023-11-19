package entity;

import java.text.SimpleDateFormat;

public class CourseTask extends Task implements CreateCourseTask {

    private Course taskCourse;

    private Float taskGrade;

    private Float taskWeight;

    private Boolean gradeReleased; // idk if we need this


    public CourseTask(String taskName, String type, SimpleDateFormat deadLine, Float taskWeight) {
        super(taskName, type, deadLine);
        this.taskWeight = taskWeight;
        this.gradeReleased = false;

    }

    public Course getCourse(){return taskCourse;}

    public void setCourse(Course course){this.taskCourse = course;}

    public Float getGrade(){return taskGrade;}

    public void setGrade(Float newGrade){taskGrade = newGrade;}

    @Override
    public void setWeight(Float newWeight){taskWeight = newWeight;}

    public Float getWeight(){return taskWeight;}

    @Override
    public void setDeadline(SimpleDateFormat newDeadline) {
        deadLine = newDeadline;
    }


    // need getGrade method
}
