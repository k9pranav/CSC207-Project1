package use_case.signup_admin;

import entity.Admin;
import entity.AdminFactory;
import entity.Person;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class SignupAdminInteractor implements SignupAdminInputBoundary {

    final SignupAdminDataAccessInterface userDataAccessObject;
    final SignupAdminOutputBoundary userPresenter;
    Admin admin;

    public SignupAdminInteractor(SignupAdminDataAccessInterface userDataAccessObject,
                                 SignupAdminOutputBoundary userPresenter,
                                 AdminFactory adminFactory){
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
        this.admin = admin;
    }

    @Override
    public void execute(SignupAdminInputData signupAdminInputData) throws GeneralSecurityException, IOException {
        if (userDataAccessObject.existsByEmail(signupAdminInputData.getEmail())){
            userPresenter.prepareFailView("User Already Exists");
        } else if (!signupAdminInputData.getPassword().equals(signupAdminInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords Don't Match");
        } else {
            Admin admin = new Admin(signupAdminInputData.getFirstName(),
                    signupAdminInputData.getLastName(), signupAdminInputData.getPassword(),
                    signupAdminInputData.getRepeatPassword(), signupAdminInputData.getEmail());

            userDataAccessObject.createCalendar(admin);
            userDataAccessObject.save(admin);


            SignupAdminOutputData signupAdminOutputData = new SignupAdminOutputData(admin.getFirstName(),
                    admin.getLastName(), admin.getEmail(), false);
        }

    }
}
