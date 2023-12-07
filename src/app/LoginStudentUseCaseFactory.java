package app;

import entity.StudentFactory;
import entity.PersonFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.landing_page.LandingPageViewModel;
import interface_adapter.login_student.LoginStudentController;
import interface_adapter.login_student.LoginStudentPresenter;
import interface_adapter.login_student.LoginStudentViewModel;
import interface_adapter.student_logged_in.StudentLoggedInViewModel;
import use_case.login_student.LoginStudentDataAccessInterface;
import use_case.login_student.LoginStudentInputBoundary;
import use_case.login_student.LoginStudentInteractor;
import use_case.login_student.LoginStudentOutputBoundary;
import view.StudentLoginView;

import javax.swing.*;
import java.io.IOException;


public class LoginStudentUseCaseFactory {

    /** Prevent instantiation. */
    private LoginStudentUseCaseFactory() {}

    public static StudentLoginView create(
            ViewManagerModel viewManagerModel,
            LoginStudentViewModel loginStudentViewModel,
            StudentLoggedInViewModel studentLoggedInViewModel,
            LandingPageViewModel landingPageViewModel,
            LoginStudentDataAccessInterface userDataAccessObject) {

        try {
            LoginStudentController loginController = createLoginUseCase(viewManagerModel, loginStudentViewModel, studentLoggedInViewModel, landingPageViewModel, userDataAccessObject);
            return new StudentLoginView(loginStudentViewModel, loginController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LoginStudentController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            LoginStudentViewModel loginViewModel,
            StudentLoggedInViewModel loggedInViewModel,
            LandingPageViewModel landingPageViewModel,
            LoginStudentDataAccessInterface userDataAccessObject) throws IOException {
        LoginStudentOutputBoundary loginOutputBoundary = new LoginStudentPresenter(viewManagerModel, loggedInViewModel, landingPageViewModel, loginViewModel);
        PersonFactory userFactory = new StudentFactory();
        LoginStudentInputBoundary loginInteractor = new LoginStudentInteractor(userDataAccessObject, loginOutputBoundary);
        return new LoginStudentController(loginInteractor);
    }
}

