package use_case.signup_admin;

import entity.*;
import use_case.signup_student.SignupStudentInputData;
import use_case.signup_student.SignupStudentOutputData;

import java.io.IOException;

public class SignupAdminInteractor implements SignupAdminInputBoundary {

    final SignupAdminDataAccessInterface adminDataAccessObject;
    final SignupAdminOutputBoundary adminPresenter;
    final AdminFactory adminFactory;

    public SignupAdminInteractor(SignupAdminDataAccessInterface userDataAccessObject,
                                 SignupAdminOutputBoundary userPresenter,
                                 AdminFactory adminFactory) {
        this.adminDataAccessObject = userDataAccessObject;
        this.adminPresenter = userPresenter;
        this.adminFactory = adminFactory;
    }

    @Override
    public void execute(SignupAdminInputData signupAdminInputData) {
        if (adminDataAccessObject.existsByEmail(signupAdminInputData.getEmail())) {
            adminPresenter.prepareFailView("User Already Exists");
        } else if (!signupAdminInputData.getPassword().equals(signupAdminInputData.getRepeatPassword())) {
            adminPresenter.prepareFailView("Passwords Don't Match");
        } else {
            try {
                Admin admin = (Admin) adminFactory.create(signupAdminInputData.getFirstName(), signupAdminInputData.getLastName(), signupAdminInputData.getPassword(), signupAdminInputData.getRepeatPassword(), signupAdminInputData.getEmail());
                adminDataAccessObject.save(admin);
                SignupAdminOutputData signupAdminOutputData = new SignupAdminOutputData(admin.getFirstName(), admin.getLastName(), admin.getEmail(), admin.getPassword(), admin.getRepeatPassword());
                adminPresenter.prepareSuccessView(signupAdminOutputData);
            } catch (IOException ex) {
                System.out.println("Error in user");
            }
        }
    }
}