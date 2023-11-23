package use_case.student_courses;

import entity.Student;

public class StudentCoursesInputData {
    final private String buttonPressed;
    final private Student loggedIn;

    public StudentCoursesInputData(String buttonPressed, Student loggedIn){
        this.buttonPressed = buttonPressed;
        this.loggedIn = loggedIn;
    }

    public String getButton(){return buttonPressed;}
    public Student getLoggedIn(){return loggedIn;}
}
