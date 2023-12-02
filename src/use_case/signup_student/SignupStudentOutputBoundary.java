package use_case.signup_student;

import use_case.signup_student.SignupStudentOutputData;

public interface SignupStudentOutputBoundary {
    void prepareSuccessView(SignupStudentOutputData user);

    void prepareFailView(String error);
}
