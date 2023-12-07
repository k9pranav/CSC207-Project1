package use_case.login_student;

import use_case.login_admin.LoginAdminOutputData;

public interface LoginStudentOutputBoundary {
    void prepareSuccessView(LoginStudentOutputData student);

    void prepareFailView(String error);
    void prepareCancelView();
}
