package use_case.student_logged_in;

import data_access.FileStudentDataAccessObject;
import use_case.student_landing_page.StudentLandingPageOutputBoundary;

public class StudentLoggedinInteractor implements StudentLoggedInInputBoundary {
    final StudentLoggedInOutputBoundary studentLoggedInPresenter;
    final FileStudentDataAccessObject fileStudentDataAccessObject;
    public StudentLoggedinInteractor(
            StudentLoggedInOutputBoundary studentLoggedInPresenter,
            FileStudentDataAccessObject fileStudentDataAccessObject) {
        this.studentLoggedInPresenter = studentLoggedInPresenter;
        this.fileStudentDataAccessObject = fileStudentDataAccessObject;
    }

    public void execute(String buttonPressed){
        if ("courses".equals(buttonPressed)){
            this.studentLoggedInPresenter.prepareCourseLandingPage(this.fileStudentDataAccessObject);
        } else if ("calendar".equals(buttonPressed)) {
            this.studentLoggedInPresenter.prepareCalendarPage(this.fileStudentDataAccessObject);
        }
    }

}
