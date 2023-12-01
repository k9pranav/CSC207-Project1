package use_case.student_tasks;

import entity.Course;

import java.text.SimpleDateFormat;

public class StudentTasksOutputData {
    private String name;
    private SimpleDateFormat deadline;
    private Float weight;
    private Float grade;
    private Course course;

    private String type;

    public StudentTasksOutputData(String name, SimpleDateFormat date, Float weight, Float grade, Course course, String type){
        this.name = name;
        this.deadline = date;
        this.weight = weight;
        this.grade = grade;
        this.course = course;
        this.type = type;
    }

    public String getName(){
        return this.name;
    }
    public String getType(){return type;}

    public SimpleDateFormat getDeadline(){
        return this.deadline;
    }

    public Float getWeight(){return this.weight;}
    public Float getGrade(){return this.grade;}
    public String getCourseName(){return this.course.getCourseName();}

    public String getCourseCode(){return this.course.getCourseCode();}
}
