import use_case.signup_student.SignupStudentOutputData;ackage use_case.signup_student;

public interface SignupStudentOutputBoundary {
    void prepareSuccessView(SignupStudentOutputData user);

    void prepareFailView(String error);
}
