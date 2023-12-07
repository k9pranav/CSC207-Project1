package use_case.student_course_tasks;

import entity.Student;

public class StudentCourseTasksInputData {
    final private String button;
    final private Student loggedIn;

    public StudentCourseTasksInputData(String button, Student loggedIn){
        this.button = button;
        this.loggedIn = loggedIn;
    }
    public String getButtonPressed(){return button;}
    public Student getLoggedIn(){return loggedIn;}
}
