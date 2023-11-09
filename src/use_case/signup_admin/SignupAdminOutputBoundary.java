package use_case.signup_admin;

public interface SignupAdminOutputBoundary {

    void prepareSuccessView(SignupOutputData admin);

    void prepareFailView(String error);
}
