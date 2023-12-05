package use_case.login_student;

public interface LoginStudentInputBoundary {
    void execute(LoginStudentInputData loginStudentInputData);
    void executeCancel();
}
