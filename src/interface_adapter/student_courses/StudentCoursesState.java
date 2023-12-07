package interface_adapter.student_courses;

import entity.Student;

public class StudentCoursesState {
    private Student studentLoggedIn;
    private String average;

    public StudentCoursesState(StudentCoursesState copy){
        studentLoggedIn = copy.studentLoggedIn;
        average = copy.average;
    }

    public StudentCoursesState(){}
    public void setLoggedInUser(Student student){this.studentLoggedIn = student;}
    public Student getLoggedInUser(){return this.studentLoggedIn;}

    public void setAverage(String avg){this.average = avg;}

    public String getAverage(){return average;}

}
