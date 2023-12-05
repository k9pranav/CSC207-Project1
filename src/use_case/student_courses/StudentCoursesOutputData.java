package use_case.student_courses;

import entity.CourseTask;
import entity.Student;

import java.util.ArrayList;

public class StudentCoursesOutputData {
    private Student student;
    private ArrayList<CourseTask> tasks;

    public StudentCoursesOutputData(ArrayList<CourseTask> tasks, Student student){
        this.tasks = tasks;
        this.student = student;
    }

    public ArrayList<CourseTask> getTasks(){return tasks;}

    public Student getLoggedInUser(){return student;}
}
