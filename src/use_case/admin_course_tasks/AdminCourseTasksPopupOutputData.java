package use_case.admin_course_tasks;

import entity.Course;

import java.text.SimpleDateFormat;

public class AdminCourseTasksPopupOutputData {
    private String name;
    private SimpleDateFormat deadline;
    private Float weight;
    private Course course;
    private String type;

    public AdminCourseTasksPopupOutputData(String name, SimpleDateFormat date, Float weight, Course course, String type){
        this.name = name;
        this.deadline = date;
        this.weight = weight;
        this.course = course;
        this.type = type;
    }

    public String getName(){
        return this.name;
    }

    public SimpleDateFormat getDeadline(){
        return this.deadline;
    }

    public Float getWeight(){return this.weight;}
    public String getCourseName(){return this.course.getCourseName();}

    public String getCourseCode(){return this.course.getCourseCode();}

    public String getType() {return type;}
}
