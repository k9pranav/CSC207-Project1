package use_case.login_admin;

import entity.Admin;
import use_case.landing_page.LandingPageOutputBoundary;

public class LoginAdminInteractor implements LoginAdminInputBoundary {
    final LoginAdminDataAccessInterface adminDataAccessObject;

    final LoginAdminOutputBoundary loginAdminPresenter;
    public LoginAdminInteractor(LoginAdminDataAccessInterface adminDataAccessObject, LoginAdminOutputBoundary loginAdminOutputBoundary) {
        this.adminDataAccessObject = adminDataAccessObject;
        this.loginAdminPresenter = loginAdminOutputBoundary;
    }

    @Override
    public void execute(LoginAdminInputData loginAdminInputData) {
        String email = loginAdminInputData.getEmail();
        String password = loginAdminInputData.getPassword();

        if (!adminDataAccessObject.existByName(email)) {
            loginAdminPresenter.prepareFailView(email + "Account does not exist");
        } else {
            String pswrd = adminDataAccessObject.get(email).getPassword();
            if (!password.equals(pswrd)) {
                loginAdminPresenter.prepareFailView("Incorrect password for " + email + ".");
            } else {
                Admin admin = adminDataAccessObject.get(loginAdminInputData.getEmail());

                LoginAdminOutputData loginAdminOutputData = new LoginAdminOutputData(admin.getEmail(), false);
                loginAdminPresenter.prepareSuccessView(loginAdminOutputData);

            }
        }

    }

    public void executeCancel(){
        loginAdminPresenter.prepareCancelView();
    }
}


