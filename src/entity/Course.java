package entity;

import java.util.ArrayList;

public class Course {
    private final String courseName;
    private final String courseCode;
    private final Integer courseID;
    private final ArrayList<Task> courseTasks;
    private final String courseDescription;
    private final Admin courseAdmin;
    private final ArrayList<Student> studentEnrolled;

    /**
     *
     * @param courseName: the name of the Course
     * @param courseCode: the course code of the course
     * @param courseID: the unique id of the offering of the course
     * @param courseDescription: description of the course
     */
    Course(String courseName, String courseCode, Integer courseID, String courseDescription, Admin courseAdmin){
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.courseID = courseID;
        this.courseDescription = courseDescription;
        this.courseTasks = new ArrayList<Task>();
        this.courseAdmin = courseAdmin;
        this.studentEnrolled = new ArrayList<Student>();

    }

    public String getCourseName(){return courseName;}
    public String getCourseCode(){return courseCode;}
    public String getCourseDescription(){return courseDescription;}
    public Integer getCourseID(){return courseID;}

    public void addStudent(Student student){
        studentEnrolled.add(student);
    }

    public void removeStudent(Student student){
        studentEnrolled.remove(student);
    }

    public void addTask(Task task){
        courseTasks.add(task);
    }

    public void removeTask(Task task){
        courseTasks.remove(task);
    }
    public Double getAverageGrade(){
        Double total = 0.0;
        for (Task courseTask : courseTasks) {
            total = total + courseTask.getGrade();
        }
        if (total != null){
            return (total / courseTasks.size());
        }
        else {return 0.0;}
    }
}
