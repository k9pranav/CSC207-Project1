package use_case.signup_admin;

import entity.Admin;
import entity.AdminFactory;

public class SignupAdminInteractor implements SignupAdminInputBoundary {

    final SignupAdminDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final AdminFactory AdminFactory;

}
