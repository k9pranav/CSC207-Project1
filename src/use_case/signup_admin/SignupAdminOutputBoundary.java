package use_case.signup_admin;

public interface SignupAdminOutputBoundary {

    void prepareSuccessView(SignupAdminOutputData admin);

    void prepareFailView(String error);
}
