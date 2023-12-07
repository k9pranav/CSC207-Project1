package app;

import interface_adapter.login_admin.LoginAdminViewModel;
import interface_adapter.signup_admin.SignupAdminController;
import interface_adapter.signup_admin.SignupAdminPresenter;
import interface_adapter.signup_admin.SignupAdminViewModel;
import use_case.signup_admin.SignupAdminDataAccessInterface;
import entity.AdminFactory;
import entity.PersonFactory;
import interface_adapter.*;
import use_case.signup_admin.SignupAdminInputBoundary;
import use_case.signup_admin.SignupAdminInteractor;
import use_case.signup_admin.SignupAdminOutputBoundary;
import view.AdminSignupView;

import javax.swing.*;
import java.io.IOException;

import javax.swing.*;
import java.io.IOException;

public class SignupAdminUseCaseFactory {

    /** Prevent instantiation. */
    private SignupAdminUseCaseFactory() {}

    public static AdminSignupView create(
            ViewManagerModel viewManagerModel, LoginAdminViewModel loginViewModel, SignupAdminViewModel signupViewModel,
            SignupAdminDataAccessInterface userDataAccessObject) {

        try {
            SignupAdminController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel,
                    loginViewModel, userDataAccessObject);
            return new AdminSignupView(signupController, signupViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SignupAdminController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignupAdminViewModel signupViewModel, LoginAdminViewModel loginViewModel, SignupAdminDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SignupAdminOutputBoundary signupOutputBoundary = new SignupAdminPresenter(signupViewModel, viewManagerModel, loginViewModel);

        PersonFactory userFactory = new AdminFactory();

        SignupAdminInputBoundary userSignupInteractor = new SignupAdminInteractor(
                userDataAccessObject, signupOutputBoundary, (AdminFactory) userFactory);

        return new SignupAdminController(userSignupInteractor);
    }
}