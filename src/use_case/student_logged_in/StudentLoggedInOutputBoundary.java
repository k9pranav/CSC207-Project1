package use_case.student_logged_in;

import data_access.FileStudentDataAccessObject;

public interface StudentLoggedInOutputBoundary {
    public void prepareCourseLandingPage(FileStudentDataAccessObject studentDataAccessObject);
}
