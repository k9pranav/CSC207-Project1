package app;

import entity.AdminFactory;
import entity.PersonFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.admin_logged_in.AdminLoggedInViewModel;
import interface_adapter.landing_page.LandingPageViewModel;
import interface_adapter.login_admin.LoginAdminController;
import interface_adapter.login_admin.LoginAdminPresenter;
import interface_adapter.login_admin.LoginAdminViewModel;
import use_case.login_admin.LoginAdminDataAccessInterface;
import use_case.login_admin.LoginAdminInputBoundary;
import use_case.login_admin.LoginAdminInteractor;
import use_case.login_admin.LoginAdminOutputBoundary;
import view.AdminLoginView;

import javax.swing.*;
import java.io.IOException;

public class LoginAdminUseCaseFactory {
    private LoginAdminUseCaseFactory() {}

    public static AdminLoginView create(
            ViewManagerModel viewManagerModel,
            LoginAdminViewModel loginAdminViewModel,
            AdminLoggedInViewModel loggedInViewModel,
            LandingPageViewModel landingPageViewModel,
            LoginAdminDataAccessInterface userDataAccessObject) {

        try {
            LoginAdminController loginController = createLoginUseCase(
                    viewManagerModel,
                    loginAdminViewModel,
                    loggedInViewModel,
                    landingPageViewModel,
                    userDataAccessObject);
            return new AdminLoginView(loginAdminViewModel, loginController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LoginAdminController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            LoginAdminViewModel loginViewModel,
            AdminLoggedInViewModel loggedInViewModel,
            LandingPageViewModel landingPageViewModel,
            LoginAdminDataAccessInterface userDataAccessObject) throws IOException {

        LoginAdminOutputBoundary loginOutputBoundary = new LoginAdminPresenter(viewManagerModel, loginViewModel, landingPageViewModel, loggedInViewModel );

        PersonFactory userFactory = new AdminFactory();

        LoginAdminInputBoundary loginInteractor = new LoginAdminInteractor(
                userDataAccessObject, loginOutputBoundary);

        return new LoginAdminController(loginInteractor);
    }
}
