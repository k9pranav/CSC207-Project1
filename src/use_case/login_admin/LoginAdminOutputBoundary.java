package use_case.login_admin;

public interface LoginAdminOutputBoundary {
    void prepareSuccessView(LoginAdminOutputData admin);

    void prepareFailView(String error);

    void prepareCancelView();
}
