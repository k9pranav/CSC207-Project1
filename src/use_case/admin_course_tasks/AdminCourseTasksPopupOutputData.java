package use_case.admin_course_tasks;

import entity.Course;

import java.text.SimpleDateFormat;

public class AdminCourseTasksPopupOutputData {
    private String name;
    private SimpleDateFormat deadline;
    private Float weight;
    private Float grade;
    private Course course;

    public AdminCourseTasksPopupOutputData(String name, SimpleDateFormat date, Float weight, Float grade, Course course){
        this.name = name;
        this.deadline = date;
        this.weight = weight;
        this.grade = grade;
        this.course = course;
    }

    public String getName(){
        return this.name;
    }

    public SimpleDateFormat getDeadline(){
        return this.deadline;
    }

    public Float getWeight(){return this.weight;}
    public Float getGrade(){return this.grade;}
    public String getCourseName(){return this.course.getCourseName();}

    public String getCourseCode(){return this.course.getCourseCode();}
}
