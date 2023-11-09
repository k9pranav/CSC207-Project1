package entity;

import java.util.ArrayList;

public class Course {
    private final String courseName;
    private final String courseCode;
    private final Integer courseYear;
    private final String courseSession;
    private ArrayList<CourseTask> courseTasks;
    private final String courseDescription;
    private final Admin courseAdmin;
    private ArrayList<Student> studentEnrolled;

    /**
     *
     * @param courseName
     * @param courseCode
     * @param courseYear
     * @param courseSession
     * @param courseDescription
     * @param courseAdmin
     */
    Course(String courseName, String courseCode, Integer courseYear, String courseSession,
           String courseDescription, Admin courseAdmin){
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.courseYear = courseYear;
        this.courseSession = courseSession;
        this.courseDescription = courseDescription;
        this.courseTasks = new ArrayList<CourseTask>();
        this.courseAdmin = courseAdmin;
        this.studentEnrolled = new ArrayList<Student>();

    }

    public String getCourseName(){return courseName;}
    public String getCourseCode(){return courseCode;}
    public String getCourseDescription(){return courseDescription;}
    // public Integer getCourseID(){return courseID;}
    public Admin getCourseAdmin(){return courseAdmin;}

    public Integer getCourseYear() {return courseYear;}

    public String getCourseSession() {return courseSession;}

    public ArrayList<Student> getStudentEnrolled() {
        return studentEnrolled;
    }

    public ArrayList<CourseTask> getCourseTasks(){
        return courseTasks;
    }
    public void addStudent(Student student){
        studentEnrolled.add(student);
    }

    public void removeStudent(Student student){
        studentEnrolled.remove(student);
    }

    public void addTask(CourseTask task){
        courseTasks.add(task);
    }

    public void removeTask(CourseTask task){
        courseTasks.remove(task);
    }
    public Double getAverageGrade(){
        Double total = 0.0;
        for (CourseTask courseTask : courseTasks) {
            total = total + courseTask.getGrade();
        }
        if (total != null){
            return (total / courseTasks.size());
        }
        else {return 0.0;}
    }

    public static class courseBuilder {
        private final String courseName;
        private final Integer courseYear;
        private final String courseSession;
        private ArrayList<CourseTask> courseTasks;
        private final String courseDescription;
        private final Admin courseAdmin;
        private ArrayList<Student> studentEnrolled;

        public courseBuilder(String courseName, Integer courseYear, String courseSession, String courseDescription, Admin courseAdmin,  ArrayList<Student> studentEnrolled){
            this.courseName = courseName;
            this.courseYear = courseYear;
            this.courseSession = courseSession;
            this.courseTasks = new ArrayList<CourseTask>();
            this.courseDescription = courseDescription;
            this.courseAdmin = courseAdmin;
            this.studentEnrolled = new ArrayList<Student>();
        }

        public void addCourseTask(CourseTask courseTask){
            courseTasks.add(courseTask);
        }

        public void addStudent(Student student){
            studentEnrolled.add(student);
        }

        public Course build(){
            return new Course(this);
        }
    }

}
