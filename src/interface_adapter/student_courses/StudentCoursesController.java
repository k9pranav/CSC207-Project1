package interface_adapter.student_courses;

import entity.Student;
import use_case.student_courses.StudentCoursesInputBoundary;
import use_case.student_courses.StudentCoursesInputData;

public class StudentCoursesController {
    private final StudentCoursesInputBoundary interactor;

    public StudentCoursesController(StudentCoursesInputBoundary interactor){
        this.interactor = interactor;
    }
    public void execute(String buttonPressed, Student loggedIn){
        StudentCoursesInputData inputData = new StudentCoursesInputData(buttonPressed, loggedIn);
        interactor.execute(inputData);
    }
}
